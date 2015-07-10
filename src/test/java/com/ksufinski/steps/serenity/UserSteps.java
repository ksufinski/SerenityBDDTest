package com.ksufinski.steps.serenity;


import com.ksufinski.models.ProductItem;
import com.ksufinski.pages.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;


public class UserSteps extends ScenarioSteps {

    Home home ;
    SignIn signIn;
    Product product;
    SearchResult searchResult;
    ComparisonList comparisonList;


    public UserSteps(Pages pages)
    {
        super(pages);
    }


    @Step("Given I have opened home page")
    public void openHomePage(){
        home.open();
    }


    @Step("When I have searched for {0}")
    public void searchingFor(String keyword){
        home.open();
        home.searchByKeyword(keyword);

    }

    @Step("And press submit button")
    public void submitSearching(){
        home.pressSearchSubmitButton();
    }

    @Step("Then Suggestions should contains {0}")
    public void checkThatSuggestionContainsKeyword(String keyword){
        home.checkSuggestionsForKeyword(keyword);
    }


    @Step("Given I have logged in")
    public void loginFromHomePageWithEmail(String emailInputText, String password){
        home.open();
        home.pressLoginButton();
        signIn.enterEmail(emailInputText);
        signIn.enterPassword(password);
        signIn.clickLoginButton();
    }

    @Step("And when I apply filter")
    public void setFilterInStock(){
        searchResult.chooseByFilterInStock();
    }

    @Step("And when I have chosen item by number in list")
    public ProductItem selectsItem(String articleNumber) {

        return searchResult.selectItem(articleNumber);

    }

    @Step("And when I have added item for favorites")
    public void addsCurrentItemToFavorites(){
        product.clickOnAddToFavoritesButton();

    }

    @Step("And when I have opened favorites")
    public void openFavorites(){
        home.pressCompareListButton();
    }

    @Step("Then {0} should be in favorites")
    public void shouldSeeItemInCart(ProductItem selectedItem) {

        Assert.assertEquals("text of assertion in product names(expected, actual) ",selectedItem.getName(),comparisonList.getProductNameFromCL());
    }




    @Step("Clean from favorites")
    public void cleaningFromFavorites(){
        home.pressCompareListButton();
        comparisonList.deleteFromFavorites();
    }


}

