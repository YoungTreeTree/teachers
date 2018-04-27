package com.young.vo;

public class TableVo
{
    private long tId;
    private Integer tIfTemp;
    private Integer tIfQOk;
    private Integer tIfWOk;
    private Integer tIfEOk;
    private String tUserInputYear;

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public Integer gettIfTemp() {
        return tIfTemp;
    }

    public void settIfTemp(Integer tIfTemp) {
        this.tIfTemp = tIfTemp;
    }

    public Integer gettIfQOk() {
        return tIfQOk;
    }

    public void settIfQOk(Integer tIfQOk) {
        this.tIfQOk = tIfQOk;
    }

    public Integer gettIfWOk() {
        return tIfWOk;
    }

    public void settIfWOk(Integer tIfWOk) {
        this.tIfWOk = tIfWOk;
    }

    public Integer gettIfEOk() {
        return tIfEOk;
    }

    public void settIfEOk(Integer tIfEOk) {
        this.tIfEOk = tIfEOk;
    }

    public String gettUserInputYear() {
        return tUserInputYear;
    }

    public void settUserInputYear(String tUserInputYear) {
        this.tUserInputYear = tUserInputYear;
    }
}
