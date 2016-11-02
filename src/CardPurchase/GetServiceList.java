package CardPurchase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.mobile.AddNetworkConnection;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import Utility.CardPurchaseUtil;

public class GetServiceList {

	WebDriver webDriver;
	AndroidDriver androidDriver;
	List<WebElement> purchaseBtn = new ArrayList();
    
	@Parameters({ "deviceName_", "URL_" })
	@BeforeTest
	public void setUp(String deviceName_, String URL_) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", deviceName_);

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.konai.konamoney");

		capabilities.setCapability("appActivity", "com.konai.konamoney.ui.splash.SplashActivity");

		try {
			//androidDriver = new AndroidDriver(new URL("http://127.0.0.2:3456/wd/hub"), capabilities);
			androidDriver = new AndroidDriver(new URL("http://"+URL_), capabilities);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void testMethod01_getServiceList() {

		androidDriver
				.findElement(By
						.xpath("//android.widget.ImageButton[@resource-id='com.konai.konamoney:id/top_right_image_btn']"))
				.click();
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void testMethod02_swipeDown() throws InterruptedException {

		Dimension dimension = androidDriver.manage().window().getSize();
		int starty, startx;
		int count = 0;
		while (count < 13) {

			purchaseBtn = androidDriver.findElements(By.className("android.widget.Button"));
			count += purchaseBtn.size();
			System.out.println("Total cards encountered so far : " + count);
			
			starty = (int) (dimension.getHeight() * 0.80);
			int endy = (int) (dimension.getHeight() * 0.20);
			startx = dimension.width / 2;
			System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

			// Swipe from Bottom to Top. driver.swipe(startx, starty, startx, starty,
			androidDriver.swipe(startx, starty, startx, endy, 3000);
		}
	}

	@Test
	public void testMethod03_purcaseCard() throws InterruptedException {

		// click the purchase button to pull the recharge page
		purchaseBtn.get(purchaseBtn.size() - 1).click();
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean isRechargeable = CardPurchaseUtil.checkCardType(androidDriver);

		if (isRechargeable) {

			CardPurchaseUtil.purchaseRechargeableCardWithStaticAmount(androidDriver);
		}

		// purchase card
		androidDriver
				.findElement(By.xpath("//android.widget.Button[@resource-id='com.konai.konamoney:id/btn_issue_card']"))
				.click();
		androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// confirm purchase
		androidDriver
				.findElement(
						By.xpath("//android.widget.Button[@resource-id='com.konai.konamoney:id/dialog_right_btn']"))
				.click();
		androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterTest
	public void End() {
		androidDriver.quit();
	}
}
