package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class
ProductPage extends AbstractPageWithParameterizedUrl {

    @FindBy(xpath = "//a[@class='button-link black-tr-b btn-pink ']")
    private WebElement addProductToOrderButton;

    @FindBy(xpath = "//div[@class='fastorder-popup']//button[contains(@class,'buy')]")
    private WebElement toMakeOrderButton;

    @FindBy(xpath = "//a[@id='js-btn-to-fav']")
    private WebElement addToFavoriteButton;

    @FindBy(xpath = "//a[contains(@class,'btn-ico-basket js-basket-popup-toggle')]")
    private WebElement goToOrderPageButton;

    @FindBy(xpath = "//a[@id='ico-favorites']")
    private WebElement goToFavoritePageButton;

    @FindBy(xpath = "//h1[@class='mf-product-title']//span[contains(text(),'Цвет')]")
    private WebElement colorSpan;

    @FindBy(xpath = "//span[@class='_price']")
    private WebElement productPrice;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductPage openPage(String urlPart) {
        driver.get("https://markformelle.by/catalog/" + urlPart);
        waitWebElementLocatedBy(By.xpath("//div[@class='header-menu-fixed show']"));
        try{
            driver.findElement(By.xpath("//a[@class='btn  btn--primary js-location-verify']")).click();
        }
        catch (Exception ex){}
        return this;
    }

    public ProductPage addProductToOrder() {
        addProductToOrderButton.click();
        jQueryAJAXCompleted();
        try{
            driver.findElement(By.xpath("//a[@class='btn  btn--primary js-location-verify']")).click();
        }
        catch (Exception ex){}
        return this;
    }

    public ProductPage addToFavorite() {
        addToFavoriteButton.click();
        try{
            driver.findElement(By.xpath("//a[@class='btn  btn--primary js-location-verify']")).click();
        }
        catch (Exception ex){}
        return this;
    }

    public boolean isFavoriteProduct(){
        waitWebElementLocatedBy(By.xpath("//div[@class='mf-footer-product']//a[contains(@class,'active')]"));
        return driver.findElements(By.xpath("//div[@class='mf-footer-product']//a[contains(@class,'active')]")).size()>0;
    }

    public ProductPage chooseProductColor(String color){
        ArrayList<WebElement>w=new ArrayList<WebElement>();
        driver.findElements(By.xpath("//ul[@class='mf-product-colorlist']//*[contains(@class,'mf-color-product')]"))
                .stream().filter(webElement -> webElement.getAttribute("title").equals(color)).forEach(webElement ->
                w.add(webElement));
        w.get(0).click();
        return this;
    }

    public ProductPage chooseCertificatePrice(int price){
        waitWebElementLocatedBy(By.xpath("//li[contains(@class,'js_prop_val_NOMINALNAYA_STOIMOST') and text()="+price+"]")).click();
        return this;
    }

    public double getCertificatePrice(){
        return getNumberByWebElementText(waitWebElementLocatedBy(By
                .xpath("//li[contains(@class,'js_prop_val_NOMINALNAYA_STOIMOST') and contains(@class,'selected')]")));
    }

    public OrderPage goToOrderPage(){
        goToOrderPageButton.click();
        return new OrderPage(driver);
    }

    public FavoritePage goToFavoritePage(){
        goToFavoritePageButton.click();
        return new FavoritePage(driver);
    }

    public String getColorProduct() {
        return colorSpan.getText();
    }

    public double getProductPrice(){
        return getNumberByWebElementText(productPrice);
    }

    private double getNumberByWebElementText(WebElement webElement){
        return Double.parseDouble(webElement.getText().replaceAll("[^\\d.]", ""));
    }
}
