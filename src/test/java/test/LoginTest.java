package test;

import static org.assertj.core.api.Assertions.*;

import javafx.scene.layout.Priority;
import model.User;
import org.testng.annotations.Test;
import page.AccountPage;
import page.HomePage;
import service.TestDataReader;
import service.UserCreator;

public class LoginTest extends CommonConditions{
    @Test
    public void userAccessTest(){
        User testUser= UserCreator.withAllProperty();
        HomePage homePage= new HomePage(driver)
                .openPage()
                .clickMenuAuthorizationButton()
                .inputEmail(testUser.getEmail())
                .inputPassword(testUser.getPassword())
                .clickAuthorizationButton();

        AccountPage accountPage= new AccountPage(driver)
                .openPage();

        assertThat(accountPage.getUserName()).contains(testUser.getUsername());
        assertThat(accountPage.getUserSurname()).isEqualTo(testUser.getUserSurname());
        assertThat(accountPage.getUserEmail()).isEqualTo(testUser.getEmail());
        assertThat(accountPage.getUserPhoneNumber()).isEqualTo(testUser.getPhoneNumber());
    }

    @Test
    public void forgotPasswordNonexistentUser(){
        HomePage homePage=new HomePage(driver)
                .openPage()
                .clickMenuAuthorizationButton()
                .clickForgotPasswordButton()
                .inputEmailWhenForgot(TestDataReader.getTestData("test.data.failemail"))
                .clickSendEmailButton();

        String currentUrl=homePage.getCurrentUrl();

        assertThat(homePage.getErrorMessageField()).isEqualTo("Профиль пользователя не найден.");
        assertThat(currentUrl).isEqualTo(homePage.getCurrentUrl());
    }
}
