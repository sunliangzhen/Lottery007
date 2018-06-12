/**
  * Copyright 2018 bejson.com 
  */
package com.bxvip.lottery007.bean.json;

public class JSON_Weather {

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private WeatherData data;
    public void setDate(String date) {
         this.date = date;
     }
     public String getDate() {
         return date;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setData(WeatherData data) {
         this.data = data;
     }
     public WeatherData getData() {
         return data;
     }

}