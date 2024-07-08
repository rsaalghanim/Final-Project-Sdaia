package org.example.dao;


import org.example.db.MCPConnection;
import org.example.dto.DoctorDto;
import org.example.dto.DoctorDtoAll;
import org.example.dto.DoctorFilterDto;
import org.example.models.Doctors;

import java.sql.*;
import java.util.ArrayList;


public class DoctorDAO {

   // private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\hospital.db";

    //SEARCH for doctor ALL:
    private static final String SELECT_ALL_DOCS = "select * from DOCTORS";
    //private static final String SELECT_DOCS = "select doctorId, name from DOCTORS";

    //SEARCH for doctor by ID 1:
    private static final String SELECT_ONE_DOC = "select * from DOCTORS where doctorId = ?";

    //SEARCH for doctor by ID 2:
    private static final String SELECT_DOC_WITH_ID= "select * from DOCTORS where doctorId = ?";

    //SEARCH for doctor by SPECIALITY:
    private static final String SELECT_DOC_WITH_SPECIALITY = "select * from DOCTORS where specialty = ?";

    //SEARCH for doctor by NAME:
    private static final String SELECT_DOC_WITH_NAME = "select * from DOCTORS where name = ?";

    //SEARCH for doctor by RATE:
    private static final String RATE_BY_DOCTOR = "SELECT doctorId, name, specialty FROM DOCTORS WHERE doctorId in (select distinct (doctorId) from  CONSULTATIONS where CONSULTATIONS.rateDoctor = ?)";

    //SEARCH for doctor by AVAILABILITY:
    private static final String SCHEDULE_AVAILABILITY_BY_DOCTOR = "SELECT doctorId, name, specialty FROM DOCTORS WHERE doctorId in (select distinct (doctorId) from SCHEDULES where SCHEDULES.isAvailable = ?)";

    //UPDATE:
    private static final String UPDATE_DOC = "update DOCTORS set email = ?, specialty = ? where doctorId = ?";
    // private static final String DELETE_DOC = "delete from DOCTORS where doctorId = ?";

    //Doctor REGISTER:
    private static final String INSERT_DOC = "insert into DOCTORS (name, specialty, email, password, phone) values (?, ?, ?, ?, ?)";

    //Doctor LOGIN:
   private static final String LOGIN_DOC = "select * from DOCTORS where email = ? AND password = ?";



    public void insertDoc(DoctorDtoAll d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(INSERT_DOC)) {
            //st.setInt(1,d.getDoctorId());
            st.setString(1, d.getName());
            st.setString(2, d.getSpecialty());
            st.setString(3, d.getEmail());
            st.setString(4, d.getPassword());
            st.setString(5, d.getPhone());

            st.executeUpdate();
        }
    }


    public void updateDoc(DoctorDtoAll d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(UPDATE_DOC)) {
            st.setInt(3, d.getDoctorId());
            st.setString(1, d.getEmail());
            st.setString(2, d.getSpecialty());
            st.executeUpdate();
        }
    }

//    public void deleteDoc(int doctorId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(DELETE_DOC);
//        st.setInt(1, doctorId);
//        st.executeUpdate();
//    }

    public DoctorDto selectDoc(int doctorId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(SELECT_ONE_DOC)) {
            st.setInt(1, doctorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new DoctorDto(rs);
            } else {
                return null;//
            }

        }
    }

    public ArrayList<DoctorDto> getRateDoctors(int consultation_rating) throws SQLException, ClassNotFoundException {
        ArrayList<DoctorDto> doctors = new ArrayList<>();

        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(RATE_BY_DOCTOR);

        st.setInt(1, consultation_rating);

        try (ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                DoctorDto doctor = new DoctorDto(
                        resultSet.getInt("doctorId"),
                        resultSet.getString("name"),
                        resultSet.getString("specialty")

                );
                doctors.add(doctor);
            }
        }

        return doctors;

    }



    public ArrayList<DoctorDto> selectAllDocs(DoctorFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn())
        {PreparedStatement st ;
        if(filter.getDocSpecialty() != null ) {
            st = conn.prepareStatement(SELECT_DOC_WITH_SPECIALITY);
            st.setString(1,filter.getDocSpecialty() );
        }
        else if(filter.getDocId() != null) {
            st = conn.prepareStatement(SELECT_DOC_WITH_ID);
            st.setInt(1, filter.getDocId());
        }
        else if(filter.getDocName() != null) {
            st = conn.prepareStatement(SELECT_DOC_WITH_NAME);
            st.setString(1, filter.getDocName());

        }

        else if(filter.getDocAval() != null) {
            st = conn.prepareStatement(SCHEDULE_AVAILABILITY_BY_DOCTOR);
            st.setBoolean(1, filter.getDocAval());

        }
        else {
            st = conn.prepareStatement(SELECT_ALL_DOCS);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<DoctorDto> docs = new ArrayList<>();
        while (rs.next()) {
            docs.add(new DoctorDto(rs));
        }

        return docs;


    }
    }
    public Doctors DoctorLogin(String docEmail,String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(LOGIN_DOC)) {
            st.setString(1, docEmail);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Doctors(rs);
            } else {
                return null;
            }
        }
    }
}
