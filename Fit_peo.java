
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Fit_peo {
    public static void main(String[] args)  {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://www.fitpeo.com/");
        WebElement revenue_calculator_link =driver.findElement(By.xpath("//div[text()='Revenue Calculator']//parent::a"));
        revenue_calculator_link.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement slider=   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-j7qwjs']/span[1]/span[3]")));
        js.executeScript("window.scrollBy(0, 200);");

        Actions actions=new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(94,0).release().build().perform();
        WebElement txt_box=driver.findElement(By.id(":r0:"));
        Assert.assertEquals(slider.getText(),txt_box.getText(),"slider and txt box value mismatch");

        txt_box.sendKeys(Keys.CONTROL + "a");
        txt_box.sendKeys(Keys.DELETE);
        txt_box.sendKeys("560");
        Assert.assertEquals(txt_box.getText(),slider.getText(),"txt box and slider value mismatch");

        js.executeScript("window.scrollBy(0, 400);");

        WebElement check_box_1= driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div[1]//input"));
        check_box_1.click();
        WebElement check_box_2= driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div[2]//input"));
        check_box_2.click();
        WebElement check_box_3= driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div[3]//input"));
        check_box_3.click();
        WebElement check_box_4= driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div[4]//input"));
        check_box_4.click();
        String amt=driver.findElement(By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")).getText();
        Assert.assertEquals("$95760",amt);
    }
}
