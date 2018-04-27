package com.young.entity;

import javax.persistence.*;

@Entity
@Table(name = "table_copy_copy_copy", schema = "teachers2", catalog = "")
public class TableCopyCopyCopy {
    private long tId;
    private String tName;
    private String tIdCard;
    private String tChinaQ;
    private String tChinaW;
    private String tChinaE;
    private String tWorldQ;
    private String tWorldQE;
    private String tWorldW;
    private String tWorldWE;
    private String tWorldE;
    private String tWorldEE;
    private String tPhone;
    private String tMail;
    private String tWho;
    private String tZuoji;
    private String tAddress;
    private String tEms;
    private Integer tIfGo;
    private String tGoReason;
    private Integer tIfQOk=-1;
    private Integer tIfWOk=-1;
    private Integer tIfEOk=-1;
    private String tQNoReason;
    private String tWNoReason;
    private String tENoReason;
    private String tNumber;
    private String tDateY;
    private String tDateM;
    private String tDateD;
    private String tStartY;
    private String tStartM;
    private String tStartD;
    private String tEndY;
    private String tEndM;
    private String tEndD;
    private Long uId;
    private Integer tIfTemp;
    private String tUserInputYear;

    @Id
    @Column(name = "t_id", nullable = false)
    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    @Basic
    @Column(name = "t_name", nullable = true, length = 255)
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Basic
    @Column(name = "t_id_card", nullable = true, length = 255)
    public String gettIdCard() {
        return tIdCard;
    }

    public void settIdCard(String tIdCard) {
        this.tIdCard = tIdCard;
    }

    @Basic
    @Column(name = "t_china_q", nullable = true, length = 255)
    public String gettChinaQ() {
        return tChinaQ;
    }

    public void settChinaQ(String tChinaQ) {
        this.tChinaQ = tChinaQ;
    }

    @Basic
    @Column(name = "t_china_w", nullable = true, length = 255)
    public String gettChinaW() {
        return tChinaW;
    }

    public void settChinaW(String tChinaW) {
        this.tChinaW = tChinaW;
    }

    @Basic
    @Column(name = "t_china_e", nullable = true, length = 255)
    public String gettChinaE() {
        return tChinaE;
    }

    public void settChinaE(String tChinaE) {
        this.tChinaE = tChinaE;
    }

    @Basic
    @Column(name = "t_world_q", nullable = true, length = 255)
    public String gettWorldQ() {
        return tWorldQ;
    }

    public void settWorldQ(String tWorldQ) {
        this.tWorldQ = tWorldQ;
    }

    @Basic
    @Column(name = "t_world_q_e", nullable = true, length = 255)
    public String gettWorldQE() {
        return tWorldQE;
    }

    public void settWorldQE(String tWorldQE) {
        this.tWorldQE = tWorldQE;
    }

    @Basic
    @Column(name = "t_world_w", nullable = true, length = 255)
    public String gettWorldW() {
        return tWorldW;
    }

    public void settWorldW(String tWorldW) {
        this.tWorldW = tWorldW;
    }

    @Basic
    @Column(name = "t_world_w_e", nullable = true, length = 255)
    public String gettWorldWE() {
        return tWorldWE;
    }

    public void settWorldWE(String tWorldWE) {
        this.tWorldWE = tWorldWE;
    }

    @Basic
    @Column(name = "t_world_e", nullable = true, length = 255)
    public String gettWorldE() {
        return tWorldE;
    }

    public void settWorldE(String tWorldE) {
        this.tWorldE = tWorldE;
    }

    @Basic
    @Column(name = "t_world_e_e", nullable = true, length = 255)
    public String gettWorldEE() {
        return tWorldEE;
    }

    public void settWorldEE(String tWorldEE) {
        this.tWorldEE = tWorldEE;
    }

    @Basic
    @Column(name = "t_phone", nullable = true, length = 255)
    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    @Basic
    @Column(name = "t_mail", nullable = true, length = 255)
    public String gettMail() {
        return tMail;
    }

    public void settMail(String tMail) {
        this.tMail = tMail;
    }

    @Basic
    @Column(name = "t_who", nullable = true, length = 255)
    public String gettWho() {
        return tWho;
    }

    public void settWho(String tWho) {
        this.tWho = tWho;
    }

    @Basic
    @Column(name = "t_zuoji", nullable = true, length = 255)
    public String gettZuoji() {
        return tZuoji;
    }

    public void settZuoji(String tZuoji) {
        this.tZuoji = tZuoji;
    }

    @Basic
    @Column(name = "t_address", nullable = true, length = 255)
    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    @Basic
    @Column(name = "t_ems", nullable = true, length = 255)
    public String gettEms() {
        return tEms;
    }

    public void settEms(String tEms) {
        this.tEms = tEms;
    }

    @Basic
    @Column(name = "t_if_go", nullable = true)
    public Integer gettIfGo() {
        return tIfGo;
    }

    public void settIfGo(Integer tIfGo) {
        this.tIfGo = tIfGo;
    }

    @Basic
    @Column(name = "t_go_reason", nullable = true, length = 255)
    public String gettGoReason() {
        return tGoReason;
    }

    public void settGoReason(String tGoReason) {
        this.tGoReason = tGoReason;
    }

    @Basic
    @Column(name = "t_if_q_ok", nullable = true)
    public Integer gettIfQOk() {
        return tIfQOk;
    }

    public void settIfQOk(Integer tIfQOk) {
        this.tIfQOk = tIfQOk;
    }

    @Basic
    @Column(name = "t_if_w_ok", nullable = true)
    public Integer gettIfWOk() {
        return tIfWOk;
    }

    public void settIfWOk(Integer tIfWOk) {
        this.tIfWOk = tIfWOk;
    }

    @Basic
    @Column(name = "t_if_e_ok", nullable = true)
    public Integer gettIfEOk() {
        return tIfEOk;
    }

    public void settIfEOk(Integer tIfEOk) {
        this.tIfEOk = tIfEOk;
    }

    @Basic
    @Column(name = "t_q_no_reason", nullable = true, length = 255)
    public String gettQNoReason() {
        return tQNoReason;
    }

    public void settQNoReason(String tQNoReason) {
        this.tQNoReason = tQNoReason;
    }

    @Basic
    @Column(name = "t_w_no_reason", nullable = true, length = 255)
    public String gettWNoReason() {
        return tWNoReason;
    }

    public void settWNoReason(String tWNoReason) {
        this.tWNoReason = tWNoReason;
    }

    @Basic
    @Column(name = "t_e_no_reason", nullable = true, length = 255)
    public String gettENoReason() {
        return tENoReason;
    }

    public void settENoReason(String tENoReason) {
        this.tENoReason = tENoReason;
    }

    @Basic
    @Column(name = "t_number", nullable = true, length = 255)
    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    @Basic
    @Column(name = "t_date_y", nullable = true, length = 255)
    public String gettDateY() {
        return tDateY;
    }

    public void settDateY(String tDateY) {
        this.tDateY = tDateY;
    }

    @Basic
    @Column(name = "t_date_m", nullable = true, length = 255)
    public String gettDateM() {
        return tDateM;
    }

    public void settDateM(String tDateM) {
        this.tDateM = tDateM;
    }

    @Basic
    @Column(name = "t_date_d", nullable = true, length = 255)
    public String gettDateD() {
        return tDateD;
    }

    public void settDateD(String tDateD) {
        this.tDateD = tDateD;
    }

    @Basic
    @Column(name = "t_start_y", nullable = true, length = 255)
    public String gettStartY() {
        return tStartY;
    }

    public void settStartY(String tStartY) {
        this.tStartY = tStartY;
    }

    @Basic
    @Column(name = "t_start_m", nullable = true, length = 255)
    public String gettStartM() {
        return tStartM;
    }

    public void settStartM(String tStartM) {
        this.tStartM = tStartM;
    }

    @Basic
    @Column(name = "t_start_d", nullable = true, length = 255)
    public String gettStartD() {
        return tStartD;
    }

    public void settStartD(String tStartD) {
        this.tStartD = tStartD;
    }

    @Basic
    @Column(name = "t_end_y", nullable = true, length = 255)
    public String gettEndY() {
        return tEndY;
    }

    public void settEndY(String tEndY) {
        this.tEndY = tEndY;
    }

    @Basic
    @Column(name = "t_end_m", nullable = true, length = 255)
    public String gettEndM() {
        return tEndM;
    }

    public void settEndM(String tEndM) {
        this.tEndM = tEndM;
    }

    @Basic
    @Column(name = "t_end_d", nullable = true, length = 255)
    public String gettEndD() {
        return tEndD;
    }

    public void settEndD(String tEndD) {
        this.tEndD = tEndD;
    }

    @Basic
    @Column(name = "u_id", nullable = true)
    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "t_if_temp", nullable = true)
    public Integer gettIfTemp() {
        return tIfTemp;
    }

    public void settIfTemp(Integer tIfTemp) {
        this.tIfTemp = tIfTemp;
    }

    @Basic
    @Column(name = "t_user_input_year", nullable = true, length = 255)
    public String gettUserInputYear() {
        return tUserInputYear;
    }

    public void settUserInputYear(String tUserInputYear) {
        this.tUserInputYear = tUserInputYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableCopyCopyCopy that = (TableCopyCopyCopy) o;

        if (tId != that.tId) return false;
        if (tName != null ? !tName.equals(that.tName) : that.tName != null) return false;
        if (tIdCard != null ? !tIdCard.equals(that.tIdCard) : that.tIdCard != null) return false;
        if (tChinaQ != null ? !tChinaQ.equals(that.tChinaQ) : that.tChinaQ != null) return false;
        if (tChinaW != null ? !tChinaW.equals(that.tChinaW) : that.tChinaW != null) return false;
        if (tChinaE != null ? !tChinaE.equals(that.tChinaE) : that.tChinaE != null) return false;
        if (tWorldQ != null ? !tWorldQ.equals(that.tWorldQ) : that.tWorldQ != null) return false;
        if (tWorldQE != null ? !tWorldQE.equals(that.tWorldQE) : that.tWorldQE != null) return false;
        if (tWorldW != null ? !tWorldW.equals(that.tWorldW) : that.tWorldW != null) return false;
        if (tWorldWE != null ? !tWorldWE.equals(that.tWorldWE) : that.tWorldWE != null) return false;
        if (tWorldE != null ? !tWorldE.equals(that.tWorldE) : that.tWorldE != null) return false;
        if (tWorldEE != null ? !tWorldEE.equals(that.tWorldEE) : that.tWorldEE != null) return false;
        if (tPhone != null ? !tPhone.equals(that.tPhone) : that.tPhone != null) return false;
        if (tMail != null ? !tMail.equals(that.tMail) : that.tMail != null) return false;
        if (tWho != null ? !tWho.equals(that.tWho) : that.tWho != null) return false;
        if (tZuoji != null ? !tZuoji.equals(that.tZuoji) : that.tZuoji != null) return false;
        if (tAddress != null ? !tAddress.equals(that.tAddress) : that.tAddress != null) return false;
        if (tEms != null ? !tEms.equals(that.tEms) : that.tEms != null) return false;
        if (tIfGo != null ? !tIfGo.equals(that.tIfGo) : that.tIfGo != null) return false;
        if (tGoReason != null ? !tGoReason.equals(that.tGoReason) : that.tGoReason != null) return false;
        if (tIfQOk != null ? !tIfQOk.equals(that.tIfQOk) : that.tIfQOk != null) return false;
        if (tIfWOk != null ? !tIfWOk.equals(that.tIfWOk) : that.tIfWOk != null) return false;
        if (tIfEOk != null ? !tIfEOk.equals(that.tIfEOk) : that.tIfEOk != null) return false;
        if (tQNoReason != null ? !tQNoReason.equals(that.tQNoReason) : that.tQNoReason != null) return false;
        if (tWNoReason != null ? !tWNoReason.equals(that.tWNoReason) : that.tWNoReason != null) return false;
        if (tENoReason != null ? !tENoReason.equals(that.tENoReason) : that.tENoReason != null) return false;
        if (tNumber != null ? !tNumber.equals(that.tNumber) : that.tNumber != null) return false;
        if (tDateY != null ? !tDateY.equals(that.tDateY) : that.tDateY != null) return false;
        if (tDateM != null ? !tDateM.equals(that.tDateM) : that.tDateM != null) return false;
        if (tDateD != null ? !tDateD.equals(that.tDateD) : that.tDateD != null) return false;
        if (tStartY != null ? !tStartY.equals(that.tStartY) : that.tStartY != null) return false;
        if (tStartM != null ? !tStartM.equals(that.tStartM) : that.tStartM != null) return false;
        if (tStartD != null ? !tStartD.equals(that.tStartD) : that.tStartD != null) return false;
        if (tEndY != null ? !tEndY.equals(that.tEndY) : that.tEndY != null) return false;
        if (tEndM != null ? !tEndM.equals(that.tEndM) : that.tEndM != null) return false;
        if (tEndD != null ? !tEndD.equals(that.tEndD) : that.tEndD != null) return false;
        if (uId != null ? !uId.equals(that.uId) : that.uId != null) return false;
        if (tIfTemp != null ? !tIfTemp.equals(that.tIfTemp) : that.tIfTemp != null) return false;
        if (tUserInputYear != null ? !tUserInputYear.equals(that.tUserInputYear) : that.tUserInputYear != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tId ^ (tId >>> 32));
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        result = 31 * result + (tIdCard != null ? tIdCard.hashCode() : 0);
        result = 31 * result + (tChinaQ != null ? tChinaQ.hashCode() : 0);
        result = 31 * result + (tChinaW != null ? tChinaW.hashCode() : 0);
        result = 31 * result + (tChinaE != null ? tChinaE.hashCode() : 0);
        result = 31 * result + (tWorldQ != null ? tWorldQ.hashCode() : 0);
        result = 31 * result + (tWorldQE != null ? tWorldQE.hashCode() : 0);
        result = 31 * result + (tWorldW != null ? tWorldW.hashCode() : 0);
        result = 31 * result + (tWorldWE != null ? tWorldWE.hashCode() : 0);
        result = 31 * result + (tWorldE != null ? tWorldE.hashCode() : 0);
        result = 31 * result + (tWorldEE != null ? tWorldEE.hashCode() : 0);
        result = 31 * result + (tPhone != null ? tPhone.hashCode() : 0);
        result = 31 * result + (tMail != null ? tMail.hashCode() : 0);
        result = 31 * result + (tWho != null ? tWho.hashCode() : 0);
        result = 31 * result + (tZuoji != null ? tZuoji.hashCode() : 0);
        result = 31 * result + (tAddress != null ? tAddress.hashCode() : 0);
        result = 31 * result + (tEms != null ? tEms.hashCode() : 0);
        result = 31 * result + (tIfGo != null ? tIfGo.hashCode() : 0);
        result = 31 * result + (tGoReason != null ? tGoReason.hashCode() : 0);
        result = 31 * result + (tIfQOk != null ? tIfQOk.hashCode() : 0);
        result = 31 * result + (tIfWOk != null ? tIfWOk.hashCode() : 0);
        result = 31 * result + (tIfEOk != null ? tIfEOk.hashCode() : 0);
        result = 31 * result + (tQNoReason != null ? tQNoReason.hashCode() : 0);
        result = 31 * result + (tWNoReason != null ? tWNoReason.hashCode() : 0);
        result = 31 * result + (tENoReason != null ? tENoReason.hashCode() : 0);
        result = 31 * result + (tNumber != null ? tNumber.hashCode() : 0);
        result = 31 * result + (tDateY != null ? tDateY.hashCode() : 0);
        result = 31 * result + (tDateM != null ? tDateM.hashCode() : 0);
        result = 31 * result + (tDateD != null ? tDateD.hashCode() : 0);
        result = 31 * result + (tStartY != null ? tStartY.hashCode() : 0);
        result = 31 * result + (tStartM != null ? tStartM.hashCode() : 0);
        result = 31 * result + (tStartD != null ? tStartD.hashCode() : 0);
        result = 31 * result + (tEndY != null ? tEndY.hashCode() : 0);
        result = 31 * result + (tEndM != null ? tEndM.hashCode() : 0);
        result = 31 * result + (tEndD != null ? tEndD.hashCode() : 0);
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        result = 31 * result + (tIfTemp != null ? tIfTemp.hashCode() : 0);
        result = 31 * result + (tUserInputYear != null ? tUserInputYear.hashCode() : 0);
        return result;
    }
}
