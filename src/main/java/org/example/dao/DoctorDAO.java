package org.example.dao;


import org.example.db.MCPConnection;
import org.example.dto.DoctorFilterDto;
import org.example.models.Doctors;

import java.sql.*;
import java.util.ArrayList;


public class DoctorDAO {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\hospital.db";
    private static final String SELECT_ALL_DOCS = "select * from DOCTORS";
    //private static final String SELECT_DOCS = "select doctorId, name from DOCTORS";
    private static final String SELECT_ONE_DOC = "select * from DOCTORS where doctorId = ?";
    private static final String SELECT_DOC_WITH_SPECIALITY = "select * from DOCTORS where specialty = ?";
    private static final String SELECT_DOC_WITH_ID= "select * from DOCTORS where doctorId = ?";
    private static final String SELECT_DOC_WITH_NAME = "select * from DOCTORS where name = ?";
    private static final String INSERT_DOC = "insert into DOCTORS (name, specialty, email, password, phone) values (?, ?, ?, ?, ?)";
    private static final String UPDATE_DOC = "update DOCTORS set email = ?, specialty = ? where doctorId = ?";
    private static final String DELETE_DOC = "delete from DOCTORS where doctorId = ?";
   private static final String LOGIN_DOC = "select * from DOCTORS where email = ? AND password = ?";


    public void insertDoc(Doctors d) throws SQLException, ClassNotFoundException {
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


    public void updateDoc(Doctors d) throws SQLException, ClassNotFoundException {
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

    public Doctors selectDoc(int doctorId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(SELECT_ONE_DOC)) {
            st.setInt(1, doctorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Doctors(rs);
            } else {
                return null;//
            }

        }
    }

//    public ArrayList<Doctors> selectAllDocs(String docSpecialty, Integer docId, String docName) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st ;
//        if(docSpecialty != null) {
//            st = conn.prepareStatement(SELECT_DOC_WITH_SPECIALITY);
//            st.setString(1,docSpecialty);
//        }
//        else if(docId != null) {
//            st = conn.prepareStatement(SELECT_DOC_WITH_ID);
//            st.setInt(1, docId);
//        }
//        else if(docName != null) {
//            st = conn.prepareStatement(SELECT_DOC_WITH_NAME);
//            st.setString(1, docName);
//
//        }
//        else {
//            st = conn.prepareStatement(SELECT_ALL_DOCS);
//        }
//        ResultSet rs = st.executeQuery();
//        ArrayList<Doctors> jobs = new ArrayList<>();
//        while (rs.next()) {
//            jobs.add(new Doctors(rs));
//        }
//
//        return jobs;
//    }


    public ArrayList<Doctors> selectAllDocs(DoctorFilterDto filter) throws SQLException, ClassNotFoundException {
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
        else {
            st = conn.prepareStatement(SELECT_ALL_DOCS);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Doctors> docs = new ArrayList<>();
        while (rs.next()) {
            docs.add(new Doctors(rs));
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
