package GoogleLogin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFail {
		public LoginFail(WebDriver driver) {
			this.driver=driver;
		}

	

	WebDriver driver;
	public void loginPage() {
		WebElement Login = driver.findElement(By.xpath("//*[@data-target = '#myModal3']"));
		Login.click();
	}
	public void googleLogin() {
		 WebElement GoogleAcct=driver.findElement(By.xpath( "//*[@data-track-label= 'Popup_Login/Register_with_Google']"));
		GoogleAcct.click();
	}
	public void gmail() throws InterruptedException {
 
		for (String window : driver.getWindowHandles()) {
		driver.switchTo().window(window);
		}
		
		WebElement emailid=driver.findElement(By.id("identifierId"));
	
		emailid.sendKeys("yash123@yash1@1");
		WebElement submit = driver.findElement(By.id("identifierNext"));
		submit.click();
		Thread.sleep(8000);
		
		
		WebElement next = driver.findElement(By.xpath("//*[text()='Next']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",next);
		
		WebElement errormessage = driver.findElement(By.cssSelector("div.o6cuMc.Jj6Lae"));
		String error_message = errormessage.getText();
		System.out.println(error_message);
		
		
	}
	public void Screen_shot() 
    {

        String abc = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());        
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(SrcFile,new File("./Screenshot/" + abc + "loginfail.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screen shot taken");
        System.out.println("--------------------------------------------");

    }
	
}
