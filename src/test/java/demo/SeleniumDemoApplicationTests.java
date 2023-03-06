package demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


@SpringBootTest
class SeleniumDemoApplicationTests {
	
	static final String DRIVER_PATH = "";
	
	WebDriver driver;
	
	private void initBrowser() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
	}

	@Test
	void invalidEntry() throws InterruptedException {
		
		//init the browser and navigate to the home page
		initBrowser();
		driver.get("http://localhost:8080/");
		Thread.sleep(2000); //for visual effect
		
		//move to the add page and try to add "abc"
		driver.findElement(By.linkText("Add Items")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("name")).sendKeys("abc");
		Thread.sleep(2000); //for visual effect
		driver.findElement(By.xpath("/html/body/form/button")).click();
		Thread.sleep(2000);
		String err = driver.findElement(By.className("validationError")).getText();
		Thread.sleep(1000); //for visual effect
		
		//assert that the proper error was displayed
		assertThat(err).isEqualTo("Name must be at least 5 characters long");
		driver.close();
	}
	
	@Test
	void validEntry() throws InterruptedException {
		
		String newItemName = "abcde";
		
		//init the browser and navigate to the home page
		initBrowser();
		driver.get("http://localhost:8080/");
		Thread.sleep(2000); //for visual effect
		
		//get the list of items (before add operation)
		List<String> itemsPre = driver.findElements(By.xpath("/html/body/ul/li"))
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
		
		//move to the add page and add newItemName
		driver.findElement(By.linkText("Add Items")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("name")).sendKeys(newItemName);
		Thread.sleep(2000); //for visual effect
		driver.findElement(By.xpath("/html/body/form/button")).click();
		Thread.sleep(2000);
		
		//get the list of items (after add operation)
		driver.findElement(By.linkText("View Items")).click();
		Thread.sleep(2000);
		List<String> itemsPost = driver.findElements(By.xpath("/html/body/ul/li"))
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
		driver.close();
		
		//assert the item "abcde" was added and no other changes were made
		assertThat(itemsPost.size() - itemsPre.size()).isEqualTo(1); //list is now one longer
		for (int i = 0; i < itemsPre.size(); i++) { //check that each element is still there
			assert itemsPre.get(i).equals(itemsPost.get(i));
		}
		assertThat(itemsPost.get(itemsPost.size()-1)).isEqualTo(newItemName);
	}
	
	//########### Assignment Tasks ###########
	
	//delete with invalid name
		//assert the proper error message
	
	//delete with new name
		//assert no change in items
	
	//delete with present name
		//assert only the proper item was removed (bottom most item with that name)

}
