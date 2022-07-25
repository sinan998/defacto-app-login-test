
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {

    AndroidDriver<MobileElement> driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Nexus 5");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "com.defacto.android");
        caps.setCapability("appActivity","com.defacto.android.MainActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
    }

    @Test
    public void tests() throws InterruptedException {
        goToLoginPage();
        login();

    }


    public void goToLoginPage(){
        waitElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
        MobileElement permissionButton = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        permissionButton.click();

        waitElement(MobileBy.AccessibilityId("Yeniler"));
        MobileElement news = (MobileElement) driver.findElementByAccessibilityId("Yeniler");
        news.click();

        waitElement(MobileBy.AccessibilityId("Hayır, Teşekkürler."));
        MobileElement NoThanksButton = (MobileElement) driver.findElementByAccessibilityId("Hayır, Teşekkürler.");
        NoThanksButton.click();

        waitElement(MobileBy.AccessibilityId("Hesabım\nTab 5 of 5"));
        MobileElement accountButton = (MobileElement) driver.findElementByAccessibilityId("Hesabım\nTab 5 of 5");
        accountButton.click();
    }


    public void login() throws InterruptedException {
        waitElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]"));
        Thread.sleep(2000);
        MobileElement emailLocator = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]");
        emailLocator.click();
        emailLocator.sendKeys("hepol58357@aregods.com");

        MobileElement passwordLocator = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]");
        passwordLocator.click();
        Thread.sleep(2000);
        passwordLocator.sendKeys("Asdf.123");

        Thread.sleep(2000);
        driver.hideKeyboard();
        MobileElement loginButton = (MobileElement) driver.findElementByAccessibilityId("Giriş Yap");
        loginButton.click();

        waitElement(MobileBy.AccessibilityId("Yeniler\nTab 1 of 6"));
        MobileElement news = (MobileElement) driver.findElementByAccessibilityId("Yeniler\nTab 1 of 6");
        Assertions.assertTrue(news.isDisplayed(),"Login was failed");


    }



    public void waitElement(By locator){
        WebDriverWait wait=new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }









}
