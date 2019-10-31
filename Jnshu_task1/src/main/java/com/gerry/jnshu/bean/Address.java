package com.gerry.jnshu.bean;

public class Address {
    private String province;
    private String city;
    private String distinct;

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
