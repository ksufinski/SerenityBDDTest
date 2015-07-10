package com.ksufinski;


import com.ksufinski.models.ProductItem;
import com.ksufinski.steps.serenity.UserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static com.ksufinski.PropertiesReader.readProperties;



@RunWith(SerenityRunner.class)
@Concurrent(threads="1")
public class Tests
{

    public  Properties keyWords;
    {
        keyWords = readProperties("Inputs.properties");
    }

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://dp.prom.ua")
    public Pages pages;

    @Before
    public  void myTest()  throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        webdriver.manage().window().maximize();

    }



    @Steps
    public static UserSteps  user;

    @WithDriver("chrome")



    @Test
    public void showSuggestionsTheSameAsSearchingInput() throws Exception
    {
        //GIVEN
        user.openHomePage();
        //WHEN
        user.searchingFor(keyWords.getProperty("keyword"));
        //THEN
        user.checkThatSuggestionContainsKeyword(keyWords.getProperty("keyword").toLowerCase());
    }

    @Test
    public void userCanAddItemsToFavorites() throws InterruptedException
    {
        //GIVEN
        user.loginFromHomePageWithEmail(keyWords.getProperty("email"),keyWords.getProperty("password"));
        //WHEN
        user.searchingFor(keyWords.getProperty("keyword"));
        user.submitSearching();
        user.setFilterInStock();
        ProductItem selectedItem = user.selectsItem(keyWords.getProperty("article"));
        user.addsCurrentItemToFavorites();
        user.openFavorites();
        //THEN
        user.shouldSeeItemInCart(selectedItem);
    }

   @Ignore@Test
    public void userCanDeleteItemFromFavorites(){
        user.cleaningFromFavorites();

    }




}
