package Utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class GiftUtil {

	public static boolean isGiftEnabled(AndroidDriver androidDriver) {

		if (androidDriver
				.findElement(By
						.xpath("//android.widget.LinearLayout[@resource-id='com.konai.konamoney:id/dashboard_gift_btn_ll']"))
				.getAttribute("clickable").equals("true"))
			return true;
		else
			return false;
	}
	
	public static void clickGiftBtn(AndroidDriver androidDriver) {
		
		androidDriver
		.findElement(By
				.xpath("//android.widget.LinearLayout[@resource-id='com.konai.konamoney:id/dashboard_gift_btn_ll']"))
		.click();
	}

	public static List<WebElement> getAvailableCards(AndroidDriver androidDriver) {

		List<WebElement> availableCards = new ArrayList<>();
		availableCards = androidDriver.findElements(By.xpath(
				"//android.widget.TextView[@resource-id='com.konai.konamoney:id/item_dashboard_sub_card_name_tv']"));

		return availableCards;
	}
}
