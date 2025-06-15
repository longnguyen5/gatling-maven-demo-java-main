package vinhuni_edu_vn.TC_VU_003_Academic_Search;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_003: Academic Search Test - 100 Users
 * 100 người dùng tìm kiếm chương trình đào tạo
 * Expected: Thời gian phản hồi trung bình < 8s, tỷ lệ thành công > 75%
 */
public class _100_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .connectionHeader("keep-alive")
    .shareConnections();

  private FeederBuilder<Object> searchTermsFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("searchTerm", "Công nghệ thông tin"),
      Map.of("searchTerm", "Kinh tế"),
      Map.of("searchTerm", "Toán học"),
      Map.of("searchTerm", "Vật lý"),
      Map.of("searchTerm", "Hóa học"),
      Map.of("searchTerm", "Sinh học"),
      Map.of("searchTerm", "Ngoại ngữ"),
      Map.of("searchTerm", "Sư phạm"),
      Map.of("searchTerm", "Luật"),
      Map.of("searchTerm", "Y học")
    )
  ).random();

  private ScenarioBuilder academicSearchScenario = scenario("Academic Search Test - 100 Users")
    .feed(searchTermsFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().in(200, 301, 302))
        .check(responseTimeInMillis().lte(30000))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Access Training Programs Page")
        .get("/chuong-trinh-dao-tao-c02l0vp0a0.html")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(8000))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("View Program Details")
        .get("/cong-nghe-ky-thuat-dieu-khien-va-tu-dong-hoa-ma-nganh-7510303-c02.01.03l0v0p0a131017.html")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(8000))
    );

  {
    setUp(
      academicSearchScenario.injectOpen(
        rampUsers(100).during(Duration.ofMinutes(1))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(8000),
      global().responseTime().percentile3().lte(15000),
      global().successfulRequests().percent().gte(75.0)
    );
  }
}
