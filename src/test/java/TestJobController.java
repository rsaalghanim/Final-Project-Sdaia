//
//import org.example.controller.JobController;
//import org.example.dao.JobDAO;
//import org.example.dto.JobDto;
//import org.example.models.job;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.net.URI;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//    public class TestJobController {
//
//        @Mock
//        private JobDAO dao;
//
//        @InjectMocks
//        private JobController controller;
//
//        @BeforeEach
//        public void setup() {
//            MockitoAnnotations.openMocks(this);
//        }
//
//        @Test
//        public void testUpdateJob() {
//            // Mock input data
//            int jobId = 1;
//            job jobToUpdate = new job(4,"tesjjjjting",3000,400000);
//            JobDto jDto = new JobDto(4,"tesjjjjting",3000,400000)
//          //  job jobToUpdate = new job();
//          //  jobToUpdate.setJob_title("Updated Job Title");
//         //   jobToUpdate.setMin_salary(995595);
//            URI uri = URI.create("http://localhost/api/webapi/jobs/1");
//
////            Assertions.assertDoesNotThrow(() -> controller.getJob(4));
////            jDto.getLinks().clear();
//            when(dao.updateJob(4)).thenReturn(jobToUpdate);
//
//
//            controller.updateJob(jobId, jobToUpdate);
//
//
//            verify(dao).updateJob(jobToUpdate);
//        }
//    }
//}
