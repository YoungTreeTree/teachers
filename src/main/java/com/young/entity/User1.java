package com.young.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User1 {
    private long u1Id;
    private String u1CodeQ;
    private String u1CodeW;
    private String u1CodeE;
    private String u1Pw;
    private Integer u1Type;
    private String u1ScName;
    private String u1UName;
    private String u1Phone;
    private String u1Email;
    private String u1Address;
    private Integer u1States;

    @Id
    @Column(name = "u1_id", nullable = false)
    public long getU1Id() {
        return u1Id;
    }

    public void setU1Id(long u1Id) {
        this.u1Id = u1Id;
    }

    @Basic
    @Column(name = "u1_code_q", nullable = true, length = 255)
    public String getU1CodeQ() {
        return u1CodeQ;
    }

    public void setU1CodeQ(String u1CodeQ) {
        this.u1CodeQ = u1CodeQ;
    }

    @Basic
    @Column(name = "u1_code_w", nullable = true, length = 255)
    public String getU1CodeW() {
        return u1CodeW;
    }

    public void setU1CodeW(String u1CodeW) {
        this.u1CodeW = u1CodeW;
    }

    @Basic
    @Column(name = "u1_code_e", nullable = true, length = 255)
    public String getU1CodeE() {
        return u1CodeE;
    }

    public void setU1CodeE(String u1CodeE) {
        this.u1CodeE = u1CodeE;
    }

    @Basic
    @Column(name = "u1_pw", nullable = true, length = 255)
    public String getU1Pw() {
        return u1Pw;
    }

    public void setU1Pw(String u1Pw) {
        this.u1Pw = u1Pw;
    }

    @Basic
    @Column(name = "u1_type", nullable = true)
    public Integer getU1Type() {
        return u1Type;
    }

    public void setU1Type(Integer u1Type) {
        this.u1Type = u1Type;
    }

    @Basic
    @Column(name = "u1_sc_name", nullable = true, length = 255)
    public String getU1ScName() {
        return u1ScName;
    }

    public void setU1ScName(String u1ScName) {
        this.u1ScName = u1ScName;
    }

    @Basic
    @Column(name = "u1_u_name", nullable = true, length = 255)
    public String getU1UName() {
        return u1UName;
    }

    public void setU1UName(String u1UName) {
        this.u1UName = u1UName;
    }

    @Basic
    @Column(name = "u1_phone", nullable = true, length = 255)
    public String getU1Phone() {
        return u1Phone;
    }

    public void setU1Phone(String u1Phone) {
        this.u1Phone = u1Phone;
    }

    @Basic
    @Column(name = "u1_email", nullable = true, length = 255)
    public String getU1Email() {
        return u1Email;
    }

    public void setU1Email(String u1Email) {
        this.u1Email = u1Email;
    }

    @Basic
    @Column(name = "u1_address", nullable = true, length = 255)
    public String getU1Address() {
        return u1Address;
    }

    public void setU1Address(String u1Address) {
        this.u1Address = u1Address;
    }

    @Basic
    @Column(name = "u1_states", nullable = true)
    public Integer getU1States() {
        return u1States;
    }

    public void setU1States(Integer u1States) {
        this.u1States = u1States;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User1 user1 = (User1) o;

        if (u1Id != user1.u1Id) return false;
        if (u1CodeQ != null ? !u1CodeQ.equals(user1.u1CodeQ) : user1.u1CodeQ != null) return false;
        if (u1CodeW != null ? !u1CodeW.equals(user1.u1CodeW) : user1.u1CodeW != null) return false;
        if (u1CodeE != null ? !u1CodeE.equals(user1.u1CodeE) : user1.u1CodeE != null) return false;
        if (u1Pw != null ? !u1Pw.equals(user1.u1Pw) : user1.u1Pw != null) return false;
        if (u1Type != null ? !u1Type.equals(user1.u1Type) : user1.u1Type != null) return false;
        if (u1ScName != null ? !u1ScName.equals(user1.u1ScName) : user1.u1ScName != null) return false;
        if (u1UName != null ? !u1UName.equals(user1.u1UName) : user1.u1UName != null) return false;
        if (u1Phone != null ? !u1Phone.equals(user1.u1Phone) : user1.u1Phone != null) return false;
        if (u1Email != null ? !u1Email.equals(user1.u1Email) : user1.u1Email != null) return false;
        if (u1Address != null ? !u1Address.equals(user1.u1Address) : user1.u1Address != null) return false;
        if (u1States != null ? !u1States.equals(user1.u1States) : user1.u1States != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (u1Id ^ (u1Id >>> 32));
        result = 31 * result + (u1CodeQ != null ? u1CodeQ.hashCode() : 0);
        result = 31 * result + (u1CodeW != null ? u1CodeW.hashCode() : 0);
        result = 31 * result + (u1CodeE != null ? u1CodeE.hashCode() : 0);
        result = 31 * result + (u1Pw != null ? u1Pw.hashCode() : 0);
        result = 31 * result + (u1Type != null ? u1Type.hashCode() : 0);
        result = 31 * result + (u1ScName != null ? u1ScName.hashCode() : 0);
        result = 31 * result + (u1UName != null ? u1UName.hashCode() : 0);
        result = 31 * result + (u1Phone != null ? u1Phone.hashCode() : 0);
        result = 31 * result + (u1Email != null ? u1Email.hashCode() : 0);
        result = 31 * result + (u1Address != null ? u1Address.hashCode() : 0);
        result = 31 * result + (u1States != null ? u1States.hashCode() : 0);
        return result;
    }
}
