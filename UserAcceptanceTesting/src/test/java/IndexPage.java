import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	public static final String URL = "http://127.0.0.1:5500/WebPage/Index.html";
	private WebDriver driver;
	
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "cross")
	private WebElement exitBtn;
	
	@FindBy(id="formButton")
	private WebElement create;
	
	@FindBy(id="deleteButton")
	private WebElement delete;
	
	@FindBy(id="TasksList")
	private WebElement taskContainer;
	
	@FindBy(id = "page")
	private WebElement body;
	
	public void clickCreate() {
		create.click();
	}
	public void clickDelete() {
		delete.click();
	}
	
	public List<WebElement> getTasks(){
		return taskContainer.findElements(By.className("Container"));
	}
	
	public boolean added(String taskName) {
		List<WebElement> tasks = getTasks();
		for (WebElement task : tasks) {
			WebElement a = task.findElement(By.tagName("h2"));
			if(a.getText().equals(taskName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean added(String taskName, String description) {
		List<WebElement> tasks = getTasks();
		for (WebElement task : tasks) {
			System.out.println(task);
			WebElement a = task.findElement(By.tagName("h2"));
			WebElement b = task.findElement(By.tagName("h4"));
			if(a.getText().equals(taskName)) {
				if(b.getText().equals(description)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean addedStep(String taskName,String step) {
		List<WebElement> tasks = getTasks();
		for (WebElement task : tasks) {
			WebElement a = task.findElement(By.tagName("h2"));
			WebElement b;
			try {
				b = task.findElement(By.tagName("span"));
				
			} catch (Exception e) {
				return false;
			}
			
			
			if(a.getText().equals(taskName)) {
				if(b.getText().equals(step)) {
					return true;
				}
			}
		}
		return false;
	}
	public List<WebElement> getSteps(){
		return taskContainer.findElements(By.className("ContainerScrollable"));
	}
	public void deleteAll() {
		List<WebElement> tasks = getTasks();
		for (WebElement task : tasks) {
			task.click();
			clickDelete();
		}
	}
	public void exit() {
		exitBtn.click();
	}
	public void clickBody() {
		body.click();
	}
	public boolean addedStep(String name, String stepText, String step2Text) {
		List<WebElement> tasks = getTasks();
		for (WebElement task : tasks) {
			WebElement a = task.findElement(By.tagName("h2"));
			if(a.getText().equals(name)) {
				boolean found =false;
				List<WebElement> steps = task.findElements(By.tagName("span"));
				for (WebElement step : steps) {
					if(step.getText().equals(stepText)) {
						if(found) {
							return true;
						}else {
							found = true;
						}
					}else {
						if(step.getText().equals(step2Text)) {
							if(found) {
								return true;
							}else {
								found = true;
							}
						}
					}
				}
			}
			
		}
		return false;
	}
	
}
