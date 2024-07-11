package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.MedicalReportDAO;
import org.example.dto.MedReportsFilterDto;
import org.example.dto.MedicalReportsDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.MedicalReportsMapper;
import org.example.models.MedicalReports;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/MEDICAL_REPORTS")
public class MedicalReportsController {

        MedicalReportDAO dao = new MedicalReportDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

        @GET
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
        public Response getAllMedicalReports(
                @BeanParam MedReportsFilterDto filter
        ) {

            try {
                GenericEntity<ArrayList<MedicalReportsDto>> meds = new GenericEntity<ArrayList<MedicalReportsDto>>(dao.selectAllMeds(filter)) {};
                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(meds)
                            .type(MediaType.APPLICATION_XML)
                            .build();

                  }
                else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                    return Response
                            .ok(meds)
                            .type("text/csv")
                            .build();
                }

                return Response
                        .ok(meds, MediaType.APPLICATION_JSON)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT")
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

//        @GET
//        @Path("{medReportsId}")
//        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"} )
//        public Response getMedicalReports(
//                @PathParam("medReportsId") int medReportsId) throws SQLException{
//
//            try {
//                MedicalReports meds = dao.selectMed(medReportsId);
//
//
//                if (meds == null) {
//                    throw new DataNotFoundException("Medical Reports " + medReportsId + " Not found");
//
//                }
//
//                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
//                    return Response
//                            .ok(meds)
//                            .type(MediaType.APPLICATION_XML)
//                            .build();
//                } // return dao.selectEmp(employee_id);
//
////                EmployeeDto dto = new EmployeeDto();
////                dto.setEmployee_id(emps.getEmployee_id());
////                dto.setFirst_name(emps.getFirst_name());
////                dto.setLast_name(emps.getLast_name());
////                dto.setEmail(emps.getEmail());
////                dto.setNumber(emps.getNumber());
////                dto.setHire_date(emps.getHire_date());
////                dto.setJob_id(emps.getJob_id());
////                dto.setSalary(emps.getSalary());
////                dto.setManager_id(emps.getManager_id());
////                dto.setDepartment_id(emps.getDepartment_id());
//                MedicalReportsDto dto = MedicalReportsMapper.INSTANCE.toMedReportsDto(meds);
//
//                addLinks(dto);
//
//                return Response.ok(dto).build();
//            }
//            catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }



    private void addLinks (MedicalReportsDto dto){
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder().path(MedicalReportsController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(),"MedicalReports");
    }


        @POST
        @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
        public Response insertMedicalReports(MedicalReportsDto meds) {

            try {
                dao.insertMed(meds);
              //  NewCookie cookie = (new NewCookie.Builder("username")).value("00000").build();
                URI uri = uriInfo.getAbsolutePathBuilder().path(meds.getMedReportsId()+"").build();
                return Response
                        .created(uri)
//                        .cookie(cookie)
//                        .header("Created by", "Ragad Alghanim")
                        .build();
               // dao.insertEmp(emps);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @PUT
        @Path("{medReportsId}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
        public Response updateMedicalReports(
                @PathParam("medReportsId") int medReportsId, MedicalReportsDto meds) {

            try {
                meds.setMedReportsId(medReportsId);
                dao.updateMed(meds);
//                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
//                    return Response
//                            .ok(meds)
//                            .type(MediaType.APPLICATION_XML)
//                            .build();
////
//                }
               // return Response.ok(meds).build();
                return Response.ok().build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



}
