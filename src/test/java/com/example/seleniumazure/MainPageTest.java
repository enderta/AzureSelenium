package com.example.seleniumazure;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {

            ChromeOptions options = new ChromeOptions();
            // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless"); // Add this line to run Chrome in headless mode
            options.addArguments("--no-sandbox"); // Add this line to disable the sandbox
            options.addArguments("--disable-dev-shm-usage"); // Add this line to disable /dev/shm usage
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.jetbrains.com/");
            driver.findElement(By.xpath("//button[@data-jetbrains-cookies-banner-action='ACCEPT_ALL']")).click();
            mainPage = new MainPage(driver);
        }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.xpath("//input"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.xpath("//input"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-submenu']"));
        assertTrue(menuPopup.isDisplayed());
    }

}
