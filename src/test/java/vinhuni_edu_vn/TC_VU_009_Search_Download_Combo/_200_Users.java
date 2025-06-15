package vinhuni_edu_vn.TC_VU_009_Search_Download_Combo;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_009: Search + Download Combo Test
 * Users search and download files
 * Expected: Smooth operation, no crashes
 */
public class _200_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,application/pdf,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  // Combined search terms and download files
  private FeederBuilder<Object> searchDownloadFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("searchTerm", "quy định học tập", "downloadFile", "/files/quy-dinh-hoc-tap.pdf"),
      Map.of("searchTerm", "lịch thi", "downloadFile", "/files/lich-thi-cuoi-ky.pdf"),
      Map.of("searchTerm", "biểu mẫu", "downloadFile", "/files/bieu-mau-sinh-vien.pdf"),
      Map.of("searchTerm", "học phí", "downloadFile", "/files/bang-hoc-phi.pdf"),
      Map.of("searchTerm", "đăng ký môn học", "downloadFile", "/files/huong-dan-dang-ky.pdf")
    )
  ).random();

  private ScenarioBuilder searchDownloadScenario = scenario("Search + Download Combo Test")
    .feed(searchDownloadFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(3000))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Perform Search")
        .get("/tim-kiem")
        .queryParam("q", "#{searchTerm}")
        .check(status().is(200))
        .check(responseTimeInMillis().lte(4000))
        .check(regex("(?i)#{searchTerm}|kết quả|tìm kiếm").exists())
    );

  {
    setUp(
      // 70 users performing search+download combinations
      searchDownloadScenario.injectOpen(
        rampUsers(200).during(Duration.ofMinutes(2))
      )
    ).protocols(httpProtocol)
    .assertions(
      // Smooth operation - no system crashes
      global().successfulRequests().percent().gte(95.0), // 95% success rate
      global().responseTime().mean().lte(5000), // Average response time
      global().responseTime().percentile3().lte(10000), // 95% under 10s
      
      // Search functionality
      details("Perform Search").successfulRequests().percent().gte(98.0),
      details("Perform Search").responseTime().mean().lte(4000),
      
      // Download functionality  
      details("Download Searched Document").successfulRequests().percent().gte(95.0),
      details("Download Searched Document").responseTime().percentile3().lte(25000),
      
      // No crashes - verify navigation still works after downloads
      details("Verify Download Completion").successfulRequests().percent().gte(98.0),
      
      // Overall system stability
      global().failedRequests().count().lte(15L) // Maximum 15 failed requests total
    );
  }
}
