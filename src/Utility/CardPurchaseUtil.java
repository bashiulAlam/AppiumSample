package Utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class CardPurchaseUtil {

	public static boolean checkCardType(AndroidDriver androidDriver) {
		List<WebElement> enterAmount = androidDriver.findElements(By.xpath(
				"//android.widget.EditText[@resource-id='com.konai.konamoney:id/card_service_detail_recharge_amount_et']"));

		if (enterAmount.size() == 1)
			return true;
		else
			return false;
	}

	public static void purchaseRechargeableCard(AndroidDriver androidDriver) {

		// enter recharge amount
		// recharge 10k
		androidDriver
				.findElement(By.xpath("//android.widget.Button[@resource-id='com.konai.konamoney:id/btn_amount_3']"))
				.click();
		// recharge 5k
		androidDriver
				.findElement(By.xpath("//android.widget.Button[@resource-id='com.konai.konamoney:id/btn_amount_4']"))
				.click();

		androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	public static void purchaseRechargeableCardWithStaticAmount(AndroidDriver androidDriver) {
		
		androidDriver.findElement(By.xpath(
				"//android.widget.EditText[@resource-id='com.konai.konamoney:id/card_service_detail_recharge_amount_et']"))
		.sendKeys("20000");
	}
}
