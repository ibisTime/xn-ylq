package com.cdkj.ylq.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class ZqznInfoFront {

    @JSONField(name = "id_no")
    private String idNo;

    private String address;

    private String gender;

    private String race;

    private String name;

    private String birth;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "ZqznInfoFront{" +
                "idNo='" + idNo + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
