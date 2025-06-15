package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;
import java.util.Map;
import java.util.List;

/**
 * Dynamic Viblo Post Reading Test
 * Accepts user count via system property: -DuserCount=N
 */
public class Dynamic_Post_Reading_Test extends Simulation {

    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

    // Post categories feeder
    private FeederBuilder<Object> categoryFeeder = listFeeder(
        List.of(
            Map.of("category", "javascript", "tag", "js"),
            Map.of("category", "python", "tag", "python"),
            Map.of("category", "java", "tag", "java"),
            Map.of("category", "react", "tag", "reactjs"),
            Map.of("category", "nodejs", "tag", "nodejs"),
            Map.of("category", "vue", "tag", "vuejs"),
            Map.of("category", "docker", "tag", "docker"),
            Map.of("category", "kubernetes", "tag", "k8s")
        )
    ).random();

    // Scenario definition
    private ScenarioBuilder scn = scenario("Viblo Post Reading Test - " + USER_COUNT + " Users")
        .feed(categoryFeeder)
        .exec(
            http("Load Homepage")
                .get("/")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(1, 2))
        .exec(
            http("Browse #{category} Posts")
                .get("/posts?tag=#{tag}")
                .check(status().is(200))
                .check(responseTimeInMillis().lte(5000))
        )
        .pause(Duration.ofSeconds(2, 4))
        .exec(
            http("Read First Post")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(3, 8))
        .exec(
            http("Load Comments")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofSeconds(2, 5))
        .exec(
            http("View Related Posts")
                .get("/posts?tag=#{tag}&sort=popular")
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
            .maxDuration(Duration.ofMinutes(12))
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
            .maxDuration(Duration.ofMinutes(18))
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
            .maxDuration(Duration.ofMinutes(25))
            .assertions(
                global().responseTime().percentile3().lte(6000),
                global().successfulRequests().percent().gte(85.0)
            );
        }
    }
}
