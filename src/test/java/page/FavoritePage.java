package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavoritePage extends AbstractPageWithStaticUrl{

    @FindBy(xpath = "//span[@id='favorites-count']")
    private WebElement numberOfFavoriteProduct;

    public FavoritePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public FavoritePage openPage(){
        driver.get("https://markformelle.by/personal/favorites/");
        return this;
    }

    public int getSizeFavoriteProductPanel(){
        return driver.findElements(By.xpath("//div[@class='mf-cart-list']")).size();
    }

    public int getNumberOfFavoriteProduct(){
        jQueryAJAXCompleted();
        waitWebElementLocatedBy(By.xpath("//span[@id='favorites-count' and text()>'0']"));
        return Integer.parseInt(numberOfFavoriteProduct.getText());
    }
}
