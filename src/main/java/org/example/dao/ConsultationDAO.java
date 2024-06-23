package org.example.dao;

import org.example.dto.ConsultFilterDto;
import org.example.models.Consultations;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsultationDAO {
    //C:\Users\dev\IdeaProjects\Hospital\hospital.db
    private static final   String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\hospital.db";
    private static final String INSERT_CONSULT = "insert into CONSULTATIONS (doctorId, patientId, requestTime, consultationTime, status, diagnosis, rateDoctor) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ONE_CONSULT = "select * from CONSULTATIONS where consultId = ?";
    private static final String SELECT_CONSULT_WITH_DIAGNOSE = "select * from CONSULTATIONS where diagnosis = ?";
    private static final String SELECT_CONSULT_WITH_PENDING_REQ = "select * from CONSULTATIONS where doctorId = ? AND status = ?";
    private static final String SELECT_CONSULT_WITH_RATE = "select * from CONSULTATIONS where rateDoctor = ?";
    private static final String SELECT_CONSULT_WITH_STAT = "select * from CONSULTATIONS where status = ?";
    private static final String SELECT_ALL_CONSULT = "select * from CONSULTATIONS";
    private static final String UPDATE_CONSULT = "update CONSULTATIONS set status = ?, diagnosis = ? where consultId = ?";
    private static final String DELETE_CONSULT = "delete from CONSULTATIONS where consultId = ?";


    public void insertConsult(Consultations c) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_CONSULT);
       // st.setInt(1, c.getConsultId());
        st.setInt(1, c.getDoctorId());
        st.setInt(2, c.getPatientId());
        st.setString(3, c.getRequestTime().toString());
        st.setString(4, c.getConsultationTime().toString());
        st.setString(5, c.getStatus());
        st.setString(6, c.getDiagnosis());
        st.setInt(7, c.getRateDoctor());

    }

    public void updateConsult(Consultations e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_CONSULT);
        st.setInt(3, e.getConsultId());
        st.setString(1, e.getStatus());
        st.setString(2, e.getDiagnosis());
        st.executeUpdate();
    }

    public void deleteConsult(int consultId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_CONSULT);
        st.setInt(1, consultId );
        st.executeUpdate();
    }//

    public Consultations selectConsult(int consultId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_CONSULT);
        st.setInt(1, consultId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Consultations(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Consultations> selectAllConsults(String diagnose , Integer rate, LocalDateTime pendingReq, String stat,Integer docId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(docId != null && stat != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_DIAGNOSE);
            st.setInt(1, docId);
            st.setString(1, stat);
        }
        else if(diagnose != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_DIAGNOSE);
            st.setString(1, diagnose);
        }
        else if(pendingReq != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_PENDING_REQ);
            st.setString(1, pendingReq.toString());
        }
        else if(rate != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_RATE);
            st.setInt(1, rate);
        }
        else if(stat != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_STAT);
            st.setString(1, stat);
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_CONSULT);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Consultations> cons = new ArrayList<>();
        while (rs.next()) {
            cons.add(new Consultations(rs));
        }

        return cons;
    }

    public ArrayList<Consultations> selectAllConsults(ConsultFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(filter.getDiagnose() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_DIAGNOSE);
            st.setString(1, filter.getDiagnose());
        }
        else if(filter.getPendingReq() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_PENDING_REQ);
            st.setString(1, filter.getPendingReq().toString());
        }
        else if(filter.getRate() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_RATE);
            st.setInt(1, filter.getRate());
        }
        else if(filter.getStat() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_STAT);
            st.setString(1, filter.getStat());
        }else {
            st = conn.prepareStatement(SELECT_ALL_CONSULT);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Consultations> cons = new ArrayList<>();
        while (rs.next()) {
            cons.add(new Consultations(rs));
        }

        return cons;
    }



}
