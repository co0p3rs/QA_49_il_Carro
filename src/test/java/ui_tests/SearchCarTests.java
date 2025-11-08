package ui_tests;

import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

import java.time.LocalDate;

public class SearchCarTests extends ApplicationManager {

    HomePage homePage;
    @BeforeMethod
    public void openHomePage(){
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchPositiveTest(){
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 12, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 22);
        homePage.typeSearchForm(city, dateFrom, dateTo);
    }
}