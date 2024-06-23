package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class MedReportsFilterDto {

    @QueryParam("pId") Integer pId;
    @QueryParam("MedDet") String MedDet;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getMedDet() {
        return MedDet;
    }

    public void setMedDet(String medDet) {
        MedDet = medDet;
    }
}
