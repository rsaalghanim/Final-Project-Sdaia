package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateDto {
    private int doctorId;


    public RateDto (ResultSet rs ) throws SQLException {
        doctorId = rs.getInt("doctorId");
    }



    public RateDto(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public RateDto() {
    }

    @Override
    public String toString() {
        return "Consultations{" +
                "doctorId=" + doctorId +
                '}';
    }
}
