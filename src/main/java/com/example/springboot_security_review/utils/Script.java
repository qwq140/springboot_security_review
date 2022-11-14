package com.example.springboot_security_review.utils;

public class Script {

    // 경고창을 띄우고 이전페이지로 이동
    public static String back(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+msg+"');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

}
