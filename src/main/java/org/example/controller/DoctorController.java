package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.ConsultationDAO;
import org.example.dao.DoctorDAO;
import org.example.dto.DoctorDto;
import org.example.dto.DoctorDtoAll;
import org.example.dto.DoctorFilterDto;
import org.example.dto.RateDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.DoctorMapper;
import org.example.models.Doctors;

import java.net.URI;
import java.util.ArrayList;


@Path("/DOCTORS")
public class DoctorController {

    DoctorDAO dao = new DoctorDAO();
    DoctorDto dto = new DoctorDto();
    ConsultationDAO consDao = new ConsultationDAO();
    DoctorDtoAll dtoAll = new DoctorDtoAll();


    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllDoctors(
            @BeanParam DoctorFilterDto filter
    ) {

        try {
            GenericEntity<ArrayList<DoctorDto>> docs = new GenericEntity<ArrayList<DoctorDto>>(dao.selectAllDocs(filter)) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(docs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
//
//            } else if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
//                return Response
//                        .ok(docs)
//                        .type("text/csv")
//                        .build();
//            }

            } return Response
                    .ok(docs, MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{doctorId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    public Response getDoctor(
            @PathParam("doctorId") int doctorId) {

        try {
            DoctorDto docs = dao.selectDoc(doctorId);
            if (docs == null) {
                throw new DataNotFoundException("Doctors " + doctorId + "Not found");
            } else if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(docs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
//
            }
           // DoctorDto dto = DoctorMapper.INSTANCE.toDocDto(docs);
       //     addLinks(docs);

            return Response.ok(docs).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    private void addLinks(DoctorDto dto) {
//        URI selfUri = uriInfo.getAbsolutePath();
//        URI empsUri = uriInfo.getAbsolutePathBuilder().path(ScheduleController.class).build();
//
//        dto.addLink(selfUri.toString(), "self");
//        dto.addLink(empsUri.toString(), "Schedules");
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    public Response getDoctorByRate(@QueryParam("rateDoctor") int rateDoctor) {
        ArrayList<DoctorDto> doctors = new ArrayList<>();
        try {
            ArrayList<RateDto> rateDtos = consDao.searchByRate(rateDoctor);
            for (RateDto rateDto : rateDtos) {
                doctors.add(dao.selectDoc(rateDto.getDoctorId()));
            }

            return Response.ok(doctors).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//
//        @DELETE
//        @Path("{doctorId}")
//        public void deleteDoctor(@PathParam("doctorId") int doctorId) {
//
//            try {
//                dao.deleteDoc(doctorId);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertDoctor(DoctorDtoAll dtoAll) {

        try {
            //Doctors docs = DoctorMapper.INSTANCE.toModel(dtoAll);
            dao.insertDoc(dtoAll);
            URI uri = uriInfo.getAbsolutePathBuilder().path(dtoAll.getDoctorId() + "").build();
            return Response
                    .created(uri)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PUT
    @Path("{doctorId}")
    public void updateDoctor(@PathParam("doctorId") int doctorId, DoctorDtoAll dtoAll) {

        try {
            dtoAll.setDoctorId(doctorId);
            dao.updateDoc(dtoAll);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/rate")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRateDoctors(@QueryParam("rateDoctor") int rateDoctor) {
        try {
            ArrayList<DoctorDto> doctors = dao.getRateDoctors(rateDoctor);

            GenericEntity<ArrayList<DoctorDto>> doctor = new GenericEntity<ArrayList<DoctorDto>>(doctors) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response.ok(doctor).type(MediaType.APPLICATION_XML).build();
//            } else if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
//                return Response.ok(doctor).type("text/csv").build();
//            }
            }else {
                return Response.ok(doctor, MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

//        @Path("doctorId/SCHEDULES")
//    public ScheduleController getScheduleController() {
//            return new ScheduleController();
//        }


        }
    }
}
