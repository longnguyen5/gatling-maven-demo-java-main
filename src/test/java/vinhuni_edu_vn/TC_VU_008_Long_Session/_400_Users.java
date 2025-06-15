package vinhuni_edu_vn.TC_VU_008_Long_Session;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_008: Long Session Simulation
 * 10-min session with repeated navigation
 * Expected: No memory leaks, stable CPU/memory
 */
public class _400_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  // Pages to navigate during long session
  private FeederBuilder<Object> pagesFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("page", "/", "pageName", "Homepage"),
      Map.of("page", "/gioi-thieu-c01l0vp0a0.html", "pageName", "About"),
      Map.of("page", "/tin-tuc-c11l0vp0a0.html", "pageName", "News"),
      Map.of("page", "/chuong-trinh-dao-tao-c02l0vp0a0.html", "pageName", "Training"),
      Map.of("page", "/khoa-hoc-cong-nghe-c03l0vp0a0.html", "pageName", "Research"),
      Map.of("page", "/nguoi-hoc-c05l0vp0a0.html", "pageName", "Students"),
      Map.of("page", "/tuyen-sinh-c06l0vp0a0.html", "pageName", "Admission"),
      Map.of("page", "/thong-bao-c19l0vp0a0.html", "pageName", "Contact")
    )
  ).circular(); // Cycle through pages continuously
  private ScenarioBuilder longSessionScenario = scenario("Long Session Simulation")
    .during(Duration.ofMinutes(10)).on( // Run for exactly 10 minutes
      feed(pagesFeeder)
        .exec(
          http("Navigate to #{pageName}")
            .get("#{page}")
            .check(status().is(200))
            .check(responseTimeInMillis().lte(5000)) // Should remain stable
        )
        .pause(Duration.ofSeconds(8), Duration.ofSeconds(15)) // Realistic user behavior
    );

  {
    setUp(
      // Start with 20 users, gradually increase to 50 over first 2 minutes
      // Then maintain stable load for 8 minutes
      longSessionScenario.injectOpen(
        rampUsers(400).during(Duration.ofMinutes(8))
      )
    ).protocols(httpProtocol)
    .assertions(
      // Performance should remain stable throughout the 10-minute test
      global().responseTime().mean().lte(3000), // Average response time stable
      global().responseTime().percentile3().lte(6000), // 95% under 6s
      global().successfulRequests().percent().gte(98.0), // High success rate
      
      // Memory/CPU stability indicators - response times shouldn't degrade
      global().responseTime().max().lte(15000), // No extreme outliers
      
      // Ensure consistent performance across different pages
      details("Navigate to Homepage").responseTime().mean().lte(2500),
      details("Navigate to About").responseTime().mean().lte(3500),
      details("Navigate to News").responseTime().mean().lte(3500),
      details("Navigate to Training").responseTime().mean().lte(3500)
    );
  }
}
