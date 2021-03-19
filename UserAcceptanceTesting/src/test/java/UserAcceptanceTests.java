import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UserAcceptanceTests {
	private static WebDriver driver;
	IndexPage idexPage = new IndexPage(driver);
	FormPage formPage = new FormPage(driver);
	
	@BeforeClass
	public static void setup() {
   	 System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
   	 ChromeOptions cOptions = new ChromeOptions();
   	 
   	 cOptions.setHeadless(false);
   	 cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
   	 cOptions.setCapability("network.cookie.cookieBehavior", 2);
   	 cOptions.setCapability("profile.block_third_party_cookies", true);
   	 driver = new ChromeDriver(cOptions);
        driver.manage().window().setSize(new Dimension(1366, 768));
   }
    @Before
	public void pre() {
    	
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		try {
			driver.get(IndexPage.URL);
			
		} catch (TimeoutException e) {
			System.out.println("Page: " + IndexPage.URL + " did not load within 30 seconds!");
		}
		
	}

	@AfterClass
	public static void tearDown() {
		
		driver.quit();
	}
	@Test
	public void createTask() throws InterruptedException {
    	final String NAME = "Walk the dog" +  Math.floor((Math.random() * 55) + 5);
    	final String DESCRIPTION = "Take the dog out for a " +  Math.floor((Math.random() * 45) + 15) +" minuite walk";
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if(idexPage.added(NAME,DESCRIPTION)) {
			idexPage.deleteAll();	
		}else {
			fail();
		}
	}
	@Test
	public void createTaskWithStep() throws InterruptedException {
    	final String NAME = "Walk the dog" +  Math.floor((Math.random() * 55) + 5);
    	final String DESCRIPTION = "Take the dog out for a " +  Math.floor((Math.random() * 45) + 15) +" minuite walk";
    	final String STEP = "Attach Leash" +  Math.floor((Math.random() * 55) + 5);
    	idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if(idexPage.addedStep(NAME,STEP)) {
			idexPage.deleteAll();
		}else {
			fail();
		}
	}
	@Test
	public void createTaskWithSteps() throws InterruptedException {
    	final String NAME = "Walk the dog" +  Math.floor((Math.random() * 55) + 5);
    	final String DESCRIPTION = "Take the dog out for a " +  Math.floor((Math.random() * 45) + 15) +" minuite walk";
    	final String STEP = "Attach Leash" +  Math.floor((Math.random() * 55) + 5);
    	final String STEP2 = "Open Door" +  Math.floor((Math.random() * 55) + 5);
    	idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.add().sendKeys(STEP2);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if(idexPage.addedStep(NAME,STEP2)) {
			idexPage.deleteAll();
		}else {
			fail();
		}
	}
}
