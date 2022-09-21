package d20_09_2022tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import d20_09_2022pages.BuyBoxPage;
import d20_09_2022pages.HeaderPage;
import d20_09_2022pages.LayerCartPage;
import d20_09_2022pages.TopMenuPage;


public abstract class BasicTest {
    protected WebDriver driver;
    protected String baseUrl = "http://automationpractice.com";
    protected BuyBoxPage buyBoxPage;
    protected LayerCartPage layerCartPage;
    protected TopMenuPage topMenuPage;
    protected HeaderPage headerPage;
    protected WebDriverWait wait;
    protected SoftAssert softAssert;


    @BeforeClass
    public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        buyBoxPage = new BuyBoxPage(driver);
        layerCartPage = new LayerCartPage(driver, wait);
        topMenuPage = new TopMenuPage(driver);
        headerPage = new HeaderPage(driver);
        softAssert = new SoftAssert();
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
