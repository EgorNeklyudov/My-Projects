package test;

import framework.dbmethods.TestTableMethods;
import framework.utils.ProjectApiMethods;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBTest {

    private static final Date date = new Date();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String TIME = simpleDateFormat.format(date);
    private static final Integer ID = 346;
    private static final Integer DEFAULT_ID = 345;
    private static final String NAME = "Checking that the response to the request will come empty";
    private static final String METHOD_NAME = "test.DBTest.testCaseGetPostWithEmptyBody";
    private static final Integer PROJECT_ID = 1;
    private static final Integer SESSION_ID = 20;
    private static final String ENV = "GRIGORYEVS";
    private static final String BROWSER = "chrome";
    private static final Integer STATUS_ID_SUCCESS = 1;
    private static final Integer STATUS_ID_FAILURE = 2;
    private static final Integer STATUS_ID_SKIP = 3;
    private static final Integer AMOUNT_OF_COPY = 10;
    private static final Integer ID_FOR_CHECK_DELETE = 346;

    @Test
    public void testCaseGetPostWithEmptyBody() throws IOException, SQLException {

        TestTableMethods testTableMethods = new TestTableMethods();

        Assert.assertNull(ProjectApiMethods.getPostById(150, HttpStatus.SC_NOT_FOUND),
                "Actual response body isn't empty ! ");

        testTableMethods.add(NAME,METHOD_NAME,PROJECT_ID,SESSION_ID,TIME,ENV,BROWSER);

        Assert.assertTrue(testTableMethods.getIsPresent(ID),
                "The corresponding entry did not appear in the database !");

    }

    @Test
    public void testCaseCopyAndEditDataInDb() throws SQLException {

        TestTableMethods testTableMethods = new TestTableMethods();

        testTableMethods.delete(DEFAULT_ID);
        testTableMethods.getAndCopyAmountOfData(AMOUNT_OF_COPY);

        Assert.assertTrue(testTableMethods.getLastResultIsPresent(AMOUNT_OF_COPY),
                "The corresponding entry did not appear in the database !");

        testTableMethods.editTime(TIME,DEFAULT_ID);
        testTableMethods.delete(DEFAULT_ID);

        Assert.assertFalse(testTableMethods.getIsPresent(ID_FOR_CHECK_DELETE),
                "The corresponding entry did not appear in the database !");

    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws SQLException {

        TestTableMethods testTableMethods = new TestTableMethods();

        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("Passed");
                testTableMethods.edit(STATUS_ID_SUCCESS,TIME,DEFAULT_ID);
            } else if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("Failed");
                testTableMethods.edit(STATUS_ID_FAILURE,TIME,DEFAULT_ID);
            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("Skipped");
                testTableMethods.edit(STATUS_ID_SKIP,TIME,DEFAULT_ID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
