package com.example.springboot_security_review.eunms;

public enum ProductStatus {
    SOLDOUT("품절"),
    SELLING("판매중"),
    STOP("판매중단");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getKey(){
        return name();
    }

    public String getValue(){
        return this.value;
    }

}
