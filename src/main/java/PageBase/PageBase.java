package PageBase;

import Configuration.DriverContext;
import org.openqa.selenium.WebDriver;

public class PageBase {
    WebDriver driver;

    public PageBase(DriverContext drivercontext) {
        this.driver = drivercontext.driver;

    }
}
