package io.ipolyzos.models;

import java.io.Serializable;

public class JEvent implements Serializable {
    private String userid;
    private long eventTime;
    private String eventType;
    private String productId;
    private String categoryId;
    private String categoryCode;
    private String brand;
    private double price;
    private String userSession;

    public JEvent() {}

    public JEvent(String userid,
                  long eventTime,
                  String eventType,
                  String productId,
                  String categoryId,
                  String categoryCode,
                  String brand,
                  double price,
                  String userSession) {
        this.userid = userid;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.productId = productId;
        this.categoryId = categoryId;
        this.categoryCode = categoryCode;
        this.brand = brand;
        this.price = price;
        this.userSession = userSession;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserSession() {
        return userSession;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }

    @Override
    public String toString() {
        return "JEvent{" +
                "userid='" + userid + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", eventType='" + eventType + '\'' +
                ", productId='" + productId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", userSession='" + userSession + '\'' +
                '}';
    }
}