package UsedCars;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class ChennaiUsedCars {
	String a;
	WebDriver driver;
	List<String>list =new ArrayList<String>();
	
	public ChennaiUsedCars(WebDriver driver) {
		this.driver= driver;
	}
	public void usedcars() {
		WebElement use = driver.findElement(By.xpath("//li/a[@href = '/used-car']"));
		Actions a =new Actions(driver);
		a.moveToElement(use).perform();
		
	}
	public void chennai() {
		WebElement chenn = driver.findElement(By.xpath("//li/span[@city='Chennai']"));
		chenn.click();
	}
	public void names() {
		WebElement models = driver.findElement(By.cssSelector("ul.zw-sr-secLev.usedCarMakeModelList.popularModels.ml-20.mt-10")) ;
		a= models.getText();
		String []arr = a.split("\n");
		for(String b: arr) {
			list.add(b);
		}
		
	}
	public void displayName() {
		for(String names :list) {
			System.out.println(names);
		
		}
		System.out.println("************************************************");	}
}
