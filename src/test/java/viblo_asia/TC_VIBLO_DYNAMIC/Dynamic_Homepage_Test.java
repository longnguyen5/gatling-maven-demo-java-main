package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

/**
 * Dynamic Viblo Homepage Load Test
 * Accepts user count via system property: -DuserCount=N
 */
public class Dynamic_Homepage_Test extends Simulation {    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

    // Scenario definition
    private ScenarioBuilder scn = scenario("Viblo Homepage Load Test - " + USER_COUNT + " Users")
        .exec(
            http("Load Homepage")
                .get("/")
                .check(status().is(200))
                .check(responseTimeInMillis().lte(5000))
        )
        .pause(Duration.ofSeconds(2))
        .exec(
            http("Load Recent Posts")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(1, 3))
        .exec(
            http("Load Popular Posts")
                .get("/posts?sort=popular")
                .check(status().is(200))
        );

    // Load model
    {
        if (USER_COUNT <= 50) {
            // Light load
            setUp(
                scn.injectOpen(
                    atOnceUsers(USER_COUNT)
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(10))
            .assertions(
                global().responseTime().percentile3().lte(3000),
                global().responseTime().percentile4().lte(5000),
                global().successfulRequests().percent().gte(95.0)
            );
        } else if (USER_COUNT <= 200) {
            // Medium load
            setUp(
                scn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(2))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(15))
            .assertions(
                global().responseTime().percentile3().lte(4000),
                global().responseTime().percentile4().lte(6000),
                global().successfulRequests().percent().gte(90.0)
            );
        } else {
            // Heavy load
            setUp(
                scn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(5))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(20))
            .assertions(
                global().responseTime().percentile3().lte(5000),
                global().responseTime().percentile4().lte(8000),
                global().successfulRequests().percent().gte(85.0)
            );
        }
    }
}
