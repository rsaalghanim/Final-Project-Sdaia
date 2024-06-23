package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement
public class MedicalReportsDto {

    private int medReportsId;
    private int patientId;
    private String details;
    private LocalDate reportDate;

    private ArrayList<LinkDto> links = new ArrayList<>();


    public MedicalReportsDto() {
    }

    public MedicalReportsDto(int medReportsId, int patientId, String details, LocalDate reportDate) {
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

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    @XmlElementWrapper
    @XmlElement(name = "link")
    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public MedicalReportsDto(ResultSet rs) throws SQLException {
        medReportsId = rs.getInt("medReportsId");
        patientId = rs.getInt("patientId");
        details = rs.getString("details");
        reportDate = rs.getDate("reportDate").toLocalDate();
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
