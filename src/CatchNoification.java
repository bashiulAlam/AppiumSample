import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import Utility.UtilityClass;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CatchNoification {

	AndroidDriver androidDriver;

	@BeforeTest
	public void setUp() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "CB5A25ZWQQ");

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

		capabilities.setCapability("platformName", "Android");

		// capabilities.setCapability("appPackage", "com.konai.konamoney");

		// capabilities.setCapability("appActivity",
		// "com.konai.konamoney.ui.splash.SplashActivity");

		capabilities.setCapability("appPackage", "com.android.calculator2");

		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

		try {

			androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		androidDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	}

	@Test
	public void catchPushNotification() {

		androidDriver.openNotifications();

		List<WebElement> allNotifications = androidDriver.findElements(By.id("android:id/title"));
		List<WebElement> allNotificationTexts = androidDriver.findElements(By.id("android:id/text"));
		List<String> text = new ArrayList<>();
		for (WebElement webElement : allNotifications) {

			AndroidElement textView = (AndroidElement) webElement;
			System.out.println("Text found : " + textView.getText());
			text.add(textView.getText());
		}

		Map<String, String> konaMoneyNotifiation = new HashMap<String, String>();

		int notificationCount = allNotifications.size();
		for (int counter = 0; counter < notificationCount; counter++) {

			if (allNotifications.get(counter).getText().equals("Gift card")) {
				String message = allNotificationTexts.get(counter).getText();
				konaMoneyNotifiation.put(allNotifications.get(counter).getText(), message);

			}

		}

		if (konaMoneyNotifiation.size() > 0) {
			UtilityClass.printList(konaMoneyNotifiation);
		} else {
			System.out.println("There was no Kona Money Notification");
		}
	}

	@AfterTest
	public void End() {
		androidDriver.quit();
	}
}
