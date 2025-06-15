package viblo_asia.TC_VIBLO_010_Peak_Load_Stress;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_010: Peak Load Stress Test - 100 Users
 * Stress test tổng hợp: mô phỏng peak traffic với nhiều hành vi khác nhau
 * Expected: Hệ thống vẫn ổn định, không crash, response time chấp nhận được
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private ScenarioBuilder peakLoadStressScenario = scenario("Viblo Peak Load Stress Test")
    .during(Duration.ofMinutes(5))
    .on(
      randomSwitch().on(
        // Homepage browsing - 25%
        percent(25).then(
          exec(http("Homepage").get("/").check(status().is(200)).check(responseTimeInMillis().lte(8000)))
        ),
        // Article search - 20%
        percent(20).then(
          exec(http("Search").get("/search").queryParam("q", "javascript").check(status().is(200)).check(responseTimeInMillis().lte(8000)))
        ),
        // Post reading - 20%
        percent(20).then(
          exec(http("Read Post").get("/newest").check(status().is(200)).check(responseTimeInMillis().lte(8000)))
        ),
        // Tag browsing - 15%
        percent(15).then(
          exec(http("Browse Tags").get("/tags/react").check(status().is(200)).check(responseTimeInMillis().lte(8000)))
        ),
        // User profiles - 10%
        percent(10).then(
          exec(http("View Profile").get("/u/viblo").check(status().in(200, 404)).check(responseTimeInMillis().lte(8000)))
        ),
        // API calls - 5%
        percent(5).then(
          exec(http("API Call").get("/api/posts").queryParam("page", "1").check(status().in(200, 404)).check(responseTimeInMillis().lte(5000)))
        ),
        // Category filtering - 5%
        percent(5).then(
          exec(http("Filter Category").get("/trending").check(status().is(200)).check(responseTimeInMillis().lte(8000)))
        )
      )
      .pause(Duration.ofMillis(500), Duration.ofSeconds(2))
    );

  {
    setUp(
      peakLoadStressScenario.injectOpen(
        // Gradual ramp up to simulate peak traffic
        rampUsers(50).during(Duration.ofSeconds(30)),
        rampUsers(150).during(Duration.ofMinutes(1)),
        constantUsersPerSec(100).during(Duration.ofMinutes(3)),
        rampUsers(50).during(Duration.ofSeconds(30))
      )
    ).protocols(httpProtocol)
    .assertions(
      // System stability under stress
      global().responseTime().mean().lte(5000),
      global().responseTime().percentile3().lte(10000),
      global().responseTime().max().lte(20000),
      
      // Success rate should remain reasonable under stress
      global().successfulRequests().percent().gte(80.0),
      
      // Specific component resilience
      details("Homepage").successfulRequests().percent().gte(85.0),
      details("Search").successfulRequests().percent().gte(75.0),
      details("Read Post").successfulRequests().percent().gte(80.0),
      
      // System should handle the load
      global().requestsPerSec().gte(50.0),
      global().failedRequests().count().lte(500L)
    );
  }
}
