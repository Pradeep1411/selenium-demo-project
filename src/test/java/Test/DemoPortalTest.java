package Test;

import org.testng.annotations.Test;


public class DemoPortalTest extends TestBase {

    @Test
    public void Task1() throws Exception {
        DemoPortalObj.navigateToHRMPortal(HRM_PORTAL_URL);
        DemoPortalObj.enterValidCred("Admin","admin123");
        DemoPortalObj.navigateToPIMModuleForTask1();
    }

    @Test
    public void Task2() throws Exception {
        DemoPortalObj.navigateToHRMPortal(HRM_PORTAL_URL);
        DemoPortalObj.enterValidCred("Admin","admin123");
        DemoPortalObj.navigateToPIMModuleForTask2();
    }
}
