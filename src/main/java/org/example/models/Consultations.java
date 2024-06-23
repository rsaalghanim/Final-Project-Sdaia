package org.example.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Consultations {
   private int consultId ;
    private int doctorId ;
   private int patientId ;
   private String requestTime;
   private String consultationTime ;
   private String status ;
   private String diagnosis;
   private int rateDoctor ;


    public Consultations() {
    }

    public Consultations(int consultId, int doctorId, int patientId, String requestTime, String consultationTime, String status, String diagnosis, int rateDoctor) {
        this.consultId = consultId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.requestTime = requestTime;
        this.consultationTime = consultationTime;
        this.status = status;
        this.diagnosis = diagnosis;
        this.rateDoctor = rateDoctor;
    }

    public int getConsultId() {
        return consultId;
    }

    public void setConsultId(int consultId) {
        this.consultId = consultId;
    }

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

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(String consultationTime) {
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

    public Consultations(ResultSet rs) throws SQLException {
        consultId = rs.getInt("consultId");
        doctorId = rs.getInt("doctorId");
        patientId = rs.getInt("patientId");
        requestTime = (rs.getString("requestTime"));
        consultationTime = (rs.getString("consultationTime"));
        status = rs.getString("status");
        diagnosis = rs.getString("diagnosis");
        rateDoctor=rs.getInt("rateDoctor");

        ResultSetMetaData mt = rs.getMetaData();
//        if(mt.getColumnCount() > 10) {
//            jobb = new job(rs);
//        }
    }

    @Override
    public String toString() {
        return "Consultations{" +
                "consultId=" + consultId +
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
