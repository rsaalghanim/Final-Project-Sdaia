package org.example.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.dto.ErrorMessage;
//import org.glassfish.jersey.spi.ExceptionMapper;


@Provider //يعني الكلاس هذا راح يوفر ريسبونس
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException exception) {//الميثود تستقبل الاكسبشن وترجع ريسبونس
        ErrorMessage err = new ErrorMessage();
        //err.setErrorContent(exe());
        err.setErrorContent(exception.getMessage());
        err.setErrorCode(404);
        err.setDocumentationUrl("https://google.com");

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(err)//انتيتي البودي اللي يطلع بالرسبونس اللي بالبوستمان
                .build();
    }


//    Override
//    public Response toResponse(DataNotFoundException exception) {

//    }

}
