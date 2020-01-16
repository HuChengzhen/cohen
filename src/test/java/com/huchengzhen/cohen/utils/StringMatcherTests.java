package com.huchengzhen.cohen.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringMatcherTests {

    @Test
    public void usernameTests() {
        assertThat(StringMatcher.isValidUsername("hcz")).isFalse();
        assertThat(StringMatcher.isValidUsername("1_中文asdf")).isTrue();
        assertThat(StringMatcher.isValidUsername("1234")).isTrue();
        assertThat(StringMatcher.isValidUsername("123")).isFalse();
        assertThat(StringMatcher.isValidUsername("一二三四")).isTrue();
        assertThat(StringMatcher.isValidUsername("一二三")).isFalse();
        String longString = "12345678901234567890123456789012345678901234567890";
        assertThat(longString.length()).isEqualTo(50);
        assertThat(StringMatcher.isValidUsername(longString)).isTrue();
        assertThat(StringMatcher.isValidUsername(longString + "0")).isFalse();
    }

    @Test
    public void emailTests() {
        assertThat(StringMatcher.isValidEmail("hcz1995@qq.com")).isTrue();
        assertThat(StringMatcher.isValidEmail("hcz1995@@qq.com")).isFalse();
        assertThat(StringMatcher.isValidEmail("@qq.com")).isFalse();
        assertThat(StringMatcher.isValidEmail("zczcv@qq")).isFalse();
    }

    @Test
    public void passwordTests() {
        assertThat(StringMatcher.isValidPassword("123456")).isTrue();
        assertThat(StringMatcher.isValidPassword("12345")).isFalse();
        String longString = "123456789012345678901234567890";
        assertThat(longString.length()).isEqualTo(30);
        assertThat(StringMatcher.isValidPassword(longString)).isTrue();
        assertThat(StringMatcher.isValidPassword(longString + "0")).isFalse();


    }
}
