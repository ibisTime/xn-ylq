package com.cdkj.ylq.domain;


public class InfoZqzn {

    ZqznInfoFront zqznInfoFront;

    ZqznInfoBack zqznInfoBack;

    ZqznInfoRealAuth zqznInfoRealAuth;

    private String frontImage;

    private String backImage;

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public ZqznInfoFront getZqznInfoFront() {
        return zqznInfoFront;
    }

    public void setZqznInfoFront(ZqznInfoFront zqznInfoFront) {
        this.zqznInfoFront = zqznInfoFront;
    }

    public ZqznInfoBack getZqznInfoBack() {
        return zqznInfoBack;
    }

    public void setZqznInfoBack(ZqznInfoBack zqznInfoBack) {
        this.zqznInfoBack = zqznInfoBack;
    }

    public ZqznInfoRealAuth getZqznInfoRealAuth() {
        return zqznInfoRealAuth;
    }

    public void setZqznInfoRealAuth(ZqznInfoRealAuth zqznInfoRealAuth) {
        this.zqznInfoRealAuth = zqznInfoRealAuth;
    }
}
