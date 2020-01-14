package com.huchengzhen.cohen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {
    //中文、英文、数字包括下划线和减号
//    private static Pattern userNamePattern = Pattern.compile("^[\\u4E00-\\u9FA5A-Za-z0-9_-]{4,}$");

    //长度为4-50的所有字符
    private static Pattern userNamePattern = Pattern.compile("^.{4,50}$");

    //只允许英文字母、数字、下划线、英文句号、以及中划线组成
    private static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    public static boolean isValidUsername(String username) {
        Matcher matcher = userNamePattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
