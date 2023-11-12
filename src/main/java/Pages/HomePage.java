package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    WebElement countryOption;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Bangladesh\"]")
    WebElement countryName;

    @FindBy(xpath = "com.androidsample.generalstore:id/nameField")
    WebElement nameInputField;

    @FindBy(xpath = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement letsShopBtn;

    public void chooseCountry(String input)
    {

    }

}
