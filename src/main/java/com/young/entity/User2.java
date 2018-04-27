package com.young.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User2 {
    private long u2Id;
    private String u2CodeQ;
    private String u2CodeW;
    private String u2CodeE;
    private String u2CodePw;
    private Integer u2CodeType;
    private String u2Name;
    private String u2Phone;
    private String u2Address;
    private String u2IdCode;
    private String u2Mail;
    private Integer u2States;

    @Id
    @Column(name = "u2_id", nullable = false)
    public long getU2Id() {
        return u2Id;
    }

    public void setU2Id(long u2Id) {
        this.u2Id = u2Id;
    }

    @Basic
    @Column(name = "u2_code_q", nullable = true, length = 255)
    public String getU2CodeQ() {
        return u2CodeQ;
    }

    public void setU2CodeQ(String u2CodeQ) {
        this.u2CodeQ = u2CodeQ;
    }

    @Basic
    @Column(name = "u2_code_w", nullable = true, length = 255)
    public String getU2CodeW() {
        return u2CodeW;
    }

    public void setU2CodeW(String u2CodeW) {
        this.u2CodeW = u2CodeW;
    }

    @Basic
    @Column(name = "u2_code_e", nullable = true, length = 255)
    public String getU2CodeE() {
        return u2CodeE;
    }

    public void setU2CodeE(String u2CodeE) {
        this.u2CodeE = u2CodeE;
    }

    @Basic
    @Column(name = "u2_code_pw", nullable = true, length = 255)
    public String getU2CodePw() {
        return u2CodePw;
    }

    public void setU2CodePw(String u2CodePw) {
        this.u2CodePw = u2CodePw;
    }

    @Basic
    @Column(name = "u2_code_type", nullable = true)
    public Integer getU2CodeType() {
        return u2CodeType;
    }

    public void setU2CodeType(Integer u2CodeType) {
        this.u2CodeType = u2CodeType;
    }

    @Basic
    @Column(name = "u2_name", nullable = true, length = 255)
    public String getU2Name() {
        return u2Name;
    }

    public void setU2Name(String u2Name) {
        this.u2Name = u2Name;
    }

    @Basic
    @Column(name = "u2_phone", nullable = true, length = 255)
    public String getU2Phone() {
        return u2Phone;
    }

    public void setU2Phone(String u2Phone) {
        this.u2Phone = u2Phone;
    }

    @Basic
    @Column(name = "u2_address", nullable = true, length = 255)
    public String getU2Address() {
        return u2Address;
    }

    public void setU2Address(String u2Address) {
        this.u2Address = u2Address;
    }

    @Basic
    @Column(name = "u2_id_code", nullable = true, length = 255)
    public String getU2IdCode() {
        return u2IdCode;
    }

    public void setU2IdCode(String u2IdCode) {
        this.u2IdCode = u2IdCode;
    }

    @Basic
    @Column(name = "u2_mail", nullable = true, length = 255)
    public String getU2Mail() {
        return u2Mail;
    }

    public void setU2Mail(String u2Mail) {
        this.u2Mail = u2Mail;
    }

    @Basic
    @Column(name = "u2_states", nullable = true)
    public Integer getU2States() {
        return u2States;
    }

    public void setU2States(Integer u2States) {
        this.u2States = u2States;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User2 user2 = (User2) o;

        if (u2Id != user2.u2Id) return false;
        if (u2CodeQ != null ? !u2CodeQ.equals(user2.u2CodeQ) : user2.u2CodeQ != null) return false;
        if (u2CodeW != null ? !u2CodeW.equals(user2.u2CodeW) : user2.u2CodeW != null) return false;
        if (u2CodeE != null ? !u2CodeE.equals(user2.u2CodeE) : user2.u2CodeE != null) return false;
        if (u2CodePw != null ? !u2CodePw.equals(user2.u2CodePw) : user2.u2CodePw != null) return false;
        if (u2CodeType != null ? !u2CodeType.equals(user2.u2CodeType) : user2.u2CodeType != null) return false;
        if (u2Name != null ? !u2Name.equals(user2.u2Name) : user2.u2Name != null) return false;
        if (u2Phone != null ? !u2Phone.equals(user2.u2Phone) : user2.u2Phone != null) return false;
        if (u2Address != null ? !u2Address.equals(user2.u2Address) : user2.u2Address != null) return false;
        if (u2IdCode != null ? !u2IdCode.equals(user2.u2IdCode) : user2.u2IdCode != null) return false;
        if (u2Mail != null ? !u2Mail.equals(user2.u2Mail) : user2.u2Mail != null) return false;
        if (u2States != null ? !u2States.equals(user2.u2States) : user2.u2States != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (u2Id ^ (u2Id >>> 32));
        result = 31 * result + (u2CodeQ != null ? u2CodeQ.hashCode() : 0);
        result = 31 * result + (u2CodeW != null ? u2CodeW.hashCode() : 0);
        result = 31 * result + (u2CodeE != null ? u2CodeE.hashCode() : 0);
        result = 31 * result + (u2CodePw != null ? u2CodePw.hashCode() : 0);
        result = 31 * result + (u2CodeType != null ? u2CodeType.hashCode() : 0);
        result = 31 * result + (u2Name != null ? u2Name.hashCode() : 0);
        result = 31 * result + (u2Phone != null ? u2Phone.hashCode() : 0);
        result = 31 * result + (u2Address != null ? u2Address.hashCode() : 0);
        result = 31 * result + (u2IdCode != null ? u2IdCode.hashCode() : 0);
        result = 31 * result + (u2Mail != null ? u2Mail.hashCode() : 0);
        result = 31 * result + (u2States != null ? u2States.hashCode() : 0);
        return result;
    }
}
