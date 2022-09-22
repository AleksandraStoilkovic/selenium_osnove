package d20_09_2022pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuPage {
	private WebDriver driver;
	private WebDriverWait wait;
//	TopMenuPage koja pribavlja:
//		metodu koja vraca WOMEN link iz glavnom menija
//		metodu koja vraca DRESSES link iz glavnom menija
//		metodu koja vraca T-SHIRTS link iz glavnom menija
//		metodu koja vraca podmeni za WOMEN
//		metodu koja vraca podmeni za DRESSES

	public TopMenuPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public WebElement getWomenLink() {
		return driver.findElement(By.xpath("//*[text()='Women']"));
	}
	public WebElement getDressesLink() {
		return driver.findElements(By.xpath("//*[text()='Dresses']")).get(1);
	}
	public WebElement getTShirtsLink() {
		return driver.findElements(By.xpath("//*[text()='T-shirts']")).get(1);
	}
	public WebElement getWomanSubmenu() {
		return driver.findElements(By.xpath("//*[contains(@class, 'submenu-container')]")).get(0);
	}
	public WebElement getDressesSubmenu() {
		return driver.findElements(By.xpath("//*[contains(@class, 'submenu-container')]")).get(1);
	}
	
}
