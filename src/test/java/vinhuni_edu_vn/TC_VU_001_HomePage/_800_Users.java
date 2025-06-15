package vinhuni_edu_vn.TC_VU_001_HomePage;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_001: Homepage Load Test - 800 Users
 * 800 người dùng truy cập trang chủ đồng thời
 * Expected: Thời gian phản hồi trung bình < 15s, tỷ lệ thành công > 60%
 */
public class _800_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .connectionHeader("keep-alive") // Reuse connections
    .shareConnections(); // Share connections between users

  private ScenarioBuilder homepageLoadScenario = scenario("Homepage Load Test - 800 Users")
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().in(200, 301, 302)) // Accept redirects
        .check(responseTimeInMillis().lte(40000)) // 40s timeout cho load cao
        .check(bodyString().saveAs("responseBody")) // Save response for debugging
    )
    .exec(session -> {
      // Log response details for debugging
      String body = session.getString("responseBody");
      if (body != null && body.length() > 100) {
        System.out.println("User " + session.userId() + " - Response received, length: " + body.length());
      } else {
        System.out.println("User " + session.userId() + " - Short or empty response");
      }
      return session;
    })
    .pause(Duration.ofSeconds(3), Duration.ofSeconds(7)); // Longer pause for high load

  {
    setUp(
      // Test với tổng 800 users: ramp up trong 4 phút
      homepageLoadScenario.injectOpen(
        rampUsers(800).during(Duration.ofMinutes(4))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(15000), // Thời gian phản hồi trung bình < 15s
      global().responseTime().percentile3().lte(30000), // 95% < 30s
      global().successfulRequests().percent().gte(60.0) // 60% thành công
    );
  }
}
