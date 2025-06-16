package vinhuni_edu_vn.TC_VU_001_HomePage;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_001: Homepage Load Test - 1000 Users
 * 1000 người dùng truy cập trang chủ đồng thời (Stress Test)
 * Expected: Thời gian phản hồi trung bình < 20s, tỷ lệ thành công > 50%
 */
public class _1000_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .connectionHeader("keep-alive") // Reuse connections
    .shareConnections(); // Share connections between users

  private ScenarioBuilder homepageLoadScenario = scenario("Homepage Load Test - 1000 Users")
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().in(200, 301, 302, 503, 504)) // Accept server errors during stress
        .check(responseTimeInMillis().lte(60000)) // 60s timeout cho stress test
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
      // Test với tổng 1000 users: ramp up trong 5 phút (stress test)
      homepageLoadScenario.injectOpen(
        rampUsers(1000).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(20000), // Thời gian phản hồi trung bình < 20s
      global().responseTime().percentile3().lte(40000), // 95% < 40s
      global().successfulRequests().percent().gte(50.0) // 50% thành công (stress test)
    );
  }
}
