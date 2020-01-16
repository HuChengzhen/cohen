package com.huchengzhen.cohen;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CohenApplicationTests {

    @Test
    void contextLoads() {
        // fail
        Assertions.assertThat(1).isEqualTo(0);
    }
}
