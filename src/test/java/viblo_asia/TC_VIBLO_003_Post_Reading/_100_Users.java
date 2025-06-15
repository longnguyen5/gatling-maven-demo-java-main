package viblo_asia.TC_VIBLO_003_Post_Reading;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_003: Post Reading Test - 100 Users
 * Đọc bài viết phổ biến trên Viblo
 * Expected: Thời gian tải bài viết < 3s, tỷ lệ thành công > 95%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> popularPostsFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("category", "newest", "postSlug", "gioi-thieu-ve-graphql"),
      Map.of("category", "trending", "postSlug", "microservices-architecture"),
      Map.of("category", "newest", "postSlug", "docker-best-practices"),
      Map.of("category", "trending", "postSlug", "react-hooks-guide"),
      Map.of("category", "newest", "postSlug", "nodejs-performance"),
      Map.of("category", "trending", "postSlug", "vue-3-composition-api"),
      Map.of("category", "newest", "postSlug", "nam-2025-ai-lam-sinh-vien-rat-kho-kiem-viec-vay-ta-can-lam-gi-qPoL7KzmLvk"),
      Map.of("category", "trending", "postSlug", "6-cong-cu-giup-ban-tao-hinh-anh-code-dep-de-chia-se-online-Ny0VG2EyLPA")
    )
  ).random();

  private ScenarioBuilder postReadingScenario = scenario("Viblo Post Reading Test")
    .feed(popularPostsFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Browse Category")
        .get("/#{category}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(3))
    .exec(
      http("Read Post")
        .get("/p/#{postSlug}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(4000))
        .check(regex("(?i)comments|like|share|view").exists())
    )
    .pause(Duration.ofSeconds(30), Duration.ofSeconds(60)); // Reading time

  {
    setUp(
      postReadingScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(2))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(3000),
      global().successfulRequests().percent().gte(95.0),
      details("Read Post").responseTime().percentile3().lte(5000)
    );
  }
}
