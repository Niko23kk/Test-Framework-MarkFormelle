package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OrderPage extends AbstractPageWithStaticUrl {
    @FindBy(xpath = "//div[@id='popup-window-overlay-loading_screen']")
    private WebElement loadingPlaceholder;

    @FindBy(xpath = "//div[@class='bx-soa-cart-total-line bx-soa-cart-total-line-total']//span[@class='bx-soa-cart-d']")
    private WebElement totalPriceBasket;

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        waitPopupWindow();
    }

    @Override
    public OrderPage openPage() {
        driver.get("https://markformelle.by/personal/cart/");
        return this;
    }

    public OrderPage deleteProduct(int orderProduct){
        driver.findElement(By.xpath("//table[@id='basket-item-table']//tr["+orderProduct+"]" +
                "//span[@class='basket-item-actions-remove']")).click();
        waitPopupWindow();
        return this;
    }

    public boolean basketIsEmpty(){
        return waitWebElementLocatedBy(By.xpath("//div[@class='bx-sbb-empty-cart-container']"))!=null;
    }

    public double getTotalSumAllProductPrice(){
        List<WebElement> allPrice=driver.findElements(By.xpath("//td[@class='basket-items-list-item-price']" +
                "//span[@class='basket-item-price-current-text']"));
        return allPrice.stream().mapToDouble(element -> getNumberByWebElementText(element)).sum();
    }

    public double getProductPrice(int orderProduct){
        return getNumberByWebElementText(driver.findElement(By.xpath("//table[@id='basket-item-table']//tr["
                        +orderProduct+"]//td[contains(@class,'basket-items-list-item-price')]")));
    }

    public double getTotalProductPrice(int orderProduct){
        return getNumberByWebElementText(driver.findElement(By.xpath("//table[@id='basket-item-table']//tr["+orderProduct+
                "]//td[@class='basket-items-list-item-price']")));
    }

    public int getCountProduct(int orderProduct){
        return Integer.parseInt(driver.findElement(By.xpath("//table[@id='basket-item-table']//tr["+orderProduct+"]" +
                "//input[@class='basket-item-amount-filed']")).getAttribute("value"));
    }

    public OrderPage enlargeCountProduct(int orderProduct)
    {
        driver.findElement(By.xpath("//table[@id='basket-item-table']//tr["+orderProduct+"]" +
                "//span[@class='basket-item-amount-btn-plus']")).click();
        return this;
    }

    public long getCountBasketProducts(){
        return driver.findElements(By.xpath("//tr[@class='basket-items-list-item-container']")).stream().count();
    }

    public double getTotalPriceBasket(){
        return getNumberByWebElementText(totalPriceBasket);
    }

    private double getNumberByWebElementText(WebElement webElement){
        return Double.parseDouble(webElement.getText().replaceAll("[^\\d.]", ""));
    }

    private void waitPopupWindow(){
        waitWebElementLocatedBy(By.xpath("//div[@id='popup-window-overlay-loading_screen']"));
        waitWebElementInvisibilityOf(loadingPlaceholder);
    }
}
