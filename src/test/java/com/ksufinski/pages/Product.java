package com.ksufinski.pages;

import com.ksufinski.models.ProductItem;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import java.util.concurrent.TimeUnit;


@DefaultUrl("http://dp.prom.ua/")
public class Product extends PageObject {


    @FindBy(xpath = "//*[contains(@class,'comparison-handler')]")
    private WebElementFacade AddToFavoritesButton;

    @FindBy(xpath = "//span[contains(@id,'link_to_product')]")
    private WebElementFacade name;

    @FindBy(xpath = "//div[contains(@name,'popup')]//span[contains(text(),'Посмотреть')]")
    private WebElementFacade suggestionShow;


    public ProductItem getProductName(){
        String productName = name.getText();
        return new ProductItem(productName);
    }

    public void clickOnAddToFavoritesButton(){
        AddToFavoritesButton.withTimeoutOf(5,TimeUnit.SECONDS).waitUntilPresent().click();
    }


}
