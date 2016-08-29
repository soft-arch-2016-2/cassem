package WebPage;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeleteUserTest {
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
  public void testDeleteUser() throws Exception {
    driver.get(baseUrl + "/cassem/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("j_idt9:j_idt11")).clear();
    driver.findElement(By.id("j_idt9:j_idt11")).sendKeys("maralocen");
    driver.findElement(By.id("j_idt9:j_idt13")).clear();
    driver.findElement(By.id("j_idt9:j_idt13")).sendKeys("tulio85304");
    driver.findElement(By.name("j_idt9:j_idt15")).click();
    driver.get("http://localhost:8080/cassem/faces/createUser.xhtml");
    driver.findElement(By.name("j_idt28:3:j_idt30:0:j_idt34:j_idt35")).click();
    driver.findElement(By.name("j_idt28:3:j_idt30:0:j_idt34:j_idt35")).click();
    driver.findElement(By.name("j_idt28:3:j_idt30:0:j_idt34:j_idt35")).click();
    driver.findElement(By.name("j_idt28:3:j_idt30:0:j_idt34:j_idt35")).click();
    driver.findElement(By.name("j_idt28:3:j_idt30:0:j_idt34:j_idt35")).click();
  }

  @After
  public void tearDown() throws Exception {
      driver.quit();
  } 
}
