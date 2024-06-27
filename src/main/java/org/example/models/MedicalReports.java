package org.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalReports {

    private int medReportsId;
    private int patientId;
    private String details;
    private String reportDate;

    public MedicalReports() {
    }

    public MedicalReports(int medReportsId, int patientId, String details, String reportDate) {
        this.medReportsId = medReportsId;
        this.patientId = patientId;
        this.details = details;
        this.reportDate = reportDate;
    }

    public int getMedReportsId() {
        return medReportsId;
    }

    public void setMedReportsId(int medReportsId) {
        this.medReportsId = medReportsId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public MedicalReports(ResultSet rs) throws SQLException {
        medReportsId = rs.getInt("medReportsId");
        patientId = rs.getInt("patientId");
        details = rs.getString("details");
        reportDate = rs.getString("reportDate");
    }

    @Override
    public String toString() {
        return "MedicalReports{" +
                "medReportsId=" + medReportsId +
                ", patientId=" + patientId +
                ", details='" + details + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
