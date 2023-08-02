package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.PageObjects;



public class StepDefinition extends BaseClass{
	
    private PageObjects page;
    
    

	@Given("I am on the Mall.cz website")
    public void navigateToMallWebsite() {
        // Navigate to the Mall.cz website
		   setup();
        	page = new PageObjects(driver);
        	page.navigateToMallWebsite("https://www.mall.cz/");
    }
    
	
    @When("I search for {string}")
    public void searchForProduct(String product) {
        // Perform the search for the given product (e.g., "Phone")	
    	page.setSearchKeyword(product);
    }

    @When("I click on the search button")
    public void clickSearchButton() throws InterruptedException {
        // Click on the search button
    	page.clickSearchButton();
    }

    @Then("the search results page should be displayed")
    public void validateSearchResultsPage() {
        // Validate that the search results page is displayed
    	 assert page.isSearchResultsPageDisplayed();
    }

    @Then("the search results should contain items related to phones")
    public void validateSearchResultsContainPhones() {
        // Validate that the search results contain items related to phones
    	assert page.doSearchResultsContainPhones();
        driver.quit();
    
	}
}
