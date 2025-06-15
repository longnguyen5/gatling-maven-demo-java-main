package viblo_asia.TC_VIBLO_004_User_Profile;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_004: User Profile Viewing Test - 100 Users
 * Xem profile của các user nổi tiếng trên Viblo
 * Expected: Thời gian tải profile < 2s, tỷ lệ thành công > 90%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> userProfilesFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("username", "viblo"),
      Map.of("username", "framgia"),
      Map.of("username", "sun-asterisk"),
      Map.of("username", "techmaster"),
      Map.of("username", "nordic-coder"),
      Map.of("username", "toidicodedao"),
      Map.of("username", "zalopay"),
      Map.of("username", "tiki"),
      Map.of("username", "shopee"),
      Map.of("username", "vnpay")
    )
  ).random();

  private ScenarioBuilder userProfileScenario = scenario("Viblo User Profile Test")
    .feed(userProfilesFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("View User Profile")
        .get("/u/#{username}")
        .check(status().in(200, 404))
        .check(responseTimeInMillis().lte(3000))
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(4))
    .exec(
      http("View User Posts")
        .get("/u/#{username}/posts")
        .check(status().in(200, 404))
        .check(responseTimeInMillis().lte(3000))
    )
    .pause(Duration.ofSeconds(2), Duration.ofSeconds(3));

  {
    setUp(
      userProfileScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(2500),
      global().successfulRequests().percent().gte(90.0),
      details("View User Profile").responseTime().percentile3().lte(4000)
    );
  }
}
