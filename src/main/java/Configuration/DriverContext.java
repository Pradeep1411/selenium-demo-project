package Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Reporter;

public class DriverContext {
    public WebDriver driver;

    public void SetUpDriver(String browsertype, String browsermode) {
        String projectpath = System.getProperty("user.dir");
        String configProjPath;
        if (browsertype.equals("Chrome") && browsermode.equals("")) {
            /*configProjPath = projectpath + "\\ChromeDriver\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", configProjPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("ignore-certificate-errors");
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);*/

            driver = WebDriverManager.chromedriver().create();

        } else if (browsertype.equals("Chrome") && browsermode.equals("incognito")) {
            configProjPath = projectpath + "\\Chrome\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", configProjPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            options.addArguments("test-type");
            options.addArguments("ignore-certificate-errors");
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(options);

        }
        driver.manage().window().maximize();

    }

    public void TearDownDriver() {
        driver.close();
        driver.quit();
    }

}
