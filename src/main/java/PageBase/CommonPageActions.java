package PageBase;

import Configuration.DriverContext;
import CustomReport.CustomReporter;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class CommonPageActions extends PageBase {
    public CommonPageActions(DriverContext drivercontext) {
        super(drivercontext);
    }

    public WebElement CheckIfElementExist(final By loc, String fieldName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 25);
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
            boolean isDisplayed = ele.isDisplayed();
            if (isDisplayed) {

                return ele;
            } else {
                return null;
            }

        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            //add custom report and ss if required
            return null;
        } catch (WebDriverException e) {
            e.fillInStackTrace();
            return null;

        } catch (Exception e) {
            e.fillInStackTrace();
            return null;

        }
    }

    public void EnterDataInFormattedText(WebElement we, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + value + "';", we);
    }

    public WebElement waitUntilElementisClickable(By loc) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 65);
            return wait.until(ExpectedConditions.elementToBeClickable(loc));

        } catch (TimeoutException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public boolean waitUntilElementinVisible(By loc) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 65);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(loc));


        } catch (TimeoutException e) {
            e.fillInStackTrace();
            return false;
        }

    }

    public void changebrowserTab(int i) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (tabs.size() == 1) {
            System.out.print("fail");
        } else {
            driver.switchTo().window(tabs.get(i));
        }
    }

    public boolean changebrowserTabandReturnBoolean(int i) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (tabs.size() == 1) {
            System.out.print("fail");
            return false;
        }
        if (i >= tabs.size()) {
            System.out.print("fail");
            return false;
        } else {
            driver.switchTo().window(tabs.get(i));
//			checkStateAndRefresh();
        }
        return true;
    }

    public void closeAndSwitchToWindow() {
        try {
            driver.close();
            driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        } catch (Exception e) {

        }
    }

    public void switchToPopuoWindow() {
        String subwindohandel = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            subwindohandel = it.next();
        }
        driver.switchTo().window(subwindohandel);
    }

    public void switchOutFromPopupwindoe(String parrent) {
        driver.switchTo().window(parrent);
    }

    public void WaitForUserDefinedTime(long waittime) {
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//	public boolean checkStateAndRefresh() {
//		try {
//			String errmsg="//div[@class="error-code"][text()="Err_EMpty_response"]";
//			By errorXpath=By.xpath(errmsg);
//			String errmsg2="//h1[text()='Internal server error- read']";
//			if(CheckIfElementExistNoErrorLogged(errorXpath,"error msg")) {
//				driver.navigate().refresh();
//				return true;
//				
//			}
//		}catch() {
//			
//		}
//	return false;
//	}

    public boolean CheckIfElementExistNoErrorLogged(final By loc, String filedname, int... time) {
        try {
            int sec = 1;
            if (time.length != 0) {
                sec = time[0];
            }
            WebDriverWait wait = new WebDriverWait(driver, sec);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));

            boolean isDisplayed = element.isDisplayed();

            if (isDisplayed) {

                return true;
            } else {
                return false;
            }

        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            //add custom report and ss if required
            return false;
        }

    }

    public WebElement GetElement(By loc, String fb) {
        WebElement el = CheckIfElementExist(loc, fb);
        return el == null ? null : el;
    }

    public void mouseHoverElement(WebElement web1) {
        Actions act = new Actions(driver);
        act.moveToElement(web1).build().perform();

    }

    public void mouseOverElement(WebElement web1, WebElement web2, WebElement web3) {
        Actions act = new Actions(driver);
        act.moveToElement(web1).perform();
        act.moveToElement(web2).perform();
        act.moveToElement(web3).click().perform();
    }

    public void switchToWindowByIndex(String windoIndex) {
        int wndIndex = Integer.parseInt(windoIndex);
        Set<String> han = driver.getWindowHandles();
        if (han.size() > wndIndex) {
            String handle = han.toArray()[wndIndex].toString();
            driver.switchTo().window(handle);
            CustomReporter.ReportTestStepStatus(Status.INFO, "Switched to Requested window");

        } else {
//			S.o.p(fail);
            CustomReporter.ReportTestStepStatus(Status.FAIL, "Requested window is out of range");
        }


    }

    public void ScrollUntilElementVisible(WebElement we) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
    }

    public void highlightElement(WebElement we, String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", we);
    }

    public WebElement FHElement(String xpath, String name) {
        By locator = By.xpath(xpath);
        WebElement we = GetElement(locator, name);
        if (we == null) {
            CustomReporter.ReportTestStepStatus(Status.FAIL, "Cannot find Element by xpath-> " + xpath);
            return null;
        }
        ScrollUntilElementVisible(we);
        highlightElement(we, name);
        WaitForUserDefinedTime(300);
        return we;

    }

    public void clickOnButton(WebElement we, String name) {
        if (we == null)
            return;
        ScrollUntilElementVisible(we);
        JavaScriptClick(we);
        CustomReporter.ReportTestStepStatus(Status.INFO, "Clicked on Button " + name);
    }

    private void JavaScriptClick(WebElement we) {
        // TODO Auto-generated method stub
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", we);
    }

    public void FHCElement(String xpath, String name) {
        clickOnButton(FHElement(xpath, name), name);
        WaitForUserDefinedTime(300);
    }


}