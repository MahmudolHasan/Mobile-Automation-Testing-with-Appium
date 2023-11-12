import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class test2 {
    w3cUtilities feature = new w3cUtilities ();
    AndroidDriver driver;

    @BeforeTest
    public void OpenApp () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities ();
        caps.setCapability ("platformName", "android");
        caps.setCapability ("enforceXPath1", true);
        caps.setCapability ("automationName", "uiAutomator2");
        caps.setCapability ("deviceName", "local");
        caps.setCapability ("udid", "emulator-5554");
        caps.setCapability ("appPackage", "com.androidsample.generalstore");
        caps.setCapability ("appActivity", "com.androidsample.generalstore.SplashActivity");

        try {
            driver = new AndroidDriver (new URL ("http://192.168.100.4:4723/"), caps);
            feature.setDriver (driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException (e);
        }


    }

    @Test(priority = 1)
    public void ChooseCountry () throws InterruptedException {
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds (10));
        driver.findElement (By.id ("com.androidsample.generalstore:id/spinnerCountry")).click ();
        Thread.sleep (5000);
        feature.scrollUntilVisible ("Bangladesh");
        driver.findElement (By.xpath ("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Bangladesh\"]")).click ();
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds (3));
//        driver.findElement(By.xpath (
//                "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Algeria\"]")).click();
    }

    @Test(priority = 2) //,dependsOnMethods = { "ChooseCountry" }
    public void inputName () {
        driver.findElement (By.id ("com.androidsample.generalstore:id/nameField")).
                sendKeys ("Nate Tropoloz");
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds (5));
    }

    @Test(priority = 3)
    public void clickOnLtsShop () {
        driver.findElement (By.id ("com.androidsample.generalstore:id/btnLetsShop")).click ();
    }

    @Test(priority = 4)
    public void scrollToJordanLiftOff () throws InterruptedException {

        feature.scrollUntilVisible ("Jordan Lift Off");
        driver.findElement (By.xpath ("//android.widget.TextView[@text=\"Jordan Lift Off\"]")).click ();
        WebElement ele = driver.findElement (By.xpath (
                "//android.widget.TextView[@text=\"Jordan Lift Off\"]/..//following-sibling::android.widget.TextView[@text=\"ADD TO CART\"]"));
        ele.click ();
    }

    @Test(priority = 5)
    public void clickOncart () throws InterruptedException {
        driver.findElement (By.id ("com.androidsample.generalstore:id/appbar_btn_cart")).click ();
        driver.findElement (By.className ("android.widget.CheckBox")).click ();
        Thread.sleep (2000);
    }
    @Test(priority = 6)
    public void testAppTransition(){
        driver.findElement (By.id ("com.androidsample.generalstore:id/btnProceed")).click ();
    }

    @Test(priority = 6)
    public void visitWebsite () throws InterruptedException {
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds (10));
        WebElement ele = driver.findElement (By.xpath ("//android.widget.Image[@resource-id=\"hplogo\"]"));
        Assert.assertTrue (ele.isDisplayed (),"Didn't land on right page!");
        Thread.sleep (5000);


    }

    @AfterTest
    public void tearDown () {
        // driver.quit();

    }
}
