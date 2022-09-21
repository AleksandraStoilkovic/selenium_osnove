package d20_09_2022pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopMenuPage {
	private WebDriver driver;
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
		return driver.findElement(By.xpath("//*[text()='Dresses']"));
	}
	public WebElement getTShirtsLink() {
		return driver.findElement(By.xpath("//*[text()='T-shirts']"));
	}
	public WebElement getWomanSubmenu() {
		return driver.findElement(By.xpath("//*[contains(@class, 'submenu-container')]"));
	}
	public WebElement getDressesSubmenu() {
		return driver.findElement(By.xpath("//*[contains(@class, 'submenu-container')]"));
	}
}
