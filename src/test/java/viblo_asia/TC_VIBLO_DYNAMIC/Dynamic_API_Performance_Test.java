package viblo_asia.TC_VIBLO_DYNAMIC;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;
import java.util.Map;
import java.util.List;

/**
 * Dynamic Viblo API Performance Test
 * Accepts user count via system property: -DuserCount=N
 */
public class Dynamic_API_Performance_Test extends Simulation {

    // Get user count from system property, default to 100
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("userCount", "100"));

    // HTTP configuration
    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://viblo.asia")
        .acceptHeader("application/json")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
        .contentTypeHeader("application/json");

    // API endpoints feeder
    private FeederBuilder<Object> apiFeeder = listFeeder(
        List.of(
            Map.of("endpoint", "/api/posts", "method", "GET"),
            Map.of("endpoint", "/api/tags", "method", "GET"),
            Map.of("endpoint", "/api/users", "method", "GET"),
            Map.of("endpoint", "/api/questions", "method", "GET"),
            Map.of("endpoint", "/api/organizations", "method", "GET"),
            Map.of("endpoint", "/api/posts/popular", "method", "GET"),
            Map.of("endpoint", "/api/posts/trending", "method", "GET"),
            Map.of("endpoint", "/api/search", "method", "GET")
        )
    ).random();

    // Search terms for API testing
    private FeederBuilder<Object> searchFeeder = listFeeder(
        List.of(
            Map.of("query", "javascript"),
            Map.of("query", "python"),
            Map.of("query", "react"),
            Map.of("query", "nodejs"),
            Map.of("query", "docker"),
            Map.of("query", "kubernetes")
        )
    ).random();

    // Scenario definition
    private ScenarioBuilder scn = scenario("Viblo API Performance Test - " + USER_COUNT + " Users")
        .feed(apiFeeder)
        .feed(searchFeeder)
        .exec(
            http("API Call: #{endpoint}")
                .get("#{endpoint}")
                .check(status().in(200, 401, 403)) // Some APIs might require auth
                .check(responseTimeInMillis().lte(3000))
        )
        .pause(Duration.ofMillis(500), Duration.ofMillis(1500))
        .exec(
            http("API Search")
                .get("/api/search?q=#{query}")
                .check(status().in(200, 401, 403))
                .check(responseTimeInMillis().lte(4000))
        )
        .pause(Duration.ofMillis(300), Duration.ofMillis(1000))
        .exec(
            http("API Posts with Pagination")
                .get("/api/posts?page=1&limit=10")
                .check(status().in(200, 401, 403))
        )
        .pause(Duration.ofMillis(200), Duration.ofMillis(800))
        .exec(
            http("API Tags Popular")
                .get("/api/tags?sort=popular&limit=20")
                .check(status().in(200, 401, 403))
        )
        .pause(Duration.ofMillis(100), Duration.ofMillis(600))
        .exec(
            http("API Users Active")
                .get("/api/users?sort=active&limit=15")
                .check(status().in(200, 401, 403))
        );

    // Load model based on user count - API testing can handle higher loads
    {
        if (USER_COUNT <= 100) {
            // Light API load
            setUp(
                scn.injectOpen(
                    atOnceUsers(USER_COUNT)
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(8))
            .assertions(
                global().responseTime().percentile3().lte(2000),
                global().responseTime().percentile4().lte(3000),
                global().successfulRequests().percent().gte(85.0) // Lower due to potential auth issues
            );
        } else if (USER_COUNT <= 300) {
            // Medium API load
            setUp(
                scn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(2))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(12))
            .assertions(
                global().responseTime().percentile3().lte(3000),
                global().responseTime().percentile4().lte(4000),
                global().successfulRequests().percent().gte(80.0)
            );
        } else {
            // Heavy API load
            setUp(
                scn.injectOpen(
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(3)),
                    constantUsersPerSec(USER_COUNT/20.0).during(Duration.ofMinutes(5))
                )
            ).protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(15))
            .assertions(
                global().responseTime().percentile3().lte(4000),
                global().responseTime().percentile4().lte(6000),
                global().successfulRequests().percent().gte(75.0)
            );
        }
    }
}
