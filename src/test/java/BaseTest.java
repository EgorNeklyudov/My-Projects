import framework.driver.DriverUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void start(){
        DriverUtils.maximizeWindow();
    }

    @AfterTest
    public void close(){
        DriverUtils.quitDriver();
    }

}