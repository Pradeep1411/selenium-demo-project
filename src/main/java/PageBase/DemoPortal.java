package PageBase;

import Configuration.DriverContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoPortal extends PageBase {

    public static String signup_Xpath = "//a[text()='Sign up']";
    public static By bySubmitButton = new ByXPath("//button[@type='submit']");
    public static String loginPageUserNameXpath = "//input[@name='username']";
    public static String loginPagePasswordXpath = "//input[@name='password']";
    public static String loginButtonXpath = "//button[@type='submit']";
    public static By spinnerLoaderWait = new ByXPath("//div[@class='oxd-loading-spinner']");
    public static By pimModule = new ByXPath("//ul[@class='oxd-main-menu']/li[2]/a/span");
    public static By addEmployee = new ByXPath("//a[text()='Add Employee']");
    public static String addEmpFirstNameXpath = "//input[@name='firstName']";
    public static String addEmpMiddleNameXpath = "//input[@name='middleName']";
    public static String addEmpLastNameXpath = "//input[@name='lastName']";
    public static String createLoginSwitchButtonXpath = "//span[contains(@class,'oxd-switch-input')]";
    public static String newLoginUserNameXpath = "(//div[@class='oxd-form-row'])[2]//input[contains(@class,'oxd-input')]";
    public static String newPasswordXpath = "(//input[@type='password'])[1]";
    public static String confirmPasswordXpath = "(//input[@type='password'])[2]";
    public static String errorMessageXpath = "//span[contains(@class,'input-field-error-message')]";
    public static String saveButtonXpath = "//button[@type='submit']";
    public static String userDropDownXpath = "//li[@class='oxd-userdropdown']/span";
    public static String logoutButtonXpath = "//ul[@class='oxd-dropdown-menu']/li[4]/a";
    public static String dashboardPageXpath="//h6[text()='Dashboard']";
    public static String dashboardVerifyXpath="//p[text()='Time at Work']";
    private final WebDriverWait wait = new WebDriverWait(driver, 10);
    public CommonPageActions PgCommonMethods;


    public DemoPortal(DriverContext drivercontext) {
        // TODO Auto-generated constructor stub
        super(drivercontext);
        PgCommonMethods = new CommonPageActions(drivercontext);

    }

    public void navigateToHRMPortal(String HrmUrl) {
        driver.get(HrmUrl);
        assert (driver.getTitle()).equals("OrangeHRM");
        PgCommonMethods.waitUntilElementisClickable(bySubmitButton);
    }

    public void enterValidCred(String username, String password) {
        PgCommonMethods.FHElement(loginPageUserNameXpath, "User Name Field").sendKeys(username);
        PgCommonMethods.FHElement(loginPagePasswordXpath, "Password Field").sendKeys(password);
        PgCommonMethods.FHCElement(loginButtonXpath, "Login Button");
        assert (driver.getTitle()).equals("OrangeHRM");
        PgCommonMethods.FHElement(dashboardPageXpath,"Dashboard Name").isDisplayed();
        PgCommonMethods.FHElement(dashboardVerifyXpath,"Time at Work").isDisplayed();
    }

    private void navigateToPimModule() {
        PgCommonMethods.waitUntilElementinVisible(spinnerLoaderWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pimModule)).click();
        PgCommonMethods.waitUntilElementinVisible(spinnerLoaderWait);
    }

    private void navigateToAddEmployeeModule() {
        PgCommonMethods.waitUntilElementinVisible(spinnerLoaderWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployee)).click();
        PgCommonMethods.waitUntilElementinVisible(spinnerLoaderWait);
    }

    private void enterBasicDetailsForNewEmployee() {
        String firstName = "Pradeep" + RandomStringUtils.randomAlphabetic(2);
        String middleName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        PgCommonMethods.FHElement(addEmpFirstNameXpath, "First Name Field").sendKeys(firstName);
        PgCommonMethods.FHElement(addEmpMiddleNameXpath, "Middle Name Field").sendKeys(middleName);
        PgCommonMethods.FHElement(addEmpLastNameXpath, "Last Name Field").sendKeys(lastName);
    }

    private void createNewEmployeeData(String userName, String newPassword, String confirmPassword) {
        PgCommonMethods.FHCElement(createLoginSwitchButtonXpath, "Switch to Create Login Details");
        PgCommonMethods.waitUntilElementinVisible(spinnerLoaderWait);
        PgCommonMethods.FHElement(newLoginUserNameXpath, "New Login UserName filed").sendKeys(userName);
        PgCommonMethods.FHElement(newPasswordXpath, "New Password filed").sendKeys(newPassword);
        PgCommonMethods.FHElement(confirmPasswordXpath, "Confirm Password filed").sendKeys(confirmPassword);
    }

    private void assertErrorMessage() {
        String PortalErrorMessage = PgCommonMethods.FHElement(errorMessageXpath, "Error message field").getText();
        assert (PortalErrorMessage).equals("Passwords do not match");
    }

    private void saveEmployeeData() {
        PgCommonMethods.FHCElement(saveButtonXpath, "Save Button");
        PgCommonMethods.waitUntilElementinVisible(spinnerLoaderWait);
    }

    private void logOutFromPortal() {
        PgCommonMethods.FHCElement(userDropDownXpath, "User Drop Down");
        PgCommonMethods.FHCElement(logoutButtonXpath, "Logout button");
    }

    public void navigateToPIMModuleForTask1() throws InterruptedException {
        navigateToPimModule();
        navigateToAddEmployeeModule();
        enterBasicDetailsForNewEmployee();

        String userName = "Pradeep@" + RandomStringUtils.randomAlphabetic(3);
        String newPassword = "Automation" + RandomStringUtils.randomAlphabetic(5);
        String confirmPassword = RandomStringUtils.randomAlphabetic(10);
        createNewEmployeeData(userName, newPassword, confirmPassword);
        assertErrorMessage();
    }

    public void navigateToPIMModuleForTask2() throws InterruptedException {
        navigateToPimModule();
        navigateToAddEmployeeModule();
        enterBasicDetailsForNewEmployee();

        String userName = "Pradeep@" + RandomStringUtils.randomAlphabetic(3);
        String newPassword = "Aut@5" + RandomStringUtils.randomAlphabetic(5);

        createNewEmployeeData(userName,newPassword,newPassword);
        saveEmployeeData();
        logOutFromPortal();
        enterValidCred(userName, newPassword);

    }


}
