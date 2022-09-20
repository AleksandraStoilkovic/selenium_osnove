package d19_09_2022;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BootstrapTableTests {
//	Kreirati BootstrapTableTests klasu koja ima:
//		Base url: https://s.bootsnipp.com
//		Test #1: Edit Row
//		Podaci:
//		First Name: ime polaznika
//		Last Name: prezime polaznika
//		Middle Name: srednje ime polanzika
//		Koraci:
//		Ucitati stranu /iframe/K5yrx
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		Klik na Edit dugme prvog reda
//		Sacekati da dijalog za Editovanje bude vidljiv
//		Popuniti formu podacima. 
//		Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//		Klik na Update dugme
//		Sacekati da dijalog za Editovanje postane nevidljiv
//		Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//		Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//		Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//		Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//		Test #2: Delete Row
//		Podaci:
//		First Name: ime polaznika
//		Last Name: prezime polaznika
//		Middle Name: srednje ime polanzika
//		Koraci:
//		Ucitati stranu /iframe/K5yrx
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		Klik na Delete dugme prvog reda
//		Sacekati da dijalog za brisanje bude vidljiv
//		Klik na Delete dugme iz dijaloga 
//		Sacekati da dijalog za Editovanje postane nevidljiv
//		Verifikovati da je broj redova u tabeli za jedan manji
//		Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//		Test #3: Take a Screenshot
//		Koraci:
//		Ucitati stranu  /iframe/K5yrx
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		Kreirati screenshot stranice. Koristan link https://www.guru99.com/take-screenshot-selenium-webdriver.html
//		Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: src/paket_za_domaci/nazivslike.png
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = " https://s.bootsnipp.com";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}

	@Test(priority = 100)
	public void editRow() {
		driver.get(baseUrl + "/iframe/K5yrx");

		Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
				"Not on my /iframe/K5yrx page.");

		driver.findElement(By.xpath("//*[@type='button'][1]")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("fn")).sendKeys("ime polaznika");
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("ln")).sendKeys("prezime polaznika");
		driver.findElement(By.id("mn")).clear();
		driver.findElement(By.id("mn")).sendKeys("srednje ime polanzika");
		driver.findElement(By.id("up")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));

		Assert.assertEquals(driver.findElement(By.id("f1")).getText(), "ime polaznika",
				"First name should be ime polaznika");
		Assert.assertEquals(driver.findElement(By.id("l1")).getText(), "prezime polaznika",
				"Last name should be prezime polaznika");
		Assert.assertEquals(driver.findElement(By.id("m1")).getText(), "srednje ime polanzika",
				"Middle name should be srednje ime polaznika");

	}

	@Test(priority = 200)
	public void deleteRows() {
		driver.get(baseUrl + "/iframe/K5yrx");

		Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
				"Not on my /iframe/K5yrx page.");

		driver.findElement(By.xpath("//*[contains(@class, 'delete')]")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("del")));
		driver.findElement(By.id("del")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("del")));
		Assert.assertFalse(
				driver.findElement(By.xpath("//*[contains(@class, 'table-responsive')]/tbody/tr[1]")).isDisplayed(),
				"First row should be deleted");

	}

	@Test(priority = 300)
	public void TakeAScreenshot() throws IOException, HeadlessException, AWTException {
		driver.get(baseUrl + "/iframe/K5yrx");

		Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
				"Not on my /iframe/K5yrx page.");

		File slikaUSeleniumu = new File("img/slika.png");
		File destFile = new File(slikaUSeleniumu.getAbsolutePath());
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		BufferedImage image = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", destFile);
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
