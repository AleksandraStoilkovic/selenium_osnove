package d15_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zadatak3 {

	public static void main(String[] args) throws InterruptedException {
//		Napisati program koji 
//		Ucitava https://seeds.sproutsocial.com/components/loader-button/
//		Odskrola do Loader buttons are used to display a loading indicator inside of a button. paragrafa. Koristan link
//		Klikce ne dugme 
//		Ceka da dugme zavrsi sa loadingom 
//		Ispisati poruku na ekranu
//		Zatvoriti pretrazivac
//		HINT: Koristite data-qa-button-isloading  atribut elementa za cekanje odredjenog stanja tog elementa
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://seeds.sproutsocial.com/components/loader-button/");
		WebElement hoverable = driver.findElement(By.xpath("//*[contains(@class, 'dYHkzc')]"));
        new Actions(driver).moveToElement(hoverable).perform();
        driver.findElement(By.xpath("//*[contains(@class, 'dzjEcK')]")).click();
        boolean elementSeUcitava = true;
        try {
			driver.findElement(By.xpath("//*[@data-qa-button-isloading='true']"));
		} catch (Exception e) {
			elementSeUcitava = false;
		}
		if (elementSeUcitava) {
			System.out.println("Dugme se ocitava.");
		} else {
			System.out.println("Dugme se ne ocitava.");
		}
		boolean elementSeUcitao = true;
		 try {
				driver.findElement(By.xpath("//*[@data-qa-button-isloading='false']"));
			} catch (Exception e) {
				elementSeUcitao = false;
			}
			if (elementSeUcitao) {
				System.out.println("Dugme se ocitalo.");
			} else {
				System.out.println("Dugme se nije ocitalo.");
			}
			Thread.sleep(1000);
			driver.quit();
		
	}

}
