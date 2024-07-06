package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.ConsultFilterDto;
import org.example.dto.ConsultationsDto;
import org.example.dto.DoctorDto;
import org.example.dto.RateDto;
import org.example.models.Consultations;

import java.sql.*;
import java.util.ArrayList;

//DAO to Access the database
public class ConsultationDAO {

    private static final   String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\hospital.db";
    //Doctor GIVE consultation to a PENDING request:
    //Doctor RECORD a patient’s diagnosis:
    //Patient REQUEST consultation from a selected doctor:
    //Patient RATE a doctor Or from UPDATE:

    //***still****
    private static final String INSERT_CONSULT = "insert into CONSULTATIONS (doctorId, patientId, requestTime, consultationTime, status, diagnosis, rateDoctor) values (?, ?, ?, ?, ?, ?, ?)";

    //Doctor CHECK all PENDING consultation REQUESTS(***still***):
    private static final String SELECT_CONSULT_WITH_PENDING_REQ = "select * from CONSULTATIONS where doctorId = ? AND status = ?";

    //Patient CHECK a consultation RESULT:
    private static final String SELECT_ALL_CONSULT = "select * from CONSULTATIONS";

    //Patient RATE a doctor Or from INSERT:
    private static final String UPDATE_CONSULT = "update CONSULTATIONS set requestTime = ?, consultationTime = ?, status = ?, diagnosis = ?, rateDoctor = ? where consultId = ?";


    //----------not needed---------
    private static final String SELECT_ONE_CONSULT = "select * from CONSULTATIONS where consultId = ?";
    private static final String SELECT_CONSULT_WITH_DIAGNOSE = "select * from CONSULTATIONS where diagnosis = ?";

   // private static final String SELECT_CONSULT_WITH_RATE = "select * from CONSULTATIONS where rateDoctor = ?";

    private static final String SELECT_CONSULT_WITH_STAT = "select * from CONSULTATIONS where status = ?";

    //private static final String DELETE_CONSULT = "delete from CONSULTATIONS where consultId = ?";
    //private static final String SELECT_RATE = "select * from CONSULTATIONS where rateDoctor = ?";

    private static final String SELECT_RATE = "select DISTINCT doctorId from CONSULTATIONS where rateDoctor = ?";
    //----------not needed---------


    public void insertConsult(Consultations c) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();

        PreparedStatement st = conn.prepareStatement(INSERT_CONSULT)){
       // st.setInt(1, c.getConsultId());
        st.setInt(1, c.getDoctorId());
        st.setInt(2, c.getPatientId());
        st.setString(3, c.getRequestTime().toString());
        st.setString(4, c.getConsultationTime().toString());
        st.setString(5, c.getStatus());
        st.setString(6, c.getDiagnosis());
        st.setInt(7, c.getRateDoctor());
        st.executeUpdate();
        }
    }

    public void updateConsult(Consultations e) throws SQLException, ClassNotFoundException {
        //the class name for sql
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             //connection to sql
            PreparedStatement st = conn.prepareStatement(UPDATE_CONSULT)){
            st.setInt(6, e.getConsultId());
            st.setString(1, e.getRequestTime().toString());
            st.setString(2, e.getConsultationTime().toString());
            st.setString(3, e.getStatus());
            st.setString(4, e.getDiagnosis());
            st.setInt(5, e.getRateDoctor());
            st.executeUpdate();
        }
    }

    public Consultations selectConsult(int consultId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_CONSULT)){
        st.setInt(1, consultId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Consultations(rs);
        }
        else {
            return null;
        }
    }
}
    public Consultations SELECT_RATE(int rateDoctor) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_RATE)){
        st.setInt(1, rateDoctor);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Consultations(rs);
        }
        else {
            return null;
        }
    }
}

        public ArrayList<RateDto> searchByRate( int rate) throws SQLException, ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = MCPConnection.getConn();
                 PreparedStatement st = conn.prepareStatement(SELECT_RATE)) {
                st.setInt(1, rate);
                ResultSet rs = st.executeQuery();
                ArrayList<RateDto> cons = new ArrayList<>();
                while (rs.next()) {
                    cons.add(new RateDto(rs));
                }
                return cons;
            }
        }



    public ArrayList<ConsultationsDto> selectAllConsults(ConsultFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn()) {
            PreparedStatement st;
            if (filter.getDocId() != null && filter.getStat() != null) {
                st = conn.prepareStatement(SELECT_CONSULT_WITH_PENDING_REQ);
                st.setInt(1, filter.getDocId());
                st.setString(2, filter.getStat());
            } else if (filter.getDiagnose() != null) {
                st = conn.prepareStatement(SELECT_CONSULT_WITH_DIAGNOSE);
                st.setString(1, filter.getDiagnose());
            } else if (filter.getPendingReq() != null) {
                st = conn.prepareStatement(SELECT_CONSULT_WITH_PENDING_REQ);
                st.setString(1, filter.getPendingReq().toString());
            } else if (filter.getRate() != null) {
                st = conn.prepareStatement(SELECT_RATE);
                st.setInt(1, filter.getRate());
            } else if (filter.getStat() != null) {
                st = conn.prepareStatement(SELECT_CONSULT_WITH_STAT);
                st.setString(1, filter.getStat());
            } else {
                st = conn.prepareStatement(SELECT_ALL_CONSULT);
            }
            ResultSet rs = st.executeQuery();
            ArrayList<ConsultationsDto> cons = new ArrayList<>();
            while (rs.next()) {
                cons.add(new ConsultationsDto(rs));
            }

            return cons;
        }

    }



}
