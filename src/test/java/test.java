import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;


public class test {
    AndroidDriver driver;

    @Test(priority = 0)
    public void runApp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities ();
        cap.setCapability ("platformName", "android");
        cap.setCapability ("automationName", "uiAutomator2");
        cap.setCapability ("deviceName", "local");
        cap.setCapability ("udid", "emulator-5554");
        cap.setCapability ("appPackage", "com.tct.calculator");
        cap.setCapability ("appActivity", "com.tct.calculator.Calculator");

        try {
             driver = new AndroidDriver (new URL ("http://192.168.100.3:4723/"),cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException (e);
        }
    }
    @Test(priority = 1)
    public void naviagteToMainPage() throws InterruptedException {

        WebElement enter = (WebElement) driver.findElement(By.id ("com.tct.calculator:id/btn_start"));
        enter.click ();


    }
    @Test(priority = 3)
    public void swipeFpage() throws InterruptedException {
        swipeRightToLeft ();
    }

    @Test(priority = 4)
    public void swipeSpage() throws InterruptedException {
        swipeRightToLeft ();
    }



    public void swipeRightToLeft() throws InterruptedException {

       // WebElement f1page =(WebElement) driver.findElement (By.id ("com.tct.calculator:id/currency_ani_imageview"));

    //Size determination and store
            Thread.sleep (10000);
            Dimension deviceSize = driver.manage ().window ().getSize ();
            int deviceWidth = deviceSize.getWidth ();
            int deviceHeight = deviceSize.getHeight ();

            int startx = (int) (deviceWidth *.8);
            int endx = (int) (deviceWidth * .4);
            int starty = deviceHeight / 2;
            int endy = deviceHeight / 2;
            PointerInput finger1 = new PointerInput (PointerInput.Kind.TOUCH, "finger1");
            Sequence swipe = new Sequence (finger1, 0);
            swipe.addAction (finger1.createPointerMove (Duration.ZERO, PointerInput.Origin.viewport (), startx, starty));
            swipe.addAction (finger1.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
            swipe.addAction (new Pause (finger1, Duration.ofMillis (100)));
            swipe.addAction (finger1.createPointerMove (Duration.ofMillis (200), PointerInput.Origin.viewport (), endx, endy));
            swipe.addAction (finger1.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

            driver.perform (Collections.singleton (swipe));

}

/*
 WebElement fpage = (WebElement) driver.findElement (AppiumBy.accessibilityId ("currency_ani_imageview"));
    driver.executeScript ("gesture : swipe", ImmutableMap.of("elementid",fpage.getAttribute ("id"),"percentage",50,"direction","left"));
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException (e);
    }
    WebElement spage = (WebElement) driver.findElement (AppiumBy.accessibilityId ("com.tct.calculator:id/convert_ani_imageview"));
    System.out.println ("Second" );
    driver.executeScript ("gesture : swipe", ImmutableMap.of("elementid",spage.getAttribute ("id"),"percentage",50,"direction","left"));
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException (e);
    }

    WebElement tpage = (WebElement) driver.findElement (AppiumBy.accessibilityId ("convert_ani_imageview"));
    System.out.println ("Second" );
    driver.executeScript ("gesture : swipe", ImmutableMap.of("elementid",tpage.getAttribute ("id"),"percentage",50,"direction","left"));
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException (e);
    }
    WebElement lbtn = (WebElement) driver.findElement (By.id ("com.tct.calculator:id/splash_converter_enter"));
    lbtn.click ();
 */
}
