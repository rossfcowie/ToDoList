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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserAcceptanceTests {
	private static WebDriver driver;
	IndexPage idexPage = new IndexPage(driver);
	FormPage formPage = new FormPage(driver);
    static ExtentReports  report = new ExtentReports("src/test/resources/reports/User_Acceptance_Report.html", true);
    static ExtentTest test;
    
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
		report.flush();
        driver.quit();
	}
	@Test
	public void createTaskTest() throws InterruptedException {
		test = report.startTest("Create task test");
		final String NAME = "Walk the dog" + Math.floor((Math.random() * 55) + 5);
		test.log(LogStatus.INFO,"Given task name was: " + NAME);
		final String DESCRIPTION = "Take the dog out for a " + Math.floor((Math.random() * 45) + 15) + " minuite walk";
		test.log(LogStatus.INFO,"Given task description was was: " + DESCRIPTION);
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
			test.log(LogStatus.PASS, "Ok");
			idexPage.deleteAll();
			report.endTest(test);
		} else {
			test.log(LogStatus.FAIL, "Could not find name and description pair.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
		
	}
	
	//Function to create a task and return to main page.
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
		test = report.startTest("Edit task test");
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
			test.log(LogStatus.PASS, "Ok");
			idexPage.deleteAll();
			report.endTest(test);
		} else {
			test.log(LogStatus.FAIL, "Could not find updated name and description pair.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
	}
	@Test
	public void editTaskWithStepsTest() throws InterruptedException {
		preCreateTask();
		test = report.startTest("Edit task, add step, test");
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
			test.log(LogStatus.PASS, "Ok");
			idexPage.deleteAll();
			report.endTest(test);
		} else {
			test.log(LogStatus.FAIL, "Could not find updated name and step pair.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
	}
	@Test
	public void editTaskWithExtraStepsTest() throws InterruptedException {
		preCreateTask();
		test = report.startTest("Edit task, multiple steps, test");
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
			 test.log(LogStatus.PASS, "Ok");
			idexPage.deleteAll();
			report.endTest(test);
		} else {
			test.log(LogStatus.FAIL, "Could not find updated name and steps trio.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
	}
	@Test
	public void createTaskWithStepTest() throws InterruptedException {
		test = report.startTest("Create task with step test");
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
			test.log(LogStatus.PASS, "Ok");
			idexPage.deleteAll();
			report.endTest(test);
		} else {
			test.log(LogStatus.FAIL, "Could not find updated name and step pair.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
	}
	@Test
	public void createTaskWithStepsTest() throws InterruptedException {
		test = report.startTest("Create task with steps test");
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
			 test.log(LogStatus.PASS, "Ok");
			idexPage.deleteAll();
			report.endTest(test);
		} else {
			test.log(LogStatus.FAIL, "Could not find name and steps trio.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
	}
	@Test
	public void deleteTaskTest() {
		test = report.startTest("Delete task test");
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
			test.log(LogStatus.INFO, "Task created sucessfully.");
			idexPage.clickDelete();
			if (idexPage.added(NAME, DESCRIPTION)) {
				test.log(LogStatus.FAIL, "Task was not deleted.");
				report.endTest(test);
				fail();
				idexPage.deleteAll();

			} else {
				test.log(LogStatus.PASS, "Task deleted sucessfully.");
				idexPage.deleteAll();
				report.endTest(test);
				
			}

		} else {
			test.log(LogStatus.FAIL, "Task was not created sucessfully.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
		
		
	}
	@Test
	public void modifyStepTest() throws InterruptedException {
		test = report.startTest("Edit task, rename step test");
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
				test.log(LogStatus.PASS, "Ok");
				
				idexPage.deleteAll();
				report.endTest(test);
			} else {
				test.log(LogStatus.FAIL, "Could not find updated name and step pair.");
				idexPage.deleteAll();
				report.endTest(test);
				fail();
		}
		} else {
			test.log(LogStatus.FAIL, "Could not find created name and step pair.");
			idexPage.deleteAll();
			report.endTest(test);
			fail();
	}
}
	
	@Test
	public void DeleteStepTest() throws InterruptedException {
		test = report.startTest("Edit task, delete step, test");
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
				test.log(LogStatus.FAIL, "Could find name and step when expected to be deleted.");
				idexPage.deleteAll();
				report.endTest(test);
				fail();
			} else {
				test.log(LogStatus.PASS, "Ok");
				idexPage.deleteAll();
				report.endTest(test);
				
		}
		} else {
			test.log(LogStatus.FAIL, "Could not find name and step when expected to be present.");
			idexPage.deleteAll();
			report.endTest(test);
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
				test.log(LogStatus.PASS, "Ok");
				idexPage.deleteAll();
				report.endTest(test);
			}else {
				test.log(LogStatus.FAIL, "Checkbox status not toggled on as expected.");
				idexPage.deleteAll();
				report.endTest(test);
				fail();
			}
			
			
			
		} else {
			test.log(LogStatus.FAIL, "Could not find name and step when expected to be present.");
			
			idexPage.deleteAll();
			report.endTest(test);
			fail();
		}
	}
}
