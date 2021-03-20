import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
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
			idexPage.deleteAll();
		} catch (TimeoutException e) {
			System.out.println("Page: " + IndexPage.URL + " did not load within 30 seconds!");
		}

	}

	@AfterClass
	public static void tearDown() {

		driver.quit();
	}
	@Test
	public void createTaskTest() throws InterruptedException {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();

		if (idexPage.added(NAME, DESCRIPTION)) {

			idexPage.deleteAll();

		} else {

			idexPage.deleteAll();

			fail();
		}
	}
	public void preCreateTask() {
		idexPage.deleteAll();
		final String NAME2 = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION2 = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME2);
		formPage.enterTaskDescription(DESCRIPTION2);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
	}
	@Test
	public void editTaskTest() throws InterruptedException {
		preCreateTask();
		final String NAME = "Take out trash" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Thorw out bin " + Math.floor((Math.random() * 45) + 15);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if (idexPage.added(NAME, DESCRIPTION)) {
			idexPage.deleteAll();
		} else {
			idexPage.deleteAll();
			fail();
		}
	}
	@Test
	public void editTaskWithStepsTest() throws InterruptedException {
		preCreateTask();
		final String NAME = "Take out trash" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Thorw out bin " + Math.floor((Math.random() * 45) + 15);
		final String STEP = "Remove bin liner" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);

		formPage.submit();

		driver.switchTo().defaultContent();
		idexPage.exit();

		driver.switchTo().defaultContent();
		idexPage.clickBody();
		idexPage.clickBody();

		if (idexPage.addedStep(NAME, STEP)) {
			idexPage.deleteAll();
		} else {
			idexPage.deleteAll();
			fail();
		}
	}
	@Test
	public void editTaskWithExtraStepsTest() throws InterruptedException {
		preCreateTask();
		final String NAME = "Take out trash" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Thorw out bin " + Math.floor((Math.random() * 45) + 15);
		final String STEP = "Remove bin liner" + Math.floor((Math.random() * 55) + 5);
		final String STEP2 = "Open Door" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.add().sendKeys(STEP2);
		formPage.submit();

		driver.switchTo().defaultContent();
		idexPage.exit();

		driver.switchTo().defaultContent();
		idexPage.clickBody();
		idexPage.clickBody();

		if (idexPage.addedStep(NAME, STEP, STEP2)) {
			idexPage.deleteAll();
		} else {
			idexPage.deleteAll();
			fail();
		}
	}
	@Test
	public void createTaskWithStepTest() throws InterruptedException {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		final String STEP = "Attach Leash" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if (idexPage.addedStep(NAME, STEP)) {
			idexPage.deleteAll();
		} else {
			idexPage.deleteAll();
			fail();
		}
	}
	@Test
	public void createTaskWithStepsTest() throws InterruptedException {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		final String STEP = "Attach Leash" + Math.floor((Math.random() * 55) + 5);
		final String STEP2 = "Open Door" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.add().sendKeys(STEP2);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if (idexPage.addedStep(NAME, STEP, STEP2)) {
			idexPage.deleteAll();
		} else {
			idexPage.deleteAll();
			fail();
		}
	}
	@Test
	public void deleteTaskTest() {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();

		if (idexPage.added(NAME, DESCRIPTION)) {
			
			idexPage.clickDelete();
			if (idexPage.added(NAME, DESCRIPTION)) {
				fail();
				idexPage.deleteAll();

			} else {
				idexPage.deleteAll();
				
			}

		} else {
			idexPage.deleteAll();
			fail();
		}
		
		
	}
	@Test
	public void modifyStepTest() throws InterruptedException {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		final String STEP = "Attach Leash" + Math.floor((Math.random() * 55) + 5);
		final String STEP2 = "Open Door" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if (idexPage.addedStep(NAME, STEP)) {
			idexPage.clickCreate();
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			formPage.modify(0).sendKeys(Keys.chord(Keys.CONTROL,"a"));
			formPage.modify(0).sendKeys(STEP2);
			formPage.submit();
			driver.switchTo().defaultContent();
			idexPage.exit();
			driver.switchTo().defaultContent();
			if (idexPage.addedStep(NAME, STEP2)) {
				idexPage.deleteAll();
			} else {
				idexPage.deleteAll();
				fail();
		}
		} else {
			idexPage.deleteAll();
			fail();
	}
}
	
	@Test
	public void DeleteStepTest() throws InterruptedException {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		final String STEP = "Attach Leash" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if (idexPage.addedStep(NAME, STEP)) {
			idexPage.clickCreate();
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			formPage.modify(0).sendKeys(Keys.chord(Keys.CONTROL,"a"));
			formPage.modify(0).sendKeys(Keys.DELETE);
			formPage.submit();
			driver.switchTo().defaultContent();
			idexPage.exit();
			driver.switchTo().defaultContent();
			if (idexPage.addedStep(NAME, STEP)) {
				idexPage.deleteAll();
				fail();
			} else {
				idexPage.deleteAll();
				
		}
		} else {
			idexPage.deleteAll();
			fail();
	}
}
	@Test
	public void FlipStepTest() throws InterruptedException {
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		final String STEP = "Attach Leash" + Math.floor((Math.random() * 55) + 5);
		idexPage.clickCreate();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		formPage.enterTaskName(NAME);
		formPage.enterTaskDescription(DESCRIPTION);
		formPage.add().sendKeys(STEP);
		formPage.submit();
		driver.switchTo().defaultContent();
		idexPage.exit();
		driver.switchTo().defaultContent();
		idexPage.clickBody();
		if (idexPage.addedStep(NAME, STEP)) {
			idexPage.getStepCheck().click();
			if(idexPage.getStepCheck().isSelected()) {
				idexPage.deleteAll();
			}else {
				idexPage.deleteAll();
				fail();
			}
			
			
			
		} else {
			idexPage.deleteAll();
			fail();
		}
	}
}
