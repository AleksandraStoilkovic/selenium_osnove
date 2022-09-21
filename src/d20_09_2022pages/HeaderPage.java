package d20_09_2022pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderPage {
	private WebDriver driver;
//	HeaderPage koja pribavlja:
//		shop phone element - gde je prikazan broj telefona
//		contact us link
//		sign in link

	public HeaderPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getPhoneNumber() {
		return driver.findElement(By.xpath("//*[contains(@class, 'shop-phone')]/strong"));
	}

	public WebElement getContactUsLink() {
		return driver.findElement(By.xpath("//*[text()='Contact us']"));
	}

	public WebElement getSignInLink() {
		return driver.findElement(By.xpath("//*[contains(@class, 'login')]"));
	}
}
