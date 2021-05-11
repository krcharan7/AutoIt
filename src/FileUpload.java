import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FileUpload {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Chrome_89\\chromedriver.exe");
		String defaultDownldPath = System.getProperty("user.dir");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", defaultDownldPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://altoconvertpdftojpg.com/");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("button[id='dropzoneInput-label']")).click();
		Thread.sleep(5000);
		Runtime.getRuntime().exec("C:\\Users\\charan\\Downloads\\FileUploadAutoIt.exe");
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=' pages to be converted:']/strong")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)");
		//Thread.sleep(2000);
		WebElement clickNow = driver.findElement(By.xpath("//button[contains(text(),'Convert Now!')]"));
		w.until(ExpectedConditions.elementToBeClickable(clickNow));
		driver.findElement(By.xpath("//button[contains(text(),'Convert Now!')]")).click();
		System.out.println("convert now is clicked");
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span[class='icon.icon-drop']")));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span[class='icon.icon-drop']")));
		driver.findElement(By.linkText("Download")).click();
		WebElement closePopup = driver.findElement(By.cssSelector("button[aria-label='Close Popup']"));
		if (closePopup.isDisplayed()) {
			closePopup.click();
		}
		Thread.sleep(3000);
		File f = new File(defaultDownldPath + "\\1.jpg");
	
			Assert.assertTrue(f.exists(),".jpg file is downloaded");

			if (f.delete())
				{System.out.println("file deleted");}

			driver.close();
		
	}

}
