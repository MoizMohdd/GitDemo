package UpcomingBikes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Bikes {
	
	WebDriver driver;
	public Bikes(WebDriver driver) {
		
		this.driver=driver;
		
	}

	List<String> Names = new ArrayList<String>();
	List<String> Prices = new ArrayList<String>();
	List<String> ExpectedDate = new ArrayList<String>();
	List<String> NewListNames = new ArrayList<String>();
	List<Integer> NewListPrices = new ArrayList<Integer>();
	List<String> NewListExpectedDate = new ArrayList<String>();


	public void clickingBikes(){
		WebElement New_Bikes = driver.findElement(By.xpath("//a[contains(text(),'New Bikes')]"));
		Actions a =new Actions(driver);
		a.moveToElement(New_Bikes).perform();
		
	}
	
	public void upcomingbikes() {
		WebElement Upcoming = driver.findElement(By.xpath("//span[@onclick=\"goToUrl('/upcoming-bikes')\"]"));
		Upcoming.click();
	}
	
	public void selectinghonda() {
		WebElement Honda_Bikes_Dropdown = driver.findElement(By.id("makeId"));
		Select s  = new Select(Honda_Bikes_Dropdown);
		s.selectByVisibleText("Honda");
	}
	
	public void viewingmore() throws InterruptedException {
		Thread.sleep(5000);
		WebElement View_More = driver.findElement(By.xpath("//*[text()='View More Bikes ']"));
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", View_More);
	  
	}
	
	public void  names() {
		List<WebElement> Bike_Names = driver.findElements(By.xpath("//div/a[@data-track-label = 'model-name']"));
		for(WebElement a :Bike_Names) {
			Names.add(a.getText());
			
		}
		System.out.println("There are "+ Names.size()+ " Honda New model bikes");
		
	}
	
	public void lauch() {
		
		List<WebElement> Bike_Expected_Date= driver.findElements(By.cssSelector("div.clr-try.fnt-14")) ;
		for(WebElement a :Bike_Expected_Date) {
			ExpectedDate.add(a.getText());
		}
		System.out.println("There are "+ExpectedDate.size()+" Expected dates");
	}
	
	public void  prices() {
		List<WebElement> Bike_Prices = driver.findElements(By.cssSelector("div.b.fnt-15"));
		
		for(WebElement a :Bike_Prices) {
			Prices.add(a.getText());
		}
	
		for(int i=0;i<Prices.size();i++) {
		System.out.println(Names.get(i)+"----->"+Prices.get(i));
		} 
		for(int i = 0 ; i<Prices.size();i++) {
			if(Prices.get(i).contains("Lakh")) {
				String[] arr= Prices.get(i).split("\\s");
				float lakhs = Float.parseFloat(arr[1]);
				lakhs = (int)(lakhs*100000);
				if(lakhs<400000) {
					
					NewListNames.add(Names.get(i));
					NewListPrices.add((int)lakhs);
					NewListExpectedDate.add(ExpectedDate.get(i));
				}
			}else {
				int thousands = Integer.parseInt(Prices.get(i).replaceAll("[^0-9]", ""));
				if(thousands<400000) {
					NewListNames.add(Names.get(i));
					NewListPrices.add(thousands);
					NewListExpectedDate.add(ExpectedDate.get(i));
				}
			}
		
		}
		System.out.println("************************************************");
	}
	
	public void display() {
		System.out.println("There are total " + NewListNames.size()+ " names of Bikes"); // For reference it prints how many names are there
		System.out.println("There are total " + NewListPrices.size()+ " prices of Bikes");// For reference it prints how many prices are there
		System.out.println("************************************************");
		try {
		   File file = new File(System.getProperty("user.dir") + "\\Reports\\Data.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook();
			 XSSFSheet sh = workbook.createSheet("Bike Details");
			
			 for(int i = 0 ; i<NewListNames.size();i++) {
					System.out.println(NewListNames.get(i)+"----> Rs."+NewListPrices.get(i)+"---->"+NewListExpectedDate.get(i));
					Row a =sh.createRow(i);
					Cell c = a.createCell(1);
					Cell c1 = a.createCell(2);
					Cell c3 =a.createCell(3);
					
					c.setCellValue(NewListNames.get(i));
					c1.setCellValue(NewListPrices.get(i));
					c3.setCellValue(NewListExpectedDate.get(i));
					
				}
			 FileOutputStream fos= new FileOutputStream(file);
			 workbook.write(fos);
			 workbook.close();
			 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------");
	}

}
