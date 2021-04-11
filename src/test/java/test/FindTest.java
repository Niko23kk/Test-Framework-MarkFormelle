package test;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;
import service.TestDataReader;

public class FindTest extends CommonConditions{
    @Test
    public void checkCorrectFindByName() {
        SearchPage searchPage= new SearchPage(driver)
                .openPage()
                .inputInSearchField(TestDataReader.getTestData("test.data.find"))
                .clickSearchButton();

        SoftAssertions softAssertions= generateSoftAssertionWithContains(searchPage.getAllProductName(),"test.data.find");
        softAssertions.assertAll();
    }
}
