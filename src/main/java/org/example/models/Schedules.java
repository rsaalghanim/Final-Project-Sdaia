package org.example.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Schedules {

    private int schedulesId ;
    private int doctorId ;
    private String startTime ;
    private String endTime ;
    private boolean isAvailable ;

    public Schedules() {
    }

    public Schedules(int schedulesId, int doctorId, String startTime, String endTime, boolean isAvailable) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
        startTime = rs.getString("startTime");
        endTime = rs.getString("endTime");
        isAvailable = Boolean.parseBoolean(rs.getString("isAvailable"));

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
