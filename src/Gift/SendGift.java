package Gift;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

import org.openqa.selenium.WebElement;
import Utility.GiftUtil;
import Helper.Constants;
import bsh.util.Util;

public class SendGift {

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
			androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void testMethod01_selectOptionCard() {

		if (GiftUtil.isGiftEnabled(androidDriver)) {
			GiftUtil.clickGiftBtn(androidDriver);
		} else {
			List<WebElement> availableCards = GiftUtil.getAvailableCards(androidDriver);
			
			for (WebElement card : availableCards) {
				
				if (card.getText() != "Kona Money") {
					card.click();
					androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
					if(GiftUtil.isGiftEnabled(androidDriver)) {
						
						GiftUtil.clickGiftBtn(androidDriver);
						Constants.sendGift = true;
						androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
						break;
					} 
				} else {
					System.out.println("Could not find any card that can be sent as a gift...");
				}
			}
		}
	}

	@AfterTest
	public void End() {
		androidDriver.quit();
	}
}
