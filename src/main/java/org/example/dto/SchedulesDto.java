package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


@XmlRootElement
public class SchedulesDto {

    private int schedulesId ;
    private int doctorId ;
    private String startTime ;
    private String endTime ;
    private boolean isAvailable ;

    private ArrayList<LinkDto> links = new ArrayList<>();


    public SchedulesDto() {
    }

    public SchedulesDto(int schedulesId, int doctorId, String startTime, String endTime, boolean isAvailable) {
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
    public SchedulesDto(ResultSet rs) throws SQLException {
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
