package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close() {
        driver.quit();
        driver = null;
    }
    @Test
    public void shouldSendApplication() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[name=\"phone\"]")).sendKeys("+79198885436");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();

        assertEquals ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}