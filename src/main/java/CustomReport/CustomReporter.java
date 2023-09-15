package CustomReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomReporter {

    public static ExtentSparkReporter ExtentHtmlReporter;
    public static ExtentReports ExtentReport;
    public static ExtentTest CurrentTest;
    private static String htmlreportPath;

    public static void SetHtmlReport() {
        String dateTimenow = new SimpleDateFormat("MMddhhmmss").format(new Date());
        htmlreportPath = System.getProperty("user.dir") + "\\ExtentReport\\" + dateTimenow + "_log.html";
        ExtentHtmlReporter = new ExtentSparkReporter(htmlreportPath);
        ExtentHtmlReporter.config().setDocumentTitle("Automation Execution Document name");
        ExtentHtmlReporter.config().setReportName("Automation Execution report name");
        ExtentHtmlReporter.config().setTheme(Theme.STANDARD);
        ExtentReport = new ExtentReports();
        ExtentReport.attachReporter(ExtentHtmlReporter);
    }

    public static void CurrentTestCaseOutcome(ITestResult result) {
        int status = result.getStatus();
        switch (status) {
            case ITestResult.SUCCESS:
                CurrentTest.pass("Passed");
                break;
            case ITestResult.FAILURE:
                CurrentTest.fail("Failed");
                break;

            case ITestResult.SKIP:
                CurrentTest.skip("Skipped");
                break;
            default:
                CurrentTest.fail("Failed");
                break;
        }

    }

    public static void AddtestCaseDataToHtmlReport(String testCaseName) {
        CurrentTest = ExtentReport.createTest(testCaseName);
    }

    public static void FlushReport() {
        ExtentReport.flush();
    }

    public static void ReportTestStepStatus(Status status, String message) {
        CurrentTest.log(status, message);
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ExtentReport\\" + screenshotName + dateName + ".png";
        File finaldestination = new File(destination);
        Files.copy(source, finaldestination);

        return destination;


    }

    public static void AddScreenshotInreport(WebDriver driver, String screenshotName) {
        try {
            CurrentTest.addScreenCaptureFromPath(getScreenshot(driver, screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
