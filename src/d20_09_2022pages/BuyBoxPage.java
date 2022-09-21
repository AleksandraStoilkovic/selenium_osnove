package d20_09_2022pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BuyBoxPage {
//	BuyBoxPage koja pribavlja:
//		input za kolicinu
//		select za velicinu
//		add to cart dugme
//		add to wishlist dugme
//		metodu koja vraca element boje. 
//		Metoda kao parametar prima naziv boje 
//		(npr: “Orange”, “Blue”) a vraca link koji ima atribut title
//		prema trazenoj vrednosti.
//		metodu koja skrola do ovog dela stranice


	private WebDriver driver;

	public BuyBoxPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getQuantityInput() {
		return driver.findElement(By.name("qty"));
	}

	public Select getSizeSelect() {
		return new Select(driver.findElement(By.name("group_1")));
	}

	public WebElement getAddToCartButton() {
		return driver.findElement(By.name("Submit"));
	}

	public WebElement getAddToWishListButton() {
		return driver.findElement(By.id("wishlist_button"));
	}

	public WebElement getColor(String boja) {
		List<WebElement> colors = driver.findElements(By.xpath("//*[@id='color_to_pick_list']/li/a"));
		WebElement color = driver.findElement(By.xpath("//*[@id='color_to_pick_list']/li/a"));
		for (int i = 0; i < colors.size(); i++) {
			if (colors.get(i).getAttribute("name").equals(boja)) {
				color = colors.get(i);
			}
		}
		return color;
	}

	public void getScrollToTheByBoxDialog() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.className("box-info-product")));
		actions.perform();
	}
}
