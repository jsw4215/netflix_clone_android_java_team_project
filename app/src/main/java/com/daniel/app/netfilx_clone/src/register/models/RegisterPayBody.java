package com.daniel.app.netfilx_clone.src.register.models;

import com.google.gson.annotations.SerializedName;

public class RegisterPayBody {

    @SerializedName("cardNum")
    private String cardNum;

    @SerializedName("expYear")
    private int expYear;

    @SerializedName("expMonth")
    private int expMonth;

    @SerializedName("name")
    private String name;

    @SerializedName("bDay")
    private int bDay;

    @SerializedName("bMonth")
    private int bMonth;

    @SerializedName("bYear")
    private int bYear;

    public RegisterPayBody() {

    }

    public RegisterPayBody(String cardNum, int expYear, int expMonth, String name, int bDay, int bMonth, int bYear) {
        this.cardNum = cardNum;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.name = name;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getbDay() {
        return bDay;
    }

    public void setbDay(int bDay) {
        this.bDay = bDay;
    }

    public int getbMonth() {
        return bMonth;
    }

    public void setbMonth(int bMonth) {
        this.bMonth = bMonth;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }
}
