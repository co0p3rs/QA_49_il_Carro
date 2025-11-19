package ui_tests;


import dto.Car;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.enums.HeaderMenuItem;

import java.util.Random;

public class LetCarWorkTests extends ApplicationManager {
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void login(){
        User user = User.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("Password123!")
                .build();
        loginPage = new HomePage(getDriver()).clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user);
        //loginPage.clickBtnOk();
        letTheCarWorkPage = loginPage.clickButtonHeader(HeaderMenuItem.LET_THE_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel-"+ new Random().nextInt(10000))
                .pricePerDay(100.77)
                .about("about")
                .image("004.jpg")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        Assert.assertTrue(letTheCarWorkPage.btnOkPopUpPresent());
    }

    @Test
    public void addNewCarNegativeTest_woManufacture(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("")
                .model("Astra")
                .year("2020")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel-"+ new Random().nextInt(10000))
                .pricePerDay(100.77)
                .about("about")
                .image("004.jpg")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Make is required"));
    }

}