package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@XmlRootElement
public class ConsultationsDto {
  // private int consultId ;
    private int doctorId ;
   private int patientId ;
   private LocalDateTime requestTime;
   private LocalDateTime consultationTime ;
   private String status ;
   private String diagnosis;
   private int rateDoctor ;

  //  private ArrayList<LinkDto> links = new ArrayList<>();

    public ConsultationsDto() {
    }

//    public ConsultationsDto(int consultId, int doctorId, int patientId, LocalDateTime requestTime, LocalDateTime consultationTime, String status, String diagnosis, int rateDoctor) {
//        this.consultId = consultId;
//        this.doctorId = doctorId;
//        this.patientId = patientId;
//        this.requestTime = requestTime;
//        this.consultationTime = consultationTime;
//        this.status = status;
//        this.diagnosis = diagnosis;
//        this.rateDoctor = rateDoctor;
//    }

//    public ConsultationsDto(int doctorId, LocalDateTime requestTime, LocalDateTime consultationTime, String status, String diagnosis, int rateDoctor, ArrayList<LinkDto> links) {
//        this.doctorId = doctorId;
//        this.requestTime = requestTime;
//        this.consultationTime = consultationTime;
//        this.status = status;
//        this.diagnosis = diagnosis;
//        this.rateDoctor = rateDoctor;
//        this.links = links;
//    }

    public ConsultationsDto(int doctorId, int patientId, LocalDateTime requestTime, LocalDateTime consultationTime, String status, String diagnosis, int rateDoctor) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.requestTime = requestTime;
        this.consultationTime = consultationTime;
        this.status = status;
        this.diagnosis = diagnosis;
        this.rateDoctor = rateDoctor;
    }


//    public int getConsultId() {
//        return consultId;
//    }
//
//    public void setConsultId(int consultId) {
//        this.consultId = consultId;
//    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public LocalDateTime getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(LocalDateTime consultationTime) {
        this.consultationTime = consultationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getRateDoctor() {
        return rateDoctor;
    }

    public void setRateDoctor(int rateDoctor) {
        this.rateDoctor = rateDoctor;
    }

//    public ArrayList<LinkDto> getLinks() {
//        return links;
//    }


//    @XmlElementWrapper
//    @XmlElement(name = "link")
//    public void addLink(String url, String rel) {
//        LinkDto link = new LinkDto();
//        link.setLink(url);
//        link.setRel(rel);
//        links.add(link);
//    }

    public ConsultationsDto(ResultSet rs) throws SQLException {
      //  consultId = rs.getInt("consultId");
        doctorId = rs.getInt("doctorId");
        patientId = rs.getInt("patientId");
        requestTime = LocalDateTime.parse(rs.getString("requestTime"));
        if(rs.getString("consultationTime").equals("")){
            consultationTime = null;
        }else {
            consultationTime = LocalDateTime.parse(rs.getString("consultationTime"));
        }
        status = rs.getString("status");
        diagnosis = rs.getString("diagnosis");
        rateDoctor=rs.getInt("rateDoctor");
    }

    @Override
    public String toString() {
        return "Consultations{" +
      //          "consultId=" + consultId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", requestTime=" + requestTime +
                ", consultationTime=" + consultationTime +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", rateDoctor=" + rateDoctor +
                '}';
    }
}
