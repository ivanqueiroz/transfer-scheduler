package dev.ivanqueiroz.transferscheduler;

import dev.ivanqueiroz.transferscheduler.application.web.controller.TransferController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransferSchedulerApplicationTests {

  @Autowired
  private TransferController transferController;

  @Test
  void contextLoads() {
    assertThat(transferController).isNotNull();
  }
}
