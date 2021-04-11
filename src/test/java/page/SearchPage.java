package page;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import page.FavoritePage;
import page.ProductPage;
import service.ProductCreator;
import service.TestDataReader;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPageWithStaticUrl {
    @FindBy(xpath = "//button[@class='input_sbmt']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@class='input_text api-search-input']")
    private WebElement searchField;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public SearchPage openPage() {
        driver.get("https://markformelle.by/search/");
        return this;
    }

    public SearchPage inputInSearchField(String search){
        searchField.sendKeys(search);
        return this;
    }

    public SearchPage clickSearchButton(){
        searchButton.click();
        return this;
    }

    public List<String> getAllProductName(){
        List<String> list =new ArrayList<>();
        driver.findElements(By.xpath("//div[@class='catalo-info']//a[@class='catalog-name__link']"))
                .stream().forEach(s->list.add(s.getText()));
        return list;
    }

}
