package viblo_asia.TC_VIBLO_005_Tag_Browse;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_005: Tag Browsing Test - 100 Users
 * Duyệt các bài viết theo tag
 * Expected: Thời gian tải tag < 2s, tỷ lệ thành công > 90%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> tagsFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("tagName", "javascript"),
      Map.of("tagName", "python"),
      Map.of("tagName", "react"),
      Map.of("tagName", "vue"),
      Map.of("tagName", "nodejs"),
      Map.of("tagName", "php"),
      Map.of("tagName", "laravel"),
      Map.of("tagName", "docker"),
      Map.of("tagName", "devops"),
      Map.of("tagName", "frontend")
    )
  ).random();

  private ScenarioBuilder tagBrowseScenario = scenario("Viblo Tag Browse Test")
    .feed(tagsFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Browse Tags Page")
        .get("/tags")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("View Tag Posts")
        .get("/tags/#{tagName}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
        .check(regex("(?i)#{tagName}|posts|articles").exists())
    )
    .pause(Duration.ofSeconds(3), Duration.ofSeconds(5));

  {
    setUp(
      tagBrowseScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(2000),
      global().successfulRequests().percent().gte(90.0),
      details("View Tag Posts").responseTime().percentile3().lte(4000)
    );
  }
}
