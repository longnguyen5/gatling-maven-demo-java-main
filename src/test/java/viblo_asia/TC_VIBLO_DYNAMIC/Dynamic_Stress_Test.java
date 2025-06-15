package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

/**
 * Dynamic Viblo Stress Test
 * Accepts user count via system property: -DuserCount=N
 * Applies intensive stress testing patterns
 */
public class Dynamic_Stress_Test extends Simulation {

    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
        .shareConnections();    // Stress scenario - rapid requests with minimal pauses
    private ScenarioBuilder stressScn = scenario("Viblo Stress Test - " + USER_COUNT + " Users")
        .exec(
            http("Rapid Homepage Load")
                .get("/")
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(500), Duration.ofMillis(1000))
        .exec(
            http("Rapid Posts Load")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(300), Duration.ofMillis(800))
        .exec(
            http("Rapid Search")
                .get("/search?q=performance")
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(200), Duration.ofMillis(600))
        .exec(
            http("Rapid Tags Load")
                .get("/tags")
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(100), Duration.ofMillis(500))
        .exec(
            http("Rapid Users Load")
                .get("/users")
                .check(status().is(200))
        );

    // Load model with different stress patterns based on user count
    {
        if (USER_COUNT <= 50) {
            // Light stress - steady load
            setUp(
                stressScn.injectOpen(
                    atOnceUsers(USER_COUNT),
                    nothingFor(Duration.ofSeconds(30)),
                    atOnceUsers(USER_COUNT)
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(8))
            .assertions(
                global().responseTime().percentile3().lte(5000),
                global().successfulRequests().percent().gte(90.0)
            );
        } else if (USER_COUNT <= 200) {
            // Medium stress - spike pattern
            setUp(
                stressScn.injectOpen(
                    rampUsers(USER_COUNT/2).during(Duration.ofSeconds(30)),
                    atOnceUsers(USER_COUNT/2),
                    constantUsersPerSec(USER_COUNT/10.0).during(Duration.ofMinutes(2))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(15))
            .assertions(
                global().responseTime().percentile3().lte(6000),
                global().successfulRequests().percent().gte(85.0)
            );
        } else if (USER_COUNT <= 500) {
            // Heavy stress - sustained high load
            setUp(
                stressScn.injectOpen(
                    rampUsers(USER_COUNT/3).during(Duration.ofMinutes(1)),
                    constantUsersPerSec(USER_COUNT/15.0).during(Duration.ofMinutes(3)),
                    rampUsers(USER_COUNT/3).during(Duration.ofMinutes(1)),
                    constantUsersPerSec(USER_COUNT/10.0).during(Duration.ofMinutes(2))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(20))
            .assertions(
                global().responseTime().percentile3().lte(8000),
                global().successfulRequests().percent().gte(80.0)
            );
        } else {
            // Extreme stress - maximum load testing
            setUp(
                stressScn.injectOpen(
                    rampUsers(USER_COUNT/4).during(Duration.ofMinutes(2)),
                    constantUsersPerSec(USER_COUNT/20.0).during(Duration.ofMinutes(5)),
                    rampUsers(USER_COUNT/4).during(Duration.ofMinutes(1)),
                    constantUsersPerSec(USER_COUNT/15.0).during(Duration.ofMinutes(3)),
                    atOnceUsers(USER_COUNT/2)
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(30))
            .assertions(
                global().responseTime().percentile3().lte(10000),
                global().successfulRequests().percent().gte(75.0)
            );
        }
    }
}
