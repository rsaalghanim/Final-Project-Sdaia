package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.ConsultationDAO;
import org.example.dto.ConsultFilterDto;
import org.example.dto.ConsultationsDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.ConsultationMapper;
import org.example.models.Consultations;

import java.net.URI;
import java.util.ArrayList;

//controller receive all requests
@Path("/CONSULTATIONS")
public class ConsultationController {

    //create object from DAO class
    ConsultationDAO dao = new ConsultationDAO();


    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    //GET annotation for all consultation
        @GET
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
        public Response getAllConsults(
                @BeanParam ConsultFilterDto filter
                ) {

            try {
                GenericEntity<ArrayList<Consultations>> cons = new GenericEntity<ArrayList<Consultations>>(dao.selectAllConsults(filter)) {};
                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(cons)
                            .type(MediaType.APPLICATION_XML)
                            .build();
//
                }else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                    return Response
                            .ok(cons)
                            .type("text/csv")
                            .build();
                }

                return Response
                        .ok(cons, MediaType.APPLICATION_JSON)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @GET
        @Path("{consultId}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})

        public Response getConsults(
                @PathParam("consultId") int consultId) {

            try {
                Consultations cons = dao.selectConsult(consultId);
                if (cons == null) {
                    throw new DataNotFoundException("Consultation " + consultId + "Not found");
                }
                else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(cons)
                            .type(MediaType.APPLICATION_XML)
                            .build();
//
                }

                ConsultationsDto dto = ConsultationMapper.INSTANCE.toConsultDto(cons);
              addLinks(dto);

                return Response.ok(dto).build();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void addLinks (ConsultationsDto dto){
            URI selfUri = uriInfo.getAbsolutePath();
            URI empsUri = uriInfo.getAbsolutePathBuilder().path(DoctorController.class).build();

            dto.addLink(selfUri.toString(), "self");
            dto.addLink(empsUri.toString(),"DOCTORS");
        }

        @POST
       @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
        public Response insertConsultation(ConsultationsDto dto) {

            try {
                Consultations cons = ConsultationMapper.INSTANCE.toModel(dto);
                dao.insertConsult(cons);
                URI uri = uriInfo.getAbsolutePathBuilder().path(cons.getConsultId()+"").build();
                return Response
                        .created(uri)
//                        .cookie(cookie)
//                        .header("Created by", "Ragad")
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }




        @PUT
        @Path("{consultId}")
        //updated attributes will in object cons
        //اضفنا  consultId عشان نوصل للعنصر
        public void updateConsult(@PathParam("consultId") int consultId, Consultations cons) {

            try {
                //passing the variable consultId to the object cons
                cons.setConsultId(consultId);
                //passing the cons object to update
                dao.updateConsult(cons);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



}
