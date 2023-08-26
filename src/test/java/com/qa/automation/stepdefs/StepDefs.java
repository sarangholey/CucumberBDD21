package com.qa.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefs {
	
	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	WebDriver driver;
	String baseUrl = "https://www.amazon.in/";
	int implictlyWaitTimeoutSec = 20;
	Scenario scn;
	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn = scn;
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\saran\\eclipse-workspace\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		logger.info("Setting up for browser path is completed");
		ChromeOptions opt = new ChromeOptions();
		opt.setBinary("C:\\Users\\saran\\eclipse-workspace\\chrome-win64\\chrome-win64\\chrome.exe");
		logger.info("Setting up chrome binary");

		driver = new ChromeDriver(opt);
		scn.log("Browser Got Opened");
		logger.info("Browser Got Opened");
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		logger.info("Browser got maximized");
		driver.manage().deleteAllCookies();
		logger.info("Browser cookies got deleted");
		driver.manage().timeouts().implicitlyWait(implictlyWaitTimeoutSec, TimeUnit.SECONDS);
		logger.info("Implicit timeout set for " + implictlyWaitTimeoutSec);
	}
	
	@After
	public void cleanUp()
	{
		driver.quit();
		scn.log("Browser got closed");
		logger.info("Browser got closed");
	}

//	@Given("user opened browser")
//	public void user_opened_browser() {
//
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\saran\\eclipse-workspace\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
//
//		ChromeOptions opt = new ChromeOptions();
//		opt.setBinary("C:\\Users\\saran\\eclipse-workspace\\chrome-win64\\chrome-win64\\chrome.exe");
//
//		driver = new ChromeDriver(opt);
//
////		WebDriverManager.firefoxdriver().setup();
////		WebDriver driver = new FirefoxDriver();
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(implictlyWaitTimeoutSec, TimeUnit.SECONDS);
//	}

	@Given("user navigate to the home application url")
	public void user_navigate_to_the_home_application_url() {

		driver.get(baseUrl);
		scn.log("User navigated to appication URL as -> " + baseUrl);
		logger.info("User navigated to appication URL as -> " + baseUrl);
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		Assert.assertEquals("The title of home page for the application is not matched as expexted", expectedTitle,
				actualTitle);
		logger.info("Home page title validation is successful with title as " + actualTitle);

	}

	@When("user search for product {string}")
	public void user_search_for_product(String productName) {

		WebElement searchBoxEle = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(searchBoxEle));

		searchBoxEle.sendKeys(productName);
		logger.info("Sent keys in searchbox to find a product as " + productName);
		scn.log("Searching for a product name as -> " + productName);

		WebElement searchBtnEle = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchBtnEle.click();
		logger.info("Clicked on search button");

	}

	@Then("Search result page is displayed with page title keyword contains {string}")
	public void search_result_page_is_displayed_with_page_title_keyword_contains(String titleProdNameKeyword) {

		String expectedtitle = "Amazon.in : " + titleProdNameKeyword;

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains(titleProdNameKeyword));

		Assert.assertEquals(expectedtitle, driver.getTitle());
		
		scn.log("Product search result is displayed for prod name -> " + titleProdNameKeyword);
		logger.info("Product search result is displayed containing the prod name in title as with keyword " + titleProdNameKeyword);

		//driver.quit();
	}

	@When("User click on any product")
	public void user_click_on_any_product() {
		//listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
        logger.info("Captured the list of products");
        //But as this step asks click on any link, we can choose to click on Index 0 of the list
        listOfProducts.get(0).click();
        logger.info("Clicked on the first product form the captured list");

	}

	@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {
		
		//As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        logger.info("captured the window handles");
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String original = it.next();//gives the parent window id
        String prodDescp = it.next();//gives the child window id

        driver.switchTo().window(prodDescp); // switch to product Descp
        scn.log("switched to the new tab");
        logger.info("switched to the new tab");


        //Now driver can access new driver window, but can not access the orignal tab
        //Check product title is displayed
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        Assert.assertEquals("Product Title is not matching ",true,productTitle.isDisplayed());
        logger.info("Product with title in new tab is displayed");

        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        Assert.assertEquals("Add to cart button is not displayed",true,addToCartButton.isDisplayed());
        logger.info("Att to cart button is displayed");

        //Switch back to the Original Window, however no other operation to be done
        driver.switchTo().window(original);
        logger.info("driver switched back to original window");

	}
	
//	@Then("Browser is closed")
//	public void browser_is_closed() {
//	    driver.quit();
//	}

}
