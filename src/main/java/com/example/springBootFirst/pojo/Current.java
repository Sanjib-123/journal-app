package com.example.springBootFirst.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Current {
    private int temperature;
    @JsonProperty("weather_descriptions")
    private String[] weatherDescriptions;
    @JsonProperty("weather_icons")
    private String[] weatherIcons;
    private int precip;
    private int windspeed;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    private int pressure;
    private int humidity;
    @JsonProperty("cloudcover")
    private int cloudcover;
    @JsonProperty("feelslike")
    private int feelslike;
    @JsonProperty("uv_index")
    private int uvIndex;
    private int visibility;
    @JsonProperty("is_day")
    private String isDay;

    // Getters and setters
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String[] getWeatherDescriptions() {
        return weatherDescriptions;
    }

    public void setWeatherDescriptions(String[] weatherDescriptions) {
        this.weatherDescriptions = weatherDescriptions;
    }

    public String[] getWeatherIcons() {
        return weatherIcons;
    }

    public void setWeatherIcons(String[] weatherIcons) {
        this.weatherIcons = weatherIcons;
    }

    public int getPrecip() {
        return precip;
    }

    public void setPrecip(int precip) {
        this.precip = precip;
    }

    public int getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(int windspeed) {
        this.windspeed = windspeed;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(int cloudcover) {
        this.cloudcover = cloudcover;
    }

    public int getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(int feelslike) {
        this.feelslike = feelslike;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getIsDay() {
        return isDay;
    }

    public void setIsDay(String isDay) {
        this.isDay = isDay;
    }

}
