package Test;

import org.testng.annotations.Test;

import PageBase.DemoPortal;

public class DemoPortalTest extends TestBase {

    @Test
    public void DemoFunction() throws Exception {

        DemoPortal.demoFunction(URL);
    }

}
