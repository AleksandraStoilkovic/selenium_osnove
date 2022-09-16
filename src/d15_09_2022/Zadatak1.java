package d15_09_2022;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
//		Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//			Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i 
		// ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//			POMOC: Brisite elemente odozdo.
//			(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://s.bootsnipp.com/iframe/Dq2X");
		List<WebElement> x = driver.findElements(By.xpath("//*[contains(@class, 'col-md-12')]/div/button"));
		for (int i = x.size(); i >= 1; i--) {
			driver.findElement(By.xpath("//*[contains(@class, 'col-md-12')]/div[" + i + "]/button")).click();
			boolean elementPostoji = true;
			try {
				driver.findElement(By.xpath("//*[contains(@class, 'col-md-12')]/div[" + i + "]/button"));
			} catch (Exception e) {
				elementPostoji = false;
			}
			if (elementPostoji) {
				System.out.println("Element br " + i + " postoji.");
			} else {
				System.out.println("Element br " + i + " ne postoji.");
			}
		}
		Thread.sleep(1000);
		driver.quit();

	}

}
