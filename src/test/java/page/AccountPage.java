package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends AbstractPageWithStaticUrl {

    @FindBy(xpath = "//input[@class='user_name']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@class='last_user_name']")
    private WebElement userSurnameField;

    @FindBy(xpath = "//input[@class='user_email']")
    private WebElement userNameEmail;

    @FindBy(xpath = "//input[@class='phone_user']")
    private WebElement userPhoneNumber;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public AccountPage openPage() {
        driver.get("https://markformelle.by/personal/");
        return this;
    }

    public String getUserName(){
        return userNameField.getAttribute("value");
    }

    public String getUserSurname(){
        return userSurnameField.getAttribute("value");
    }

    public String getUserEmail(){
        return userNameEmail.getAttribute("value");
    }

    public String getUserPhoneNumber(){
        return userPhoneNumber.getAttribute("value");
    }
}
