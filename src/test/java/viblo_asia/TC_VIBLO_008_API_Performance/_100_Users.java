package viblo_asia.TC_VIBLO_008_API_Performance;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_008: API Performance Test - 100 Users
 * Test hiệu suất các API endpoints của Viblo
 * Expected: API response time < 1s, tỷ lệ thành công > 95%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("application/json, text/plain, */*")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> apiEndpointsFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("endpoint", "/api/posts", "params", "?page=1&limit=20"),
      Map.of("endpoint", "/api/posts/trending", "params", "?page=1&limit=10"),
      Map.of("endpoint", "/api/posts/newest", "params", "?page=1&limit=10"),
      Map.of("endpoint", "/api/tags", "params", "?page=1&limit=50"),
      Map.of("endpoint", "/api/users", "params", "?page=1&limit=20"),
      Map.of("endpoint", "/api/search", "params", "?q=javascript&page=1"),
      Map.of("endpoint", "/api/categories", "params", ""),
      Map.of("endpoint", "/api/posts/popular", "params", "?page=1&limit=10")
    )
  ).random();

  private ScenarioBuilder apiPerformanceScenario = scenario("Viblo API Performance Test")
    .feed(apiEndpointsFeeder)
    .exec(
      http("API Request")
        .get("#{endpoint}#{params}")
        .check(status().in(200, 404))
        .check(responseTimeInMillis().lte(2000))
        .check(header("Content-Type").exists())
    )
    .pause(Duration.ofMillis(100), Duration.ofMillis(500));

  {
    setUp(
      apiPerformanceScenario.injectOpen(
        rampUsers(100).during(Duration.ofSeconds(30)),
        constantUsersPerSec(20).during(Duration.ofMinutes(2))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(1000),
      global().responseTime().percentile3().lte(2000),
      global().successfulRequests().percent().gte(95.0),
      global().requestsPerSec().gte(50.0)
    );
  }
}
