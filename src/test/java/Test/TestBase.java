package Test;

import Configuration.ConfigHelper;
import Configuration.DriverContext;
import CustomReport.CustomReporter;
import PageBase.CommonPageActions;
import PageBase.DemoPortal;
import Utility.ReadDataFromXML;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TestBase extends ConfigHelper {
    public CommonPageActions PgCommonMethods;
    public DemoPortal DemoPortalObj;
   // public HRMDashboardPage HRMDashboardPageObj;
    String currentTestClass;
    String currentTestName;
    String currentUTCTime;
    private DriverContext drivercontext = new DriverContext();

    @BeforeClass
    public void beforeClass() {
        currentTestClass = this.getClass().getName();
        currentTestClass = currentTestClass.replace("Test.", "");
    }

    @BeforeTest
    public void SetEnvVar() throws IOException {
        SetEnvironmentData();
        CustomReporter.SetHtmlReport();
    }

    @BeforeMethod
    public void SetUpForEachTest(Method method) {
        try {
            currentTestName = method.getName();
            String env = PortalEnv;

            ReadDataFromXML.AddTestDataIntoTest(env, currentTestClass, currentTestName);
            drivercontext.SetUpDriver(Browser, BrowserMode);
            PgCommonMethods = new CommonPageActions(drivercontext);
            //here we can also add login  page or any other pagebase

            DemoPortalObj = new DemoPortal(drivercontext);
            //HRMDashboardPageObj= new HRMDashboardPage(drivercontext);

            currentUTCTime = OffsetDateTime.now(ZoneOffset.UTC).toString().replace("T", " ").replace("Z", "");
            CustomReporter.AddtestCaseDataToHtmlReport(currentTestName);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void TearDownAfterEachTest(ITestResult result) {
        drivercontext.TearDownDriver();
        CustomReporter.CurrentTestCaseOutcome(result);
    }

    @AfterTest
    public void CreateReport() {
        CustomReporter.FlushReport();
    }

    public String GetTestData(String key) {
        return ReadDataFromXML.ConfigDataValueperkey(key);
    }

}
