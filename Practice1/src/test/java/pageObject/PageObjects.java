package pageObject;

import java.time.Duration; // Import the Duration class
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjects {

    private WebDriver driver;
    private WebDriverWait wait; // Add WebDriverWait instance

    // Constructor
    public PageObjects(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10)); 
        PageFactory.initElements(driver, this); // Initialize elements with PageFactory
    }

    // Elements on the page
    @FindBy(id = "site-search-input")
    private WebElement searchInput;

    @FindBy(id = "search-button")
    private WebElement searchButton;

    @FindBy(css = ".search-results")
    private WebElement searchResults;

    // Methods to interact with the page

    public void navigateToMallWebsite(String url) {
        driver.get(url);
    }
    
    

    public void setSearchKeyword(String keyword) {
        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(searchInput)).clear();
                searchInput.sendKeys(keyword);
                success = true;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                // Element is stale, retry after a short wait
                attempts++;
            }
        }
    }

    
    
    

    public void clickSearchButton() throws InterruptedException {
    	/*Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();*/
    	Actions actions = new Actions(driver);
    	actions.moveToElement(searchButton).click().perform();

    }


    public boolean isSearchResultsPageDisplayed() {
        try {
            // Wait for the element to be present in the DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search-results")));
            // Wait for the element to be visible on the page
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-results"))).isDisplayed();
        } catch (TimeoutException e) {
            // Element is not displayed within the timeout
            return false;
        }
    }
    

    public boolean doSearchResultsContainPhones() {
        List<WebElement> searchResultItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='category-menu-item__title']")));

        for (WebElement searchResultItem : searchResultItems) {
            if (searchResultItem.getText().toLowerCase().contains("phone")) {
                return true;
            }
        }

        return false;
    }
}
