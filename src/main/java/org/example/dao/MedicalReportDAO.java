package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.MedReportsFilterDto;
import org.example.dto.MedicalReportsDto;
import org.example.models.MedicalReports;

import java.sql.*;
import java.util.ArrayList;

public class MedicalReportDAO {

    private static final   String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\hospital.db";

    //Doctor SEARCH patients’ medical records:
    private static final String SELECT_MED_WITH_PATIENT = "select * from MEDICAL_REPORTS where patientId = ?";

    private static final String INSERT_MED = "insert into MEDICAL_REPORTS (patientId, details, reportDate ) values (?, ?, ?)";


    //----------not needed---------
   // private static final String SELECT_ONE_MED = "select * from MEDICAL_REPORTS where medReportsId = ?";
    private static final String SELECT_MED_WITH_DETAIL = "select * from MEDICAL_REPORTS where details = ?";
    private static final String SELECT_ALL_MEDS = "select * from MEDICAL_REPORTS";
    private static final String UPDATE_MED = "update MEDICAL_REPORTS set patientId = ?, details = ?, reportDate = ? where medReportsId = ?";
   // private static final String DELETE_MED = "delete from MEDICAL_REPORTS where medReportsId = ?";
    //----------not needed---------


    public void insertMed(MedicalReportsDto m) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(INSERT_MED)) {
            //st.setInt(1, m.getMedReportsId());
            st.setInt(1, m.getPatientId());
            st.setString(2, m.getDetails());
            st.setString(3, m.getReportDate().toString());
            st.executeUpdate();
        }
    }

    public void updateMed(MedicalReportsDto m) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(UPDATE_MED)) {
            st.setInt(4, m.getMedReportsId());
            st.setInt(1, m.getPatientId());
            st.setString(2, m.getDetails());
            st.setString(3, m.getReportDate().toString());
            st.executeUpdate();
        }
    }

//    public void deleteMed(int medReportsId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(DELETE_MED);
//        st.setInt(1, medReportsId);
//        st.executeUpdate();
//    }

//    public MedicalReports selectMed(int medReportsId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_ONE_MED);
//        st.setInt(1, medReportsId);
//        ResultSet rs = st.executeQuery();
//        if(rs.next()) {
//            return new MedicalReports(rs);
//        }
//        else {
//            return null;
//        }
//    }

    public ArrayList<MedicalReportsDto> selectAllMeds(MedReportsFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn()) {
            PreparedStatement st;
            if (filter.getpId() != null) {
                st = conn.prepareStatement(SELECT_MED_WITH_PATIENT);
                st.setInt(1, filter.getpId());
            } else if (filter.getMedDet() != null) {
                st = conn.prepareStatement(SELECT_MED_WITH_DETAIL);
                st.setString(1, filter.getMedDet());
            } else {
                st = conn.prepareStatement(SELECT_ALL_MEDS);
            }
            ResultSet rs = st.executeQuery();
            ArrayList<MedicalReportsDto> meds = new ArrayList<>();
            while (rs.next()) {
                meds.add(new MedicalReportsDto(rs));
            }

            return meds;
        }
    }
}
