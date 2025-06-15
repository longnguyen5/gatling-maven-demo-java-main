package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;
import java.util.Map;
import java.util.List;

/**
 * Dynamic Viblo Long Session Test
 * Accepts user count via system property: -DuserCount=N
 * Simulates extended user sessions with multiple activities
 */
public class Dynamic_Long_Session_Test extends Simulation {

    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
        .shareConnections();

    // Session activities feeder
    private FeederBuilder<Object> activityFeeder = listFeeder(
        List.of(
            Map.of("activity", "browsing", "search_term", "javascript", "tag", "js"),
            Map.of("activity", "learning", "search_term", "python", "tag", "python"),
            Map.of("activity", "exploring", "search_term", "react", "tag", "reactjs"),
            Map.of("activity", "researching", "search_term", "nodejs", "tag", "nodejs"),
            Map.of("activity", "studying", "search_term", "docker", "tag", "docker"),
            Map.of("activity", "reading", "search_term", "kubernetes", "tag", "k8s")
        )
    ).random();

    // Long session scenario - simulates 15-30 minutes of user activity
    private ScenarioBuilder longSessionScn = scenario("Viblo Long Session Test - " + USER_COUNT + " Users")
        .feed(activityFeeder)
        
        // Phase 1: Initial browsing (5 minutes)
        .exec(
            http("Start Session - Homepage")
                .get("/")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(3, 8))
        .exec(
            http("Browse Recent Posts")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(5, 12))
        .exec(
            http("Read First Article")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(30, 90)) // Reading time
        
        // Phase 2: Search and exploration (8 minutes)
        .exec(
            http("Search: #{search_term}")
                .get("/search?q=#{search_term}")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(3, 8))
        .exec(
            http("Browse Search Results")
                .get("/search?q=#{search_term}&sort=popular")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(15, 45))
        .exec(
            http("Read Search Result Article")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(45, 120)) // Extended reading
        
        // Phase 3: Tag exploration (7 minutes)
        .exec(
            http("Explore Tag: #{tag}")
                .get("/tags/#{tag}")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(5, 15))
        .exec(
            http("Browse Tag Posts")
                .get("/posts?tag=#{tag}")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(3, 10))
        .exec(
            http("View Popular in Tag")
                .get("/posts?tag=#{tag}&sort=popular")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(20, 60))
        
        // Phase 4: User and community exploration (5 minutes)
        .exec(
            http("Browse Users")
                .get("/users")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(5, 15))
        .exec(
            http("View Top Users")
                .get("/users?sort=reputation")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(10, 30))
        .exec(
            http("Browse Organizations")
                .get("/organizations")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(5, 20))
        
        // Phase 5: Final browsing (5 minutes)
        .exec(
            http("Browse Questions")
                .get("/questions")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(10, 30))
        .exec(
            http("View Trending Posts")
                .get("/posts?sort=trending")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(15, 45))
        .exec(
            http("End Session - Homepage")
                .get("/")
                .check(status().is(200))
        );

    // Load model with extended duration based on user count
    {
        if (USER_COUNT <= 30) {
            // Light long session load
            setUp(
                longSessionScn.injectOpen(
                    atOnceUsers(USER_COUNT)
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(35))
            .assertions(
                global().responseTime().percentile3().lte(5000),
                global().successfulRequests().percent().gte(95.0)
            );
        } else if (USER_COUNT <= 100) {
            // Medium long session load
            setUp(
                longSessionScn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(5))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(45))
            .assertions(
                global().responseTime().percentile3().lte(6000),
                global().successfulRequests().percent().gte(90.0)
            );
        } else {
            // Heavy long session load
            setUp(
                longSessionScn.injectOpen(
                    rampUsers(USER_COUNT/2).during(Duration.ofMinutes(5)),
                    constantUsersPerSec(USER_COUNT/30.0).during(Duration.ofMinutes(10)),
                    rampUsers(USER_COUNT/2).during(Duration.ofMinutes(5))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(60))
            .assertions(
                global().responseTime().percentile3().lte(8000),
                global().successfulRequests().percent().gte(85.0)
            );
        }
    }
}
