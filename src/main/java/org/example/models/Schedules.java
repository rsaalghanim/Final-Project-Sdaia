package org.example.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Schedules {

    private int schedulesId ;
    private int doctorId ;
    private LocalDateTime startTime ;
    private LocalDateTime endTime ;
    private boolean isAvailable ;

    public Schedules() {
    }

    public Schedules(int schedulesId, int doctorId, LocalDateTime startTime, LocalDateTime endTime, boolean isAvailable) {
        this.schedulesId = schedulesId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    public int getSchedulesId() {
        return schedulesId;
    }

    public void setSchedulesId(int schedulesId) {
        this.schedulesId = schedulesId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Schedules(ResultSet rs) throws SQLException {
        schedulesId = rs.getInt("schedulesId");
        doctorId = rs.getInt("doctorId");
        startTime = LocalDateTime.parse(rs.getString("startTime"));
        endTime = LocalDateTime.parse(rs.getString("endTime"));
        isAvailable = Boolean.parseBoolean(String.valueOf(rs.getBoolean("isAvailable")));

        ResultSetMetaData mt = rs.getMetaData();
//        if(mt.getColumnCount() > 10) {
//            jobb = new job(rs);
//        }
    }

    @Override
    public String toString() {
        return "Schedules{" +
                "schedulesId=" + schedulesId +
                ", doctorId=" + doctorId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
