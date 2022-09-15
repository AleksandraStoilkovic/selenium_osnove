package d13_09_2022;

import java.util.ArrayList;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {

	public static void main(String[] args) throws InterruptedException {
//		Napisati program koji matematicku formulu koju korisnik unese izvrsaav na stranici:
//			Ucitati stranicu https://www.calculatorsoup.com/calculators/math/basic.php
//			Korisnik unosi formulu, samo osnovne matematicke operacija, npr:
//			1243+329=
//			21912-4=
//			12913÷4=
//			U programu se formula unosi kao jedan string i potrebno je razbiti formulu na karaktere. 
//			Za to imate metodu https://www.geeksforgeeks.org/convert-a-string-to-a-list-of-characters-in-java/
//			Zatim u odnosu na karakter uradite odredjenu logiku
		Scanner s = new Scanner(System.in);
		System.out.print("Unesite formulu: ");
		String formula = s.next();
		ArrayList<Character> razbijena = new ArrayList<Character>();
		for (char ch : formula.toCharArray()) {
			if(ch == '-') {
				ch = '-';
			} else if (ch == '*'){
				ch = '×';
			} else if (ch == '/') {
				ch = '÷';
			}
			razbijena.add(ch);
		}
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.calculatorsoup.com/calculators/math/basic.php");
		Thread.sleep(5000);
		for (int i = 0; i < razbijena.size(); i++) {
			driver.findElement(By.xpath("//*[@value='" + razbijena.get(i) + "']")).click();
			Thread.sleep(500);
		}
		driver.quit();
	}

}
