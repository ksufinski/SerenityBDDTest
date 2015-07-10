package com.ksufinski.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@DefaultUrl("https://my.prom.ua/cabinet/sign-in")
public class SignIn  extends PageObject{

    private static final Integer CATALOG_WAIT_FOR_TIMEOUT = 20000;
    public SignIn(WebDriver driver) {super(driver, CATALOG_WAIT_FOR_TIMEOUT);}

    @FindBy(xpath = ".//*[@id='phone_email']")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElementFacade passwordInput;

    @FindBy(id = "submit_login_button")
    private WebElementFacade submitLoginButton;

    public void enterEmail(String emailInputText) {element(emailInput).type(emailInputText);}

    public void enterPassword(String password){passwordInput.sendKeys(password);}

    public void clickLoginButton(){submitLoginButton.click();}
}
