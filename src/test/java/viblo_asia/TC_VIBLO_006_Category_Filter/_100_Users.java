package viblo_asia.TC_VIBLO_006_Category_Filter;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_006: Category Filter Test - 100 Users
 * Lọc bài viết theo danh mục (newest, trending, series)
 * Expected: Thời gian tải < 2s, tỷ lệ thành công > 95%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> categoriesFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("category", "newest"),
      Map.of("category", "trending"),
      Map.of("category", "series"),
      Map.of("category", "questions"),
      Map.of("category", "clips"),
      Map.of("category", "events")
    )
  ).random();

  private ScenarioBuilder categoryFilterScenario = scenario("Viblo Category Filter Test")
    .feed(categoriesFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Filter by Category")
        .get("/#{category}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
        .check(regex("(?i)#{category}|posts|articles").exists())
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(3))
    .exec(
      http("Load More Posts")
        .get("/#{category}")
        .queryParam("page", "2")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(4));

  {
    setUp(
      categoryFilterScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(2000),
      global().successfulRequests().percent().gte(95.0),
      details("Filter by Category").responseTime().percentile3().lte(3500)
    );
  }
}
