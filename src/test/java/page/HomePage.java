package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPageWithStaticUrl{
    @FindBy(xpath = "//a[@class='header-profile js-popup-modal-input']")
    private WebElement authorizationMenuButton;

    @FindBy(xpath = "//div[@class='block login_btn_sign']//input")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//div[@class='choose-block email']//td[@class='btn_block login_btn_sign']/input")
    private WebElement authorizationButton;

    @FindBy(xpath = "//a[@class='header-profile']")
    private WebElement goToUserButton;

    public HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @Override
    public HomePage openPage()
    {
        driver.navigate().to("https://markformelle.by/");
        return this;
    }

    public AccountPage clickGoToUserButton(){
        goToUserButton.click();
        try {
            goToUserButton.click();
        }
        catch (Exception ex){}
        return new AccountPage(driver);
    }

    public HomePage clickMenuAuthorizationButton(){
        authorizationMenuButton.click();
        try {
            authorizationMenuButton.click();
        }
        catch (Exception ex){}
        return this;
    }

    public HomePage clickAuthorizationButton(){
        authorizationButton.click();
        return this;
    }

    public HomePage clickForgotPasswordButton(){
        waitWebElementLocatedBy(By.xpath("//a[@class='popup-modal-input forgot']")).click();
        return this;
    }

    public HomePage inputEmailWhenForgot(String email){
        waitWebElementLocatedBy(By.xpath("//div[@class='block']//input")).sendKeys(email);
        return this;
    }

    public HomePage inputEmail(String email){
        waitWebElementLocatedBy(By.xpath("//div[@class='choose-block email']//tbody//tr[1]//input")).sendKeys(email);
        return this;
    }

    public HomePage inputPassword(String password){
        waitWebElementLocatedBy(By.xpath("//div[@class='choose-block email']//tbody//tr[2]//input")).sendKeys(password);
        return this;
    }

    public String getErrorMessageField(){
        return waitWebElementLocatedBy(By.xpath("//div[@class='error_message_form']")).getText();
    }

    public HomePage clickSendEmailButton(){
        sendEmailButton.click();
        return this;
    }
}
