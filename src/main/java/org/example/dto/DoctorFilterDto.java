package org.example.dto;

import jakarta.ws.rs.QueryParam;


public class DoctorFilterDto {

    @QueryParam("docSpecialty") String docSpecialty;
    @QueryParam("docId") Integer docId;
    @QueryParam("docName") String docName;
    @QueryParam("docAval") Boolean docAval;

    public String getDocSpecialty() {
        return docSpecialty;
    }

    public void setDocSpecialty(String docSpecialty) {
        this.docSpecialty = docSpecialty;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Boolean getDocAval() {
        return docAval;
    }

    public void setDocAval(Boolean docAval) {
        this.docAval = docAval;
    }
}


