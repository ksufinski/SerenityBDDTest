package com.ksufinski.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

import static com.ksufinski.PropertiesReader.readProperties;



@DefaultUrl("http://dp.prom.ua/")
public class Home extends PageObject {

    public  Properties keyWords;
    {
        keyWords = readProperties("Inputs.properties");
    }

    private static final Integer CATALOG_WAIT_FOR_TIMEOUT = 20000;
    public Home(WebDriver driver) {super(driver, CATALOG_WAIT_FOR_TIMEOUT);}

    @FindBy(xpath = "//a[contains(@href,'sign-in')]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(@href,'comparison')]")
    private WebElement compareListButton;

    @FindBy(id= "search_submit")
    private WebElement searchSubmitButton;

    @FindBy(id="search_text")
    private WebElement searchInput;

    @FindBy(xpath ="//*[contains(@class,'search_autocomplete ')]/li/a")
    List<WebElementFacade> elements;



    public void checkSuggestionsForKeyword(String keyword){// но хочу чтобы был тип WebElementState, тогда нужно return

      for(WebElement element : elements){

          Assert.assertTrue("SUGGESTION ",element.getText().toLowerCase().contains(keyword));
      }
    }


    public void pressLoginButton () {
        Boolean a = true;
        do {
            try {
                loginButton.click();
                a=true;
            } catch (StaleElementReferenceException e) {
                System.err.print("StaleElementReferenceException failed! Try again...");
                a=false;

            } catch (NullPointerException e) {
                System.err.print("NullPointerException failed! Try again...");
                a=false;
            }
        }while (!a);
    }

    public void pressCompareListButton () {
        Boolean a = true;
        do {
            try {
                compareListButton.click();

                a=true;
            } catch (StaleElementReferenceException e) {
                System.err.print("StaleElementReferenceException failed! Try again...");
                a=false;

            } catch (NullPointerException e) {
                System.err.print("NullPointerException failed! Try again...");
                a=false;
            }
        }while (!a);
    }

    public void searchByKeyword(String keyword){element(searchInput).type(keyword);}

    public void pressSearchSubmitButton(){element(searchSubmitButton).click();}





}
