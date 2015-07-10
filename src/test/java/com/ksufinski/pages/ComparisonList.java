package com.ksufinski.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class ComparisonList extends PageObject{





    @FindBy(xpath = "//a[contains(@id,'link_to_product')]")
    private WebElementFacade productNameFromCompList;

    @FindBy(xpath = "//span[contains(@class,'remove')]")
    private WebElementFacade buttonDelete;

    @FindBy(xpath = "//div[contains(text(),'В избранном пусто')]")
    private WebElementFacade emptyFavorites;

    public String getProductNameFromCL(){

        Boolean a = true;
        do {
            try {
                 productNameFromCompList.getText();
                a=true;

            } catch (StaleElementReferenceException e) {
                System.err.print("StaleElementReferenceException failed! Try again...");
                a=false;

            } catch (NullPointerException e) {
                System.err.print("NullPointerException failed! Try again...");
                a=false;
            }catch (NoSuchElementException e) {
                System.err.print("NoSuchElementException failed! Try again...");
                a=false;
            }

        }while (!a);
        return productNameFromCompList.getText();

    }

    public void deleteFromFavorites(){
        buttonDelete.waitUntilVisible().click();
    }




}
