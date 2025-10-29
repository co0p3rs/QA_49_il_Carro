package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {
    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("Password123!")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedDisplayed());
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        User user = User.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("wrong password")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedIncorrect());
    }

    @Test
    public void loginNegativeTest_emptyPassword(){
        User user = User.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
    }

    @Test
    public void loginNegativeTest_emailWOAt(){
        User user = User.builder()
                .username("bilbo_baggins_12345mail.com")
                .password("Password123!")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"));
    }
}