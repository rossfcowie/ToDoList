import java.util.List;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {
	public static final String URL = "http://127.0.0.1:5500/WebPage/Form.html";
	private WebDriver driver;
	
	@FindBy(id="taskName")
	private WebElement taskNameBox;
	
	@FindBy(id="description")
	private WebElement taskDescriptionBox;
	
	@FindBy(id="submitButton")
	private WebElement submitButton;

	@FindBy(tagName="button")
	private WebElement addButton;
	
	@FindBy(id="newStepsContaier")
	private WebElement stepContainer;
	

	
	public FormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement add() {
		addButton.click();
		int i= stepContainer.findElements(By.tagName("input")).size();
		return stepContainer.findElement(By.id("Step"+i));
		}
	public WebElement modify(int i) {
		return stepContainer.findElement(By.id("Step"+i));
		}
	public void enterTaskName(String str) {
		taskNameBox.sendKeys(str);
	}
	public void enterTaskDescription(String str) {
		taskDescriptionBox.sendKeys(str);
	}
	public void submit() {
		submitButton.click();
	}


}
