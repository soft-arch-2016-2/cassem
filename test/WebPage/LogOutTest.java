package WebPage;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LogOutTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.gecko.driver", 
                        "lib/geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogOut() throws Exception {
    driver.get(baseUrl + "/cassem/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("j_idt9:j_idt11")).clear();
    driver.findElement(By.id("j_idt9:j_idt11")).sendKeys("maralocen");
    driver.findElement(By.id("j_idt9:j_idt13")).clear();
    driver.findElement(By.id("j_idt9:j_idt13")).sendKeys("tulio85304");
    driver.findElement(By.name("j_idt9:j_idt15")).click();
    driver.get("http://localhost:8080/cassem/faces/createPart.xhtml");
  }

  @After
  public void tearDown() throws Exception {
      driver.quit();
  } 
}
