package Object_Package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Base.Base;
import GoogleLogin.LoginFail;
import UpcomingBikes.Bikes;
import UsedCars.ChennaiUsedCars;

public class Testing extends Base {
	
	WebDriver driver ;
	Bikes b;
	ChennaiUsedCars city;
	LoginFail l;
	 
	ExtentReports reports;
	ExtentHtmlReporter htmlreporter;
	
	
	@BeforeTest
	public void startTest() {
		reports = new ExtentReports();
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Reporting.html");
	    //this line redirect the path of my workspace
		
		reports.setSystemInfo("Machine", "YashChimade");
		reports.setSystemInfo("Browser", "Chrome");	
	}
	
	@BeforeSuite
	public void setup() {
		driver = Base.Start("chrome");
		b = new Bikes(driver);
		city = new ChennaiUsedCars(driver);
		l = new LoginFail(driver);
		}
	
	
	@Test(priority = 1)
	public void clickbikes() {
		reports.attachReporter(htmlreporter);
		ExtentTest Test1=reports.createTest("Test to validate functionality of NEWBIKES icon");
		Test1.log(Status.INFO, "Starting the 1st scenario");
		
		Test1.info("This will open zigwheels and click on the NEW BIKES");
		b.clickingBikes();
		Test1.pass("Passed");
	}
	
	
	@Test(priority = 2)
	public void upcoming() throws InterruptedException {
		reports.attachReporter(htmlreporter);
		ExtentTest Test2=reports.createTest("Test to validate functionality of Upcoming bikes");
		Test2.log(Status.INFO, "Starting the 2nd scenario");
		
		Test2.info("This will open zigwheels and click on the Upcoming bikes");
		
		b.upcomingbikes();
		Test2.pass("Passed");

	} 
	
	
	@Test(priority = 3 , dependsOnMethods = {"upcoming"})
	public void honda() throws InterruptedException {
		reports.attachReporter(htmlreporter);
		ExtentTest Test3=reports.createTest("Test to validate functionality of dropdown");
		Test3.log(Status.INFO, "Starting the 3rd scenario");
		
		Test3.info("This will select the honda bikes in the dropdown");
	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("makeId"))));
		b.selectinghonda();
		b.viewingmore();
		Test3.pass("Passed");
	}
	
	
	@Test(priority = 4 , dependsOnMethods = {"honda"})
	public void details() throws InterruptedException {
		reports.attachReporter(htmlreporter);
		ExtentTest Test4=reports.createTest("Test to validate the Information of total number of bikes and prices ");
		Test4.log(Status.INFO, "Starting the 4th scenario");
		
		Test4.info("This will print the infomation of number of total bikes and prices ");
		Thread.sleep(10000);

		b.names();
		b.lauch();
		b.prices();
		Test4.pass("Passed");
		
	}
	
	
	@Test(priority = 5,dependsOnMethods = {"details"})
	public void displayingdetails() {
		reports.attachReporter(htmlreporter);
		ExtentTest Test5=reports.createTest("Test to validate bike information with prices and launch details, where the bike price is below 4.00L");
		Test5.log(Status.INFO, "Starting the 5th scenario");
		
		Test5.info("This will print the infomation of bikes with prices and launch details where the bike price is less than 4.00L");

		b.display();
		Test5.pass("Passed");
	
	}
	
	
	@Test(priority = 6)
	public void alreadyused() {
		reports.attachReporter(htmlreporter);
		ExtentTest Test6=reports.createTest("Test to validate the Used Cars");
		Test6.log(Status.INFO, "Starting the 6th scenario");
		
		Test6.info("This will navigate to home page and then hover the mouse to used cars and click on Chennai");
		Navigation("https://www.zigwheels.com/");
		city.usedcars();
		city.chennai();
		Test6.pass("Passed");
		
	}
	
	
	@Test(priority =7 , dependsOnMethods= {"alreadyused"})
	public void displaycompanies() {
		reports.attachReporter(htmlreporter);
		ExtentTest Test7=reports.createTest("Test to validate the Information of companies");
		Test7.log(Status.INFO, "Starting the 7th scenario");
		
		Test7.info("This will check the company names in chennai");
		
		city.names();
		city.displayName();
		Test7.pass("Passed");
	}
	
	
	@Test(priority =8)
	public void login() {
		reports.attachReporter(htmlreporter);
		ExtentTest Test8=reports.createTest("Test to validate the Login button");
		Test8.log(Status.INFO, "Starting the 8th scenario");
		
		Test8.info("This will check the functionality of login");
		Navigation("https://www.zigwheels.com/");
		l.loginPage();
		l.googleLogin();
		Test8.pass("Passed");
		
	}
	
	
	@Test(priority = 9 , dependsOnMethods = {"login"})
	public void clickinggmail() throws InterruptedException {
		reports.attachReporter(htmlreporter);
		ExtentTest Test9=reports.createTest("Test to validate the gmail");
		Test9.log(Status.INFO, "Starting the 9th scenario");
		
		Test9.info("This will check the functionality of login");
		
		l.gmail();
		l.Screen_shot();
		Test9.pass("Passed");
	}
	
	
	@AfterSuite
	public void closeBrowser() {
		reports.flush();
		driver.quit();
	}
}
