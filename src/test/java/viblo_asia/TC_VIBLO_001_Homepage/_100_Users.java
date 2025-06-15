package viblo_asia.TC_VIBLO_001_Homepage;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_001: Homepage Load Test - 100 Users
 * 100 người dùng truy cập trang chủ Viblo.asia đồng thời
 * Expected: Thời gian phản hồi < 3s, tỷ lệ thành công > 95%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private ScenarioBuilder homepageLoadScenario = scenario("Viblo Homepage Load Test")
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(5000))
        .check(regex("(?i)viblo|newest|trending").exists())
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(4));

  {
    setUp(
      homepageLoadScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(3000),
      global().responseTime().percentile3().lte(5000),
      global().successfulRequests().percent().gte(95.0)
    );
  }
}
