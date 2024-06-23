package org.example.dto;

import jakarta.ws.rs.QueryParam;

import java.time.LocalDateTime;

public class ConsultFilterDto {

    @QueryParam("diagnose") String diagnose;
    @QueryParam("rate") Integer rate;
    @QueryParam("pendingReq") LocalDateTime pendingReq;
    @QueryParam("stat") String stat;
    @QueryParam("docId") Integer docId;

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public LocalDateTime getPendingReq() {
        return pendingReq;
    }

    public void setPendingReq(LocalDateTime pendingReq) {
        this.pendingReq = pendingReq;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }
}


