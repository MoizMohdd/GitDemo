package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;
	
//	The class has a static method named "Start," which takes a parameter "browsername" as a string
	public static WebDriver Start(String browsername) {
		if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.zigwheels.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		}
		else if(browsername.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.zigwheels.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		}else {
			System.out.println("Enter valid Browser name");
		}
		return driver;
}

	public static void Navigation(String url) {
		driver.navigate().to(url);
	}
	public static void Stop() {
		driver.quit();
	}

}
