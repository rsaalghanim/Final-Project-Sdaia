package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.ScheduleDAO;
import org.example.dto.ScheduleFilterDto;
import org.example.dto.SchedulesDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.ScheduleMapper;
import org.example.models.Schedules;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/SCHEDULES")
public class ScheduleController {

        ScheduleDAO dao = new ScheduleDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

        @GET
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
        public Response getAllSchedule(
                @BeanParam ScheduleFilterDto filter
        ) {

            try {
                GenericEntity<ArrayList<Schedules>> scheds = new GenericEntity<ArrayList<Schedules>>(dao.selectAllSched(filter)) {};
                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(scheds)
                            .type(MediaType.APPLICATION_XML)
                            .build();

                  }
                else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                    return Response
                            .ok(scheds)
                            .type("text/csv")
                            .build();
                }

                return Response
                        .ok(scheds, MediaType.APPLICATION_JSON)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @GET
        @Path("{schedulesId}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"} )
        public Response getSchedule(
                @PathParam("schedulesId") int schedulesId) throws SQLException{

            try {
                Schedules scheds = dao.selectSched(schedulesId);


                if (scheds == null) {
                    throw new DataNotFoundException("Schedule " + schedulesId + " Not found");

                }

                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(scheds)
                            .type(MediaType.APPLICATION_XML)
                            .build();
                } // return dao.selectEmp(employee_id);

//                EmployeeDto dto = new EmployeeDto();
//                dto.setEmployee_id(emps.getEmployee_id());
//                dto.setFirst_name(emps.getFirst_name());
//                dto.setLast_name(emps.getLast_name());
//                dto.setEmail(emps.getEmail());
//                dto.setNumber(emps.getNumber());
//                dto.setHire_date(emps.getHire_date());
//                dto.setJob_id(emps.getJob_id());
//                dto.setSalary(emps.getSalary());
//                dto.setManager_id(emps.getManager_id());
//                dto.setDepartment_id(emps.getDepartment_id());
                SchedulesDto dto = ScheduleMapper.INSTANCE.toScheduleDto(scheds);

                addLinks(dto);

                return Response.ok(dto).build();
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }



    private void addLinks (SchedulesDto dto){
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder().path(ScheduleController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(),"Schedules");
    }

        @DELETE
        @Path("{schedulesId}")
        public Response deleteSchedule(
                @PathParam("schedulesId") int schedulesId) {

            try {
                dao.deleteSched(schedulesId);
                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok()
                            .type(MediaType.APPLICATION_XML)
                            .build();
//
                }
                return Response.ok().build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @POST
        @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
        public Response insertSchedule(Schedules scheds) {

            try {
                dao.insertSched(scheds);
                NewCookie cookie = (new NewCookie.Builder("username")).value("00000").build();
                URI uri = uriInfo.getAbsolutePathBuilder().path(scheds.getSchedulesId()+"").build();
                return Response
                        .created(uri)
                        .cookie(cookie)
                        .header("Created by", "Ragad Alghanim")
                        .build();
               // dao.insertEmp(emps);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @PUT
        @Path("{schedulesId}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
        public Response updateSchedule(
                @PathParam("schedulesId") int schedulesId, Schedules scheds) {

            try {
                scheds.setSchedulesId(schedulesId);
                dao.updateSched(scheds);
                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(scheds)
                            .type(MediaType.APPLICATION_XML)
                            .build();
//
                }
                return Response.ok(scheds).build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



}
