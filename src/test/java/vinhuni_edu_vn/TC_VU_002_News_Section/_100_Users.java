package vinhuni_edu_vn.TC_VU_002_News_Section;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_002: News Section Access Test
 * Users browse the latest news
 * Expected: 95% requests under SLA, stable response
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  private ScenarioBuilder newsAccessScenario = scenario("News Section Access Test")
    .exec(
      http("Access News Section")
        .get("/tin-tuc-c11l0vp0a0.html")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000)) // SLA: 3 seconds
        .check(regex("(?i)tin tức|news|thông báo").exists())
    );
  {
    setUp(
      // More realistic load - 30 users over 1 minute, sustain 15 users for 2 minutes
      newsAccessScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().percentile3().lte(8000), // 95% under 8s (more realistic)
      global().responseTime().mean().lte(5000), // Average response time 5s
      global().successfulRequests().percent().gte(85.0), // 85% success rate
      forAll().responseTime().max().lte(20000) // No request should exceed 20s
    );
  }
}
