package viblo_asia.TC_VIBLO_009_Long_Session;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_009: Long Session Test - 100 Users
 * Mô phỏng phiên đọc dài: browse → read → search → read more
 * Expected: Session ổn định trong 15 phút, không memory leak
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> longSessionActivitiesFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("activity", "browse", "path", "/newest", "duration", "30"),
      Map.of("activity", "search", "path", "/search?q=react", "duration", "20"),
      Map.of("activity", "read", "path", "/p/sample-post", "duration", "60"),
      Map.of("activity", "tags", "path", "/tags/javascript", "duration", "25"),
      Map.of("activity", "trending", "path", "/trending", "duration", "35"),
      Map.of("activity", "profile", "path", "/u/viblo", "duration", "15"),
      Map.of("activity", "series", "path", "/series", "duration", "20"),
      Map.of("activity", "clips", "path", "/clips", "duration", "40")
    )
  ).circular();

  private ScenarioBuilder longSessionScenario = scenario("Viblo Long Session Test")
    .during(Duration.ofMinutes(15)).on(
      feed(longSessionActivitiesFeeder)
        .exec(
          http("Activity: #{activity}")
            .get("#{path}")
            .check(status().in(200, 404))
            .check(responseTimeInMillis().lte(5000))
        )
        .pause("#{duration}")
    );

  {
    setUp(
      longSessionScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(2))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(3000),
      global().responseTime().percentile3().lte(6000),
      global().successfulRequests().percent().gte(90.0),
      global().responseTime().max().lte(15000)
    );
  }
}
