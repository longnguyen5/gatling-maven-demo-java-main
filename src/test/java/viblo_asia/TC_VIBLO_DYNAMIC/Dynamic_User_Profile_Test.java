package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;
import java.util.Map;
import java.util.List;

/**
 * Dynamic Viblo User Profile Test
 * Accepts user count via system property: -DuserCount=N
 */
public class Dynamic_User_Profile_Test extends Simulation {

    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

    // User profiles feeder (example usernames)
    private FeederBuilder<Object> userFeeder = listFeeder(
        List.of(
            Map.of("username", "admin", "profile_type", "admin"),
            Map.of("username", "user1", "profile_type", "regular"),
            Map.of("username", "developer", "profile_type", "developer"),
            Map.of("username", "author", "profile_type", "author"),
            Map.of("username", "contributor", "profile_type", "contributor"),
            Map.of("username", "moderator", "profile_type", "moderator"),
            Map.of("username", "expert", "profile_type", "expert"),
            Map.of("username", "newbie", "profile_type", "newbie")
        )
    ).random();

    // Scenario definition
    private ScenarioBuilder scn = scenario("Viblo User Profile Test - " + USER_COUNT + " Users")
        .feed(userFeeder)
        .exec(
            http("Load Homepage")
                .get("/")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(1, 2))
        .exec(
            http("Browse Users List")
                .get("/users")
                .check(status().is(200))
                .check(responseTimeInMillis().lte(5000))
        )
        .pause(Duration.ofSeconds(2, 3))
        .exec(
            http("View User Profile - #{username}")
                .get("/u/#{username}")
                .check(status().in(200, 404)) // Some users might not exist
        )
        .pause(Duration.ofSeconds(3, 6))
        .exec(
            http("View User Posts")
                .get("/u/#{username}/posts")
                .check(status().in(200, 404))
        )
        .pause(Duration.ofSeconds(2, 4))
        .exec(
            http("View User Questions")
                .get("/u/#{username}/questions")
                .check(status().in(200, 404))
        )
        .pause(Duration.ofSeconds(1, 3))
        .exec(
            http("Load Top Users")
                .get("/users?sort=reputation")
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
                global().successfulRequests().percent().gte(90.0) // Lower due to 404s
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
                global().successfulRequests().percent().gte(85.0)
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
                global().successfulRequests().percent().gte(80.0)
            );
        }
    }
}
