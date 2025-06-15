package vinhuni_edu_vn.TC_VU_006_PDF_Download;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_006: PDF Download Test - 100 Users
 * 100 người dùng tải file PDF (quy chế, biểu mẫu, tài liệu)
 * Expected: Thời gian download < 30s, tỷ lệ thành công > 85%
 */
public class _200_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .connectionHeader("keep-alive")
    .shareConnections();

  private FeederBuilder<Object> pdfDocumentsFeeder = listFeeder(
    java.util.Arrays.asList(
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/GSNguyenHuyBang.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/GSLqQuocHoi.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/GSDinhXuanKhoa.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/GSNguyenThiMyLoc.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/GSNguyenVanQuang.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/GSThaiVanThanh.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSNguyenThiThuCuc.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSLeDucGiang.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSPhamThiHuong.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSTranNgocLong.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSNguyenDuyQuyet.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSDinhTrungThanh.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSPhanVanTien.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSTranBaTien.pdf"),
      Map.of("pdfPath", "/Upload/files/Hoidoingiasou/Nam2025/PGSHoangHuuViet.pdf")
    )
  ).random();

  private ScenarioBuilder pdfDownloadScenario = scenario("PDF Download Test - 100 Users")
    .feed(pdfDocumentsFeeder)
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().in(200, 301, 302))
        .check(responseTimeInMillis().lte(30000))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    .exec(
      http("Download PDF Document")
        .get("#{pdfPath}")
        .check(status().in(200, 404))
        .check(responseTimeInMillis().lte(30000))
        .check(header("Content-Type").exists())
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2));

  {
    setUp(
      pdfDownloadScenario.injectOpen(
        rampUsers(200).during(Duration.ofMinutes(2))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(15000),
      global().responseTime().percentile3().lte(30000),
      global().successfulRequests().percent().gte(85.0)
    );
  }
}
