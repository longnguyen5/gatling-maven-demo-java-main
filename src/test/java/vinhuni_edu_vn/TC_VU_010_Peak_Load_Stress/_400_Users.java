package vinhuni_edu_vn.TC_VU_010_Peak_Load_Stress;

import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_010: Peak Load Stress Test (Tổng hợp)
 * Mô phỏng nhiều hành vi người dùng: homepage, news, search, xem chi tiết, tải ảnh, tải PDF, duyệt thông báo, navigation, download...
 * Random hành vi, không dùng feeder, stress hỗn hợp.
 * Expected: Hệ thống vẫn đáp ứng, không crash, các chỉ số ổn định.
 */
public class _400_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

  // Stress test scenario - tổng hợp random hành vi từ các TC_VU_001-009
  private ScenarioBuilder stressTestScenario = scenario("Peak Load Stress Test - Tổng hợp")
    .forever()
    .on(
      randomSwitch().on(
        // Homepage
        percent(15).then(
          exec(http("Homepage").get("/").check(status().in(200,301,302)).check(responseTimeInMillis().lte(10000)))
        ),
        // News
        percent(10).then(
          exec(http("News").get("/tin-tuc-c11l0vp0a0.html").check(status().is(200)).check(responseTimeInMillis().lte(10000)))
        ),
        // Academic Search
        percent(10).then(
          exec(http("Academic Search").get("/chuong-trinh-dao-tao-c02l0vp0a0.html").check(status().is(200)).check(responseTimeInMillis().lte(10000)))
        ),
        // Announcement Detail
        percent(10).then(
          exec(http("Announcement Detail").get("/thong-bao-c19l0vp0a0.html").check(status().is(200)).check(responseTimeInMillis().lte(10000)))
        ),
        // Image Rendering
        percent(10).then(
          exec(http("Image Rendering").get("/Upload/images/HCTH/2025-06/ACCA(1).jpg").check(status().in(200,304,404)).check(responseTimeInMillis().lte(15000)))
        ),
        // PDF Download
        percent(10).then(
          exec(http("PDF Download").get("/Upload/files/Hoidoingiasou/Nam2025/GSNguyenHuyBang.pdf").check(status().in(200,304,404)).check(responseTimeInMillis().lte(20000)))
        ),
        // Multi-Page Navigation
        percent(10).then(
          exec(
            http("Nav Home").get("/").check(status().is(200)).check(responseTimeInMillis().lte(5000))
          ).pause(1)
          .exec(
            http("Nav Search").get("/search.aspx").queryParam("q","Công nghệ thông tin").check(status().is(200)).check(responseTimeInMillis().lte(5000))
          ).pause(1)
          .exec(
            http("Nav Detail").get("/ket-noi-hop-tac-3-nha-de-trien-khai-cac-de-an-ve-phat-trien-nguon-nhan-luc-c11l0v0p0a131219.html").check(status().is(200)).check(responseTimeInMillis().lte(5000))
          )
        ),
        // Long Session (simulate by repeat homepage)
        percent(10).then(
          repeat(3).on(
            exec(http("LongSessionPage").get("/").check(status().is(200)).check(responseTimeInMillis().lte(10000))).pause(2)
          )
        ),
        // Search + Download Combo
        percent(5).then(
          exec(
            http("Combo Search").get("/tim-kiem").queryParam("q","quy định học tập").check(status().is(200)).check(responseTimeInMillis().lte(8000))
          ).pause(1)
          .exec(
            http("Combo Download").get("/files/quy-dinh-hoc-tap.pdf").check(status().in(200,304,404)).check(responseTimeInMillis().lte(20000))
          )
        )
      )
    );
  {
    setUp(
      stressTestScenario.injectOpen(
        rampUsers(400).during(Duration.ofMinutes(10))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(12000),
      global().responseTime().percentile3().lte(25000),
      global().responseTime().max().lte(60000),
      global().successfulRequests().percent().gte(70.0),
      global().failedRequests().count().lte(200L)
    );
  }
}
