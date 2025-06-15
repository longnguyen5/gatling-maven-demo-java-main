package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;
import java.util.Map;
import java.util.List;

/**
 * Dynamic Viblo Article Search Test
 * Accepts user count via system property: -DuserCount=N
 */
public class Dynamic_Article_Search_Test extends Simulation {

    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");    // Search terms feeder
    private FeederBuilder<Object> searchFeeder = listFeeder(
        List.of(
            Map.of("search_term", "Java"),
            Map.of("search_term", "Python"), 
            Map.of("search_term", "JavaScript"),
            Map.of("search_term", "React"),
            Map.of("search_term", "Node.js"),
            Map.of("search_term", "Docker"),
            Map.of("search_term", "Kubernetes"),
            Map.of("search_term", "Machine Learning"),
            Map.of("search_term", "AI"),
            Map.of("search_term", "DevOps"),
            Map.of("search_term", "Frontend"),
            Map.of("search_term", "Backend"),
            Map.of("search_term", "Database"),
            Map.of("search_term", "API")
        )
    ).random();

    // Scenario definition
    private ScenarioBuilder scn = scenario("Viblo Article Search Test - " + USER_COUNT + " Users")
        .feed(searchFeeder)
        .exec(
            http("Load Homepage")
                .get("/")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(1, 2))
        .exec(
            http("Search Articles")
                .get("/search?q=#{search_term}")
                .check(status().is(200))
                .check(responseTimeInMillis().lte(6000))
        )
        .pause(Duration.ofSeconds(2, 4))
        .exec(
            http("Filter Search Results")
                .get("/search?q=#{search_term}&sort=newest")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(1, 2))
        .exec(
            http("Load Search Page 2")
                .get("/search?q=#{search_term}&page=2")
                .check(status().is(200))
        );

    // Load model based on user count
    {
        if (USER_COUNT <= 50) {
            setUp(
                scn.injectOpen(
                    atOnceUsers(USER_COUNT)
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(10))
            .assertions(
                global().responseTime().percentile3().lte(4000),
                global().successfulRequests().percent().gte(95.0)
            );
        } else if (USER_COUNT <= 200) {
            setUp(
                scn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(3))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(15))
            .assertions(
                global().responseTime().percentile3().lte(5000),
                global().successfulRequests().percent().gte(90.0)
            );
        } else {
            setUp(
                scn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(5))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(20))
            .assertions(
                global().responseTime().percentile3().lte(6000),
                global().successfulRequests().percent().gte(85.0)
            );
        }
    }
}
