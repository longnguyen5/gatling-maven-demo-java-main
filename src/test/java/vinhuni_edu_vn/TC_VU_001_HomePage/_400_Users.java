package vinhuni_edu_vn.TC_VU_001_HomePage;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_001: Homepage Load Test - 400 Users
 * 400 người dùng truy cập trang chủ đồng thời
 * Expected: Thời gian phản hồi trung bình < 12s, tỷ lệ thành công > 65%
 */
public class _400_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .connectionHeader("keep-alive") // Reuse connections
    .shareConnections(); // Share connections between users

  private ScenarioBuilder homepageLoadScenario = scenario("Homepage Load Test - 400 Users")
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().in(200, 301, 302)) // Accept redirects
        .check(responseTimeInMillis().lte(30000)) // 30s timeout
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
    });

  {
    setUp(
      // Test với tổng 400 users: ramp up trong 3 phút
      homepageLoadScenario.injectOpen(
        rampUsers(400).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(12000), // Thời gian phản hồi trung bình < 12s
      global().responseTime().percentile3().lte(25000), // 95% < 25s
      global().successfulRequests().percent().gte(65.0) // 65% thành công
    );
  }
}
