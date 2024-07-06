package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.ScheduleFilterDto;
import org.example.dto.SchedulesDto;
import org.example.models.Schedules;

import java.sql.*;
import java.util.ArrayList;

public class ScheduleDAO {

    private static final   String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\hospital.db";

    //Doctor MANAGE availability schedule:
    private static final String UPDATE_SCHED = "update SCHEDULES set startTime = ?, endTime = ?, isAvailable = ? where schedulesId = ?";

    private static final String INSERT_SCHED = "insert into SCHEDULES (doctorId, startTime, endTime, isAvailable) values (?, ?, ?, ?)";

    //----------not needed---------
    private static final String SELECT_ONE_SCHED = "select * from SCHEDULES where schedulesId = ?";
    private static final String SELECT_SCHED_WITH_DOC = "select * from SCHEDULES where doctorId = ?";
    private static final String SELECT_SCHED_WITH_AVAILABLE = "select * from SCHEDULES where isAvailable = ?";
    private static final String SELECT_SCHED_WITH_DOC_AVAILABLE = "select * from SCHEDULES where doctorId = ? AND isAvailable = ?";
    private static final String SELECT_ALL_SCHEDS = "select * from SCHEDULES";
    // private static final String UPDATE_SCHED = "update SCHEDULES set startTime = ?, endTime = ? where doctorId = ?";
    //  private static final String DELETE_SCHED = "delete from SCHEDULES where schedulesId = ?";
    //----------not needed---------






    public void insertSched(Schedules s) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(INSERT_SCHED)) {
            // st.setInt(1, s.getSchedulesId());
            st.setInt(1, s.getDoctorId());
            st.setString(2, s.getStartTime().toString());
            st.setString(3, s.getEndTime().toString());
            st.setBoolean(4, s.isAvailable());
            st.executeUpdate();
        }
    }

    public void updateSched(SchedulesDto s) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(UPDATE_SCHED)) {
            st.setInt(4, s.getSchedulesId());
            st.setString(1, s.getStartTime().toString());
            st.setString(2, s.getEndTime().toString());
            st.setBoolean(3, s.isAvailable());
            st.executeUpdate();
        }
    }

    public Schedules selectSched(int schedulesId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(SELECT_ONE_SCHED)) {
            st.setInt(1, schedulesId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Schedules(rs);
            } else {
                return null;
            }
        }
    }

    public ArrayList<Schedules> selectAllSched(ScheduleFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn()) {
            PreparedStatement st;
            if (filter.getDocId() != null && filter.getDocAvailable() != null) {
                st = conn.prepareStatement(SELECT_SCHED_WITH_DOC_AVAILABLE);
                st.setInt(1, filter.getDocId());
                st.setBoolean(2, filter.getDocAvailable());
            } else if (filter.getDocId() != null) {
                st = conn.prepareStatement(SELECT_SCHED_WITH_DOC);
                st.setInt(1, filter.getDocId());
            } else if (filter.getDocAvailable() != null) {
                st = conn.prepareStatement(SELECT_SCHED_WITH_AVAILABLE);
                st.setBoolean(1, filter.getDocAvailable());
            } else {
                st = conn.prepareStatement(SELECT_ALL_SCHEDS);
            }
            ResultSet rs = st.executeQuery();
            ArrayList<Schedules> scheds = new ArrayList<>();
            while (rs.next()) {
                scheds.add(new Schedules(rs));
            }

            return scheds;
        }
    }

}
