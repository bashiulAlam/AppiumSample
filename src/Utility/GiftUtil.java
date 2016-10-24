package Utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GiftUtil {

	public static boolean isGiftEnabled(WebDriver webDriver) {

		if (webDriver
				.findElement(By
						.xpath("//android.widget.TextView[@resource-id='com.konai.konamoney:id/dashboard_gift_text_tv']"))
				.isEnabled())
			return true;
		else
			return false;
	}

	public static List<WebElement> getAvailableCards(WebDriver webDriver) {

		List<WebElement> availableCards = new ArrayList<>();
		availableCards = webDriver.findElements(By.xpath(
				"//android.widget.TextView[@resource-id='com.konai.konamoney:id/item_dashboard_sub_card_name_tv']"));

		return availableCards;
	}
}
