package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.DoctorDAO;
import org.example.dto.DoctorDto;
import org.example.dto.DoctorFilterDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.DoctorMapper;
import org.example.models.Doctors;

import java.net.URI;
import java.util.ArrayList;


@Path("/DOCTORS")
public class DoctorController {

    DoctorDAO dao = new DoctorDAO();


    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

        @GET
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
        public Response getAllDoctors(
                @BeanParam DoctorFilterDto filter
                ) {

            try {
                GenericEntity<ArrayList<Doctors>> docs = new GenericEntity<ArrayList<Doctors>>(dao.selectAllDocs(filter)) {};
                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(docs)
                            .type(MediaType.APPLICATION_XML)
                            .build();
//
                }else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                    return Response
                            .ok(docs)
                            .type("text/csv")
                            .build();
                }

                return Response
//                    .ok()
//                    .entity(jobs)
//                    .type(MediaType.APPLICATION_JSON)
                        .ok(docs, MediaType.APPLICATION_JSON)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @GET
        @Path("{doctorId}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})

        public Response getDoctor(
                @PathParam("doctorId") int doctorId) {

            try {
                Doctors docs = dao.selectDoc(doctorId);
                if (docs == null) {
                    throw new DataNotFoundException("Doctors " + doctorId + "Not found");
                }
                else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(docs)
                            .type(MediaType.APPLICATION_XML)
                            .build();
//
                }

//                JobDto dto = new JobDto();
//                dto.setJob_id(jobs.getJob_id());
//                dto.setJob_title(jobs.getJob_title());
//                dto.setMin_salary(jobs.getMin_salary());
//                dto.setMax_salary(jobs.getMax_salary());
                DoctorDto dto = DoctorMapper.INSTANCE.toJobDto(docs);
              addLinks(dto);

                return Response.ok(dto).build();
              // return jobs;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void addLinks (DoctorDto dto){
            URI selfUri = uriInfo.getAbsolutePath();
            URI empsUri = uriInfo.getAbsolutePathBuilder().path(DoctorController.class).build();

            dto.addLink(selfUri.toString(), "self");
            dto.addLink(empsUri.toString(),"Doctors");
        }

        @DELETE
        @Path("{doctorId}")
        public void deleteDoctor(@PathParam("doctorId") int doctorId) {

            try {
                dao.deleteDoc(doctorId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @POST
       @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
        public Response insertDoctor(DoctorDto dto) {

            try {
                Doctors docs = DoctorMapper.INSTANCE.toModel(dto);
                dao.insertDoc(docs);
                NewCookie cookie = (new NewCookie.Builder("username")).value("00000").build();
                URI uri = uriInfo.getAbsolutePathBuilder().path(docs.getDoctorId()+"").build();
                return Response
                        .created(uri)
                        .cookie(cookie)
                        .header("Created by", "Ragad")
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }




        @PUT
        @Path("{doctorId}")
        public void updateDoctor(@PathParam("doctorId") int doctorId, Doctors docs) {

            try {
                docs.setDoctorId(doctorId);
                dao.updateDoc(docs);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
//        @Path("doctorId/SCHEDULES")
//    public ScheduleController getScheduleController() {
//            return new ScheduleController();
//        }



}
