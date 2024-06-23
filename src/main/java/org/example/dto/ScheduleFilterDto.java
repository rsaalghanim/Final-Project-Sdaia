package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class ScheduleFilterDto {

    @QueryParam("docId") Integer docId;
    @QueryParam("docAvailable") Boolean docAvailable;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Boolean getDocAvailable() {
        return docAvailable;
    }

    public void setDocAvailable(Boolean docAvailable) {
        this.docAvailable = docAvailable;
    }
}
