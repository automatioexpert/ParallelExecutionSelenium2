package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBrowser {

	//public static WebDriver driver; static WebDriver is not used in case of parallel execution
	public ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	
	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
		
	}

	public WebDriver getDriver() {
		return driver.get();
	}
	@Parameters("browser")
	@Test
	public void browser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
		}
		else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			setDriver(new InternetExplorerDriver());
		}
		
		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
		}
		
		
		getDriver().manage().window().maximize();
		getDriver().get("https://www.google.com");
		System.out.println(getDriver().getTitle());
		getDriver().navigate().refresh();
		getDriver().get(getDriver().getCurrentUrl());
		getDriver().quit();
	}
}
