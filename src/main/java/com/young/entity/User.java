package com.young.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    private long uId;
    private String uCodeQ;
    private String uCodeW;
    private String uCodeE;
    private String uPw;
    private Integer uType;
    private String uName;
    private String uV2Name;
    private String uV2Phone;
    private String uV2Email;

    @Id
    @Column(name = "u_id", nullable = false)
    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "u_code_q", nullable = true, length = 255)
    public String getuCodeQ() {
        return uCodeQ;
    }

    public void setuCodeQ(String uCodeQ) {
        this.uCodeQ = uCodeQ;
    }

    @Basic
    @Column(name = "u_code_w", nullable = true, length = 255)
    public String getuCodeW() {
        return uCodeW;
    }

    public void setuCodeW(String uCodeW) {
        this.uCodeW = uCodeW;
    }

    @Basic
    @Column(name = "u_code_e", nullable = true, length = 255)
    public String getuCodeE() {
        return uCodeE;
    }

    public void setuCodeE(String uCodeE) {
        this.uCodeE = uCodeE;
    }

    @Basic
    @Column(name = "u_pw", nullable = true, length = 255)
    public String getuPw() {
        return uPw;
    }

    public void setuPw(String uPw) {
        this.uPw = uPw;
    }

    @Basic
    @Column(name = "u_type", nullable = true)
    public Integer getuType() {
        return uType;
    }

    public void setuType(Integer uType) {
        this.uType = uType;
    }

    @Basic
    @Column(name = "u_name", nullable = true, length = 255)
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "u_v2_name", nullable = true, length = 255)
    public String getuV2Name() {
        return uV2Name;
    }

    public void setuV2Name(String uV2Name) {
        this.uV2Name = uV2Name;
    }

    @Basic
    @Column(name = "u_v2_phone", nullable = true, length = 255)
    public String getuV2Phone() {
        return uV2Phone;
    }

    public void setuV2Phone(String uV2Phone) {
        this.uV2Phone = uV2Phone;
    }

    @Basic
    @Column(name = "u_v2_email", nullable = true, length = 255)
    public String getuV2Email() {
        return uV2Email;
    }

    public void setuV2Email(String uV2Email) {
        this.uV2Email = uV2Email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uId != user.uId) return false;
        if (uCodeQ != null ? !uCodeQ.equals(user.uCodeQ) : user.uCodeQ != null) return false;
        if (uCodeW != null ? !uCodeW.equals(user.uCodeW) : user.uCodeW != null) return false;
        if (uCodeE != null ? !uCodeE.equals(user.uCodeE) : user.uCodeE != null) return false;
        if (uPw != null ? !uPw.equals(user.uPw) : user.uPw != null) return false;
        if (uType != null ? !uType.equals(user.uType) : user.uType != null) return false;
        if (uName != null ? !uName.equals(user.uName) : user.uName != null) return false;
        if (uV2Name != null ? !uV2Name.equals(user.uV2Name) : user.uV2Name != null) return false;
        if (uV2Phone != null ? !uV2Phone.equals(user.uV2Phone) : user.uV2Phone != null) return false;
        if (uV2Email != null ? !uV2Email.equals(user.uV2Email) : user.uV2Email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uId ^ (uId >>> 32));
        result = 31 * result + (uCodeQ != null ? uCodeQ.hashCode() : 0);
        result = 31 * result + (uCodeW != null ? uCodeW.hashCode() : 0);
        result = 31 * result + (uCodeE != null ? uCodeE.hashCode() : 0);
        result = 31 * result + (uPw != null ? uPw.hashCode() : 0);
        result = 31 * result + (uType != null ? uType.hashCode() : 0);
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (uV2Name != null ? uV2Name.hashCode() : 0);
        result = 31 * result + (uV2Phone != null ? uV2Phone.hashCode() : 0);
        result = 31 * result + (uV2Email != null ? uV2Email.hashCode() : 0);
        return result;
    }
}
