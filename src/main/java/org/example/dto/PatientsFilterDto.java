package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class PatientsFilterDto {

    @QueryParam("patName") String patName;
    @QueryParam("patPhone") String patPhone;

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatPhone() {
        return patPhone;
    }

    public void setPatPhone(String patPhone) {
        this.patPhone = patPhone;
    }
}
