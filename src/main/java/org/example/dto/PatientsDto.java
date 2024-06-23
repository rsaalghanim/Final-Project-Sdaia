package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement
public class PatientsDto {
   private int patientId ;
   private String name ;
   private String email ;
   private String password;
   private String phone ;
   private LocalDate dateOfBirth;

    private ArrayList<LinkDto> links = new ArrayList<>();

    public PatientsDto() {
    }

    public PatientsDto(int patientId, String email, String name, String password, String phone, LocalDate dateOfBirth) {
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
    public PatientsDto(ResultSet rs) throws SQLException {
        patientId = rs.getInt("patientId");
        name = rs.getString("name");
        email = rs.getString("email");
        password = rs.getString("password");
        phone = rs.getString("phone");
        dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();

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