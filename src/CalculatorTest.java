import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculatorTest {

	WebDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "LGD8565677d967");

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.android.calculator2");

		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

		
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Sum() {
		// Click on DELETE/CLR button to clear result text box before running
		// test.
		driver.findElements(By.xpath("//android.widget.ImageButton[@resource-id='com.android.calculator2:id/allClear']")).get(0).click();

		// Click on number 2 button.
		driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.android.calculator2:id/digit2']")).click();

		// Click on + button.
		driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.android.calculator2:id/plus']")).click();

		// Click on number 5 button.
		driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.android.calculator2:id/digit5']")).click();

		// Click on = button.
		driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.android.calculator2:id/equal']")).click();

		// Get result from result text box.
		String result = driver.findElement(By.className("com.android.calculator2.CalculatorEditText")).getText();
		System.out.println("Number sum result is : " + result);

	}

	@AfterTest
	public void End() {
		driver.quit();
	}
}
