package org.example;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Singleton
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @Context HttpHeaders headers;
    @Context UriInfo uriInfo;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(
            @HeaderParam("apiKey") String apiKey,
            @CookieParam("username")String username
//            @Context HttpHeaders headers,
//            @Context UriInfo uriInfo
            ) {

        System.out.println(headers.getDate());
        System.out.println(headers.getLanguage());
        System.out.println(headers.getMediaType());
        System.out.println(headers.getCookies());
        System.out.println("---------------------");

        System.out.println(uriInfo.getAbsolutePath());
        System.out.println(uriInfo.getPathSegments());
        System.out.println(uriInfo.getPathParameters());
        System.out.println(uriInfo.getPathParameters().get("minsal"));

        return "Got it! name: " +username + ", apiKey: " + apiKey;
    }


//    @GET
//    @Path("{day}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getDate(@PathParam("day") LocalDate day) {
//        return "The date is: " + day;
//    }

//    @GET
//    @Path("/employees/{empId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public EmpIdDto getDate(@PathParam("empId") EmpIdDto empId) {
//        return empId;
//    }
}
