package d20_09_2022tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class AutomationPracticeTests extends BasicTest {
//	Kreirati klasu AutomationPracticeTests 
//	baseUrl: http://automationpractice.com/
//		Test #1:  Adding product to the cart
//	Ucitati stranicu /index.php?id_product=1&controller=product
//	Odskrolati do buy box-a
//	Postavite kolicinu na 3
//	Izaberite velicinu L
//	Izaberite plavu boju
//	Kliknite na dugme add to cart
//	Cekajte da dijalog layer cart bude vidljiv
//	Verifikovati da je kolicina iz layer cart dijalog 3
//	Verifikovati da je velicina L
//	Verifikovati da je izabran proizvod sa plavom bojom
//	Verifikovati da je total price 3 puta veci od cene proizvoda
//	Kliknite na dugme continue shopping
//	cekajte da dijalog layer cart postane nevidljiv
//	Izaberite novi proizvod sa kolicinom 1, velicinom S i bojom Organe
//	Kliknite na dugme add to cart
//	Cekajte da dijalog bude vidljiv
//	kliknite na dugme proceed to checkout
//	Verifikujte da je naslov stranice Order - My Store
//
//
//		Test #2:  Top menu mouse over
//	Predjite misem preko women linka. Koristan link kako da predjete misem preko nekog elementa link
//	Verifikujte da je podmeni za women deo vidljiv
//	Predjite misem preko dresses linka. 
//	Verifikujte da je podmeni za dresses deo vidljiv
//	Predjite misem preko t-shirts linka. 
//	Verifikujte podmeniji za womens i dresses nevidljivi
//	Ukoliko je potrebno ukljucite odgovarajuca cekanja, kojim bi se sacekalo da stranica dodje u odgovarajuce stanje
//	Provera preko za vidljivost preko soft assert-a
//
//		Test #3:  Phone number visibility check on resize
//	Maksimizujte prozor
//	Proverite da je element za broj telefona vidljiv
//	Smanjite dimenziju pretrazivaca na velicinu 767 x 700
//	Proverite element za broj telefona nije vidljiv
//	Promenite dimenziju pretrazivaca na 768 x 700
//	Proverite da je broj telefona vidljiv
//	Maksimizujte prozor
//	Provera preko soft asserta
//	
//	Test #4:  Header links check
//	Kliknite na contact us link
//	Verifikujte da je naslov stranice Contact us - My Store
//	Kliknite na sign in link
//	Verifikujte da je naslov stranice Login - My Store
//	Provera preko soft asserta

	@Test(priority = 1)
	public void addingProductToTheCart() {
		driver.navigate().to(baseUrl + "/index.php?id_product=1&controller=product");
		buyBoxPage.getScrollToTheByBoxDialog();
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("3");
		buyBoxPage.getSizeSelect().selectByVisibleText("L");
		buyBoxPage.getColor("Blue").click();
		buyBoxPage.getAddToCartButton().click();
		layerCartPage.waitForDialogToBeVisible();
		softAssert.assertEquals(layerCartPage.getQuantity().getText(), "3", "There should be 3 items for quantity.");
		softAssert.assertTrue(layerCartPage.getAttributes().getText().contains("L"),
				"There should be L size selected.");
		softAssert.assertTrue(layerCartPage.getAttributes().getText().contains("Blue"),
				"There should be blue color selected.");

		softAssert.assertEquals(layerCartPage.getTotalPrice().getText(), "$" + 3 * 16.51,
				"There should be triple the price");
		layerCartPage.getContinueShoopingButton().click();
		layerCartPage.waitForDialogToBeInvisible();
		buyBoxPage.getScrollToTheByBoxDialog();
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("1");
		buyBoxPage.getSizeSelect().selectByVisibleText("S");
		buyBoxPage.getColor("Orange").click();
		buyBoxPage.getAddToCartButton().click();
		layerCartPage.waitForDialogToBeVisible();
		layerCartPage.getProceedToCheckoutButton().click();
		softAssert.assertEquals(driver.getTitle(), "Order - My Store", "Not on shoping cart page.");
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void topMenuMouseOver() throws InterruptedException {
		new Actions(driver).moveToElement(topMenuPage.getWomenLink()).perform();
		Thread.sleep(5000);
		softAssert.assertTrue(topMenuPage.getWomanSubmenu().getAttribute("style").contains("display: block;"), 
				"Woman submenu should be displayed.");
		new Actions(driver).moveToElement(topMenuPage.getDressesLink()).perform();
		Thread.sleep(5000);
		softAssert.assertTrue(topMenuPage.getDressesSubmenu().getAttribute("style").contains("display: block;"), 
				"Dresses submenu should be displayed.");
		new Actions(driver).moveToElement(topMenuPage.getTShirtsLink()).perform();
		Thread.sleep(5000);
		softAssert.assertFalse(topMenuPage.getWomanSubmenu().getAttribute("style").contains("display: block;"),
				"Woman submenu should not be displayed.");
		softAssert.assertFalse(topMenuPage.getDressesSubmenu().getAttribute("style").contains("display: block;"),
				"Dresses submenu should not be displayed.");
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void phoneNumberVisibilityCheckOnResize() throws InterruptedException {
		softAssert.assertTrue(headerPage.getPhoneNumber().isDisplayed(), "Phone number should be displayed");
		driver.manage().window().setSize(new Dimension(767, 700));
		softAssert.assertFalse(headerPage.getPhoneNumber().isDisplayed(), "Phone number should not be displayed");
		driver.manage().window().setSize(new Dimension(768, 700));
		softAssert.assertTrue(headerPage.getPhoneNumber().isDisplayed(), "Phone number should be displayed");
		driver.manage().window().maximize();
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void headerLinksCheck() {
		headerPage.getContactUsLink().click();
		softAssert.assertEquals(driver.getTitle(), "Contact us - My Store", "Not on contact form page");
		headerPage.getSignInLink().click();
		softAssert.assertEquals(driver.getTitle(), "Login - My Store", "Not on log in page");
		softAssert.assertAll();
	}

}