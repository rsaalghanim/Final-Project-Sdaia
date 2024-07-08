package org.example.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

public class Patients {
   private int patientId ;
   private String name ;
   private String email ;
   private String password;
   private String phone ;
   private LocalDate dateOfBirth;

    public Patients() {
    }

    public Patients(int patientId, String email, String name, String password, String phone, LocalDate dateOfBirth) {
        this.patientId = patientId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Patients(ResultSet rs) throws SQLException {
        patientId = rs.getInt("patientId");
        name = rs.getString("name");
        email = rs.getString("email");
        password = rs.getString("password");
        phone = rs.getString("phone");
        dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));

        ResultSetMetaData mt = rs.getMetaData();
//        if(mt.getColumnCount() > 10) {
//            jobb = new job(rs);
//        }
    }

    @Override
    public String toString() {
        return "Patients{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
