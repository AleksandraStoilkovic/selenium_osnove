package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
//		Napisati program koji ima:
//			Podesava:
//			implicitno cekanje za trazenje elemenata od 10s
//			implicitno cekanje za ucitavanje stranice od 10s
//			eksplicitno cekanje podeseno na 10s
//			Podaci:
//			Potrebno je u projektu ukljuciti 4 slike.
//			Imenovanje slika neka bude po pravilu pozicija_ime_prezime_
//			polaznika.ekstenzija
//			Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg …
//			Koraci:
//			Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//			Maksimizuje prozor
//			Selektuje Image - Front klikom na tu karticu u dnu ekrana
//			Klik na add photo iz levog navigacionog menia
//			Upload slike. Upload preko File objekta koristeci getAbsolutePath
//			Sacekati da broj prikazanih slika u donjem uglu navigacije 
//			bude za 1 veca
//			Kliknuti na poslednje dodatu sliku kako bi bila izabrana za
//			postavljanje
//			Ceka da dijalog bude vidljiv
//			Klik na Use One Side Only dugme
//			Ceka da se pojavi dijalog sa slikom
//			Klik na Done
//			Ponoviti proces za Left, Right i Back
//			Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//			Kliknuti na Add To Cart dugme
//			Verifikovati postojanje greske Oops! It looks like you`ve not added an 
//			text to this field, please add one before continuing.
//			Xpath: //*[@action='error']
//			Zatvorite pretrazivac
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		ArrayList<File> slike = new ArrayList<File>();
		slike.add(new File("img/front_aleksandra_stoilkovic.jpg"));
		slike.add(new File("img/left_aleksandra_stoilkovic.jpg"));
		slike.add(new File("img/right_aleksandra_stoilkovic.jpg"));
		slike.add(new File("img/back_aleksandra_stoilkovic.jpg"));
		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
		for(int i=0; i<4; i++) {
			int broj = i+1;
			driver.findElement(By.xpath("//*[@alt='image "+broj+"']")).click();
			if(i==0) {
				driver.findElement(By.xpath("//*[@alt='Front']")).click();
			} else {
				driver.findElement(By.xpath("//*[text()='+ Add Image']")).click();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")));
			driver.findElement(By.id("imageUpload")).sendKeys(slike.get(i).getAbsolutePath());
			driver.findElement(By.xpath("//*[@loading='lazy'][last()]")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Use One Side Only']")));
			driver.findElement(By.xpath("//*[text()='Use One Side Only']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Done']")));
			driver.findElement(By.xpath("//*[text()='Done']")).click();
		}
		Random random = new Random();
		int x= random.nextInt(5);
		driver.findElement(By.xpath("//div[@name='" + x + "']")).click();
		driver.findElement(By.xpath("//*[contains(@class, 'sc-bczRLJ')]")).click();
		boolean elementPostoji = true;
		try {
			driver.findElement(By.xpath("//*[@action='error']"));
		} catch (Exception e) {
			elementPostoji = false;
		}
		if (elementPostoji) {
			System.out.println("Dijalog postoji.");
		} else {
			System.out.println("Dijalog ne postoji.");
		}
		Thread.sleep(5000);
		driver.quit();

	}

}
