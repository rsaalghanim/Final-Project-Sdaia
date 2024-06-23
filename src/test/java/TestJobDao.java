import jakarta.inject.Inject;
import org.example.dao.JobDAO;
import org.example.models.job;
import org.example.models.job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@ExtendWith(MockitoExtension.class)
public class TestJobDao {


    @InjectMocks
    JobDAO dao;



    @Test
    public void TestUpdateJob() throws SQLException, ClassNotFoundException {
job j = new job(4,"testing",3000,400000);

Assertions.assertDoesNotThrow(() -> dao.updateJob(j));


job newJ = dao.selectJob(j.getJob_id());

Assertions.assertNotNull(newJ);
    Assertions.assertEquals(newJ.getJob_title(), j.getJob_title());
    Assertions.assertEquals(newJ.getMax_salary() ,j.getMax_salary());




    }



}
