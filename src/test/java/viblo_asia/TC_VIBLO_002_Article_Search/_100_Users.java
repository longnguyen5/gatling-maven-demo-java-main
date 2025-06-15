package viblo_asia.TC_VIBLO_002_Article_Search;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_002: Article Search Test - 100 Users
 * Tìm kiếm bài viết với các từ khóa khác nhau
 * Expected: Kết quả tìm kiếm < 2s, tỷ lệ thành công > 90%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> searchTermsFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("searchTerm", "javascript"),
      Map.of("searchTerm", "python"),
      Map.of("searchTerm", "react"),
      Map.of("searchTerm", "nodejs"),
      Map.of("searchTerm", "vue"),
      Map.of("searchTerm", "php"),
      Map.of("searchTerm", "laravel"),
      Map.of("searchTerm", "docker"),
      Map.of("searchTerm", "kubernetes"),
      Map.of("searchTerm", "microservices")
    )
  ).random();

  private ScenarioBuilder articleSearchScenario = scenario("Viblo Article Search Test")
    .feed(searchTermsFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Search Articles")
        .get("/search")
        .queryParam("q", "#{searchTerm}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
        .check(regex("(?i)kết quả|results|#{searchTerm}").exists())
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(3));

  {
    setUp(
      articleSearchScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(2000),
      global().successfulRequests().percent().gte(90.0),
      details("Search Articles").responseTime().percentile3().lte(4000)
    );
  }
}
