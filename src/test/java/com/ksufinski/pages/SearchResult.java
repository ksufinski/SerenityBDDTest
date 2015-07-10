package com.ksufinski.pages;

import com.ksufinski.models.ProductItem;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.ksufinski.PropertiesReader.readProperties;

public class SearchResult  extends PageObject {

    public Properties keyWords;
    {
        keyWords = readProperties("Inputs.properties");
    }

    @FindBy(xpath = "//*[@class='b-product-line b-product-line_size_wide']//img[@alt]")
    private WebElement searchedItemImgToClickOn;

    @FindBy(xpath = "//*[@class='b-product-line b-product-line_size_wide']//div[contains(@class,'available')]")
    private WebElement productInStock;

    @FindBy(xpath = "//input[@data-df-track-label='В наличии']")
    private WebElementFacade checkboxInStock;

    @FindBy(xpath = "//a[contains(text(),'Показать')]")
    private WebElementFacade linkShowSuggestions;



    public void chooseByFilterInStock(){
        checkboxInStock.waitUntilVisible();
        checkboxInStock.click();
        linkShowSuggestions.withTimeoutOf(4, TimeUnit.SECONDS).waitUntilPresent().click();
            }

    public ProductItem  selectItem(String articleNumber){
        int noa = Integer.parseInt(articleNumber);

        WebElement nameP = findBy("//*[@class='b-product-line b-product-line_size_wide']/div["+noa+"]//h3[@title]");
        WebElement img = findBy("//*[@class='b-product-line b-product-line_size_wide']/div[" + noa + "]//img[@alt]");
        String name = nameP.getText();
        img.click();

        return new ProductItem(name);

    }


}