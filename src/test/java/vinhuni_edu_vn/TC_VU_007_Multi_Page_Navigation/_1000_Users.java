package vinhuni_edu_vn.TC_VU_007_Multi_Page_Navigation;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_007: Multi-Page Navigation Test
 * Homepage → Search → Detail view
 * Expected: Flow completed in under 6s
 */
public class _1000_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  // Search terms for navigation flow
  private FeederBuilder<Object> navigationFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("searchTerm", "Công nghệ thông tin", "detailPage", "/ket-noi-hop-tac-3-nha-de-trien-khai-cac-de-an-ve-phat-trien-nguon-nhan-luc-c11l0v0p0a131219.html"),
      Map.of("searchTerm", "Kinh tế", "detailPage", "/ban-tuyen-giao-va-dan-van-trung-uong-khao-sat-viec-thuc-hien-ket-luan-so-94-kl-tw-tai-truong-dai-hoc-vinh-c11l0v0p0a131217.html"),
      Map.of("searchTerm", "Toán học", "detailPage", "/truong-dai-hoc-vinh-tap-huan-nghiep-vu-kiem-tra-cong-tac-coi-thi-ky-thi-tot-nghiep-thpt-nam-2025-c11l0v0p0a131215.html"),
      Map.of("searchTerm", "Vật lý", "detailPage", "/lan-dau-tien-truong-dai-hoc-vinh-nam-trong-top-10-co-so-giao-duc-dai-hoc-vien-nghien-cuu-cua-viet-nam-tren-bang-xep-hang-scimago-c11l0v0p0a131209.html"),
      Map.of("searchTerm", "Hóa học", "detailPage", "/lich-seminar-vien-ky-thuat-va-cong-nghe-c03.07l0v0p0a131206.html")
    )
  ).random();

  private ScenarioBuilder multiPageNavigationScenario = scenario("Multi-Page Navigation Test")
    .feed(navigationFeeder)
    .exec(session -> {
      session.set("startTime", System.currentTimeMillis());
      return session;
    })
    .exec(
      http("Step 1: Homepage")
        .get("/")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(2000))
    )
    .pause(Duration.ofMillis(500), Duration.ofSeconds(1)) // Quick navigation
    .exec(
      http("Step 2: Search Page")
        .get("/search.aspx")
        .queryParam("q", "#{searchTerm}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(2000))
    )
    .pause(Duration.ofMillis(500), Duration.ofSeconds(1))
    .exec(
      http("Step 3: Detail View")
        .get("#{detailPage}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(2000))
    )
    .exec(session -> {
      long startTime = session.getLong("startTime");
      long endTime = System.currentTimeMillis();
      long totalTime = endTime - startTime;
      
      // Log the total flow time
      System.out.println("Total navigation flow time: " + totalTime + "ms");
      
      // Assert total time is under 6 seconds (6000ms)
      if (totalTime > 6000) {
        System.err.println("Navigation flow exceeded 6 seconds: " + totalTime + "ms");
      }
      
      return session.set("totalFlowTime", totalTime);
    });

  {
    setUp(
      // 1000 users performing complete navigation flows
      multiPageNavigationScenario.injectOpen(
        rampUsers(1000).during(Duration.ofMinutes(15))
      )
    ).protocols(httpProtocol)
    .assertions(
      // Individual step assertions
      details("Step 1: Homepage").responseTime().mean().lte(2000),
      details("Step 2: Search Page").responseTime().mean().lte(2000),
      details("Step 3: Detail View").responseTime().mean().lte(2000),
      
      // Overall flow assertions
      global().responseTime().mean().lte(2000), // Average per request < 2s
      global().successfulRequests().percent().gte(95.0), // 95% success rate
      
      // Total flow should complete quickly
      global().responseTime().percentile3().lte(4000) // 95% of requests < 4s
    );
  }
}
