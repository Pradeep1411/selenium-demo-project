package PageBase;

import Configuration.DriverContext;
import org.openqa.selenium.By;

public class DemoPortal extends PageBase {

    public CommonPageActions PgCommonMethods;
   public static String signup_Xpath = "//a[text()='Sign up']";
    public DemoPortal(DriverContext drivercontext) {
        // TODO Auto-generated constructor stub
        super(drivercontext);
        PgCommonMethods = new CommonPageActions(drivercontext);

    }

    public void demoFunction(String url) {

        driver.navigate().to("https://www.browserstack.com/guide/testng-listeners");
        PgCommonMethods.waitUntilElementinVisible(By.xpath("xxdfdk"));
        //PgCommonMethods.FHCElement(signup_Xpath, "Signup Button");


    }

}
