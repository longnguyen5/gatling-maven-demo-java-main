package viblo_asia.TC_VIBLO_007_Multi_Page_Navigation;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VIBLO_007: Multi-Page Navigation Test - 100 Users
 * Navigation flow: Homepage → Search → Post → Profile → Tags
 * Expected: Toàn bộ flow hoàn thành < 10s
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://viblo.asia")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

  private FeederBuilder<Object> navigationFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("searchTerm", "javascript", "username", "viblo", "tagName", "frontend"),
      Map.of("searchTerm", "python", "username", "framgia", "tagName", "backend"),
      Map.of("searchTerm", "react", "username", "sun-asterisk", "tagName", "react"),
      Map.of("searchTerm", "vue", "username", "techmaster", "tagName", "vue"),
      Map.of("searchTerm", "nodejs", "username", "toidicodedao", "tagName", "nodejs")
    )
  ).random();

  private ScenarioBuilder multiPageNavScenario = scenario("Viblo Multi-Page Navigation Test")
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
    .pause(Duration.ofMillis(500), Duration.ofSeconds(1))
    .exec(
      http("Step 2: Search")
        .get("/search")
        .queryParam("q", "#{searchTerm}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(2000))
    )
    .pause(Duration.ofMillis(500), Duration.ofSeconds(1))
    .exec(
      http("Step 3: View Profile")
        .get("/u/#{username}")
        .check(status().in(200, 404))
        .check(responseTimeInMillis().lte(2000))
    )
    .pause(Duration.ofMillis(500), Duration.ofSeconds(1))
    .exec(
      http("Step 4: Browse Tag")
        .get("/tags/#{tagName}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(2000))
    )
    .exec(session -> {
      long startTime = session.getLong("startTime");
      long endTime = System.currentTimeMillis();
      long totalTime = endTime - startTime;
      
      System.out.println("Total navigation flow time: " + totalTime + "ms");
      
      if (totalTime > 10000) {
        System.err.println("Navigation flow exceeded 10 seconds: " + totalTime + "ms");
      }
      
      return session.set("totalFlowTime", totalTime);
    });

  {
    setUp(
      multiPageNavScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(1500),
      global().successfulRequests().percent().gte(85.0),
      global().responseTime().percentile3().lte(3000)
    );
  }
}
