package vinhuni_edu_vn.TC_VU_005_Announcement_Detail;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_005: Announcement Detail View Test
 * Open and read announcement details
 * Expected: 95% successful requests
 */
public class _1000_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  private ScenarioBuilder announcementDetailScenario = scenario("Announcement Detail View Test")
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Access Announcements List")
        .get("/thong-bao-c19l0vp0a0.html")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(4000))
        .check(regex("(?i)thông báo|announcement").exists())
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(4)) // User reads the list
    .exec(
      http("View Announcement Detail 1")
        .get("/ve-viec-ra-soat-chan-chinh-tac-phong-hoc-tap-lam-viec-cua-nguoi-hoc-va-vien-chuc-nguoi-lao-dong-c19l0v0p0a131197.html")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(4000))
    )
    .pause(Duration.ofSeconds(3), Duration.ofSeconds(6)) // User reads the announcement
    .exec(
      http("View Another Announcement")
        .get("/hoan-lich-thi-vong-2-ky-tuyen-dung-vien-chuc-va-nguoi-lao-dong-nam-2024-cua-truong-dai-hoc-vinh-c19l0v0p0a130943.html")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(4000))
    );

  {
    setUp(
      // 1000 users browsing announcements
      announcementDetailScenario.injectOpen(
        rampUsers(1000).during(Duration.ofMinutes(10))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().successfulRequests().percent().gte(95.0), // 95% successful requests
      global().responseTime().mean().lte(4000), // Average response time
      global().responseTime().percentile3().lte(6000), // 95% under 6s
      // Specific assertion for announcement pages
      details("Access Announcements List").successfulRequests().percent().gte(95.0),
      details("View Announcement Detail 1").successfulRequests().percent().gte(95.0)
    );
  }
}
