package vinhuni_edu_vn.TC_VU_004_Image_Rendering;

import java.time.Duration;
import java.util.Map;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * TC_VU_004: Image Rendering Test - 1000 Users (Stress Test)
 * 1000 người dùng truy cập trang có nhiều hình ảnh
 * Expected: Thời gian phản hồi trung bình < 25s, tỷ lệ thành công > 55%
 */
public class _1000_Users extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://vinhuni.edu.vn")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptLanguageHeader("vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .connectionHeader("keep-alive")
    .shareConnections();

  private FeederBuilder<Object> imageGalleryFeeder = listFeeder(
    java.util.Arrays.asList(
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(1).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(2).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(3).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(4).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(5).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(6).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(7).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(8).jpg"),
        Map.of("imagePath", "/Upload/images/HCTH/2025-06/ACCA(9).jpg")
    )
  ).circular();

  private ScenarioBuilder imageRenderingScenario = scenario("Image Rendering Test - 100 Users")
    .exec(
      http("Access Homepage")
        .get("/")
        .check(status().in(200, 301, 302))
        .check(responseTimeInMillis().lte(30000))
    )
    .pause(Duration.ofSeconds(1), Duration.ofSeconds(2))
    // Lặp qua các ảnh trong feeder, mỗi user sẽ tải tất cả ảnh
    .repeat(9).on(
      feed(imageGalleryFeeder)
        .exec(
          http("Load Gallery Image")
            .get("#{imagePath}")
            .check(status().in(200, 404))
            .check(responseTimeInMillis().lte(10000))
        )
        .pause(Duration.ofMillis(500), Duration.ofSeconds(1))
    );

  {
    setUp(
      imageRenderingScenario.injectOpen(
        rampUsers(1000).during(Duration.ofMinutes(5))
      )
    ).protocols(httpProtocol)
    .assertions(
      global().responseTime().mean().lte(25000),
      global().responseTime().percentile3().lte(45000),
      global().successfulRequests().percent().gte(55.0)
    );
  }
}
