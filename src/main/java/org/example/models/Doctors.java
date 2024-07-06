package org.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {

  private int doctorId ;
  private String name;
  private String specialty;
  private String email ;
  private String password;
  private String phone;

    public Doctors() {
    }

    public Doctors(String name, int doctorId, String specialty) {
        this.name = name;
        this.doctorId = doctorId;
        this.specialty = specialty;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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

    public Doctors(ResultSet rs) throws SQLException {
        doctorId = rs.getInt("doctorId");
        name = rs.getString("name");
        specialty = rs.getString("specialty");
        email = rs.getString("email");
        password = rs.getString("password");
        phone = rs.getString("phone");
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}