package Wallet;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Login {

	WebDriver webDriver;
	AndroidDriver androidDriver;
	
	@BeforeTest
	public void setUp() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "CB5A25ZWQQ");

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.konai.konamoney");

		capabilities.setCapability("appActivity", "com.konai.konamoney.ui.splash.SplashActivity");

		try {
			androidDriver = new AndroidDriver(new URL("http://127.0.0.1:1234/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test
	public void testMethod01_login() {
		
		androidDriver
		.findElement(By
				.xpath("//android.widget.EditText[@resource-id='com.konai.konamoney:id/edit_password']")).sendKeys("112233");
		
		androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		androidDriver
		.findElement(By
				.xpath("//android.widget.LinearLayout[@resource-id='com.konai.konamoney:id/login_btn_ll']"))
		.click();
		
		androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void End() {
		androidDriver.quit();
	}
}
