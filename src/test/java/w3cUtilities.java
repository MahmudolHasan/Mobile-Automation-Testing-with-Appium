import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;

public class w3cUtilities {
    AndroidDriver driver;
    public void setDriver(AndroidDriver driver){
        this.driver = driver;
    }

    public void scrollUp(double scrollSpace)
    {
        Duration dur = Duration.ofMillis (400);
        Dimension size = driver.manage ().window ().getSize ();
        if(scrollSpace <=0 || scrollSpace >= 1) throw new Error ("scrollSpace should be between 0 to 1 exclusive!");
        System.out.println ("Size: "+size);
        Point midPoint = new Point (size.getWidth ()/2,size.getHeight ()/2);
        System.out.println ("MidPoint: "+midPoint);
        Point upPoint = new Point (midPoint.x, (int)(midPoint.y-(midPoint.y)*scrollSpace));
        swpie (new Point(midPoint.x, midPoint.y), new Point (upPoint.x, upPoint.y),dur);

    }


    public void swpie(Point start, Point end,Duration duration){
        PointerInput finger1 = new PointerInput (PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence (finger1, 0);
        swipe.addAction (finger1.createPointerMove (Duration.ZERO, PointerInput.Origin.viewport (), start.x, start.y));
        swipe.addAction (finger1.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        swipe.addAction (new Pause (finger1, Duration.ofMillis (100)));
        swipe.addAction (finger1.createPointerMove (duration, PointerInput.Origin.viewport (), end.x, end.y));
        swipe.addAction (finger1.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));


        driver.perform (ImmutableList.of (swipe));
    }
    public void scrollUntilVisible(String eleText) throws InterruptedException {
        driver.findElement (new AppiumBy.ByAndroidUIAutomator (
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + eleText + "\").instance(0))"
        ));
        scrollUp (.2);

        Thread.sleep (200); //wait of 10 seconds

    }
}
