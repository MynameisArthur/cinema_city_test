package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CinemaCity {
    protected ChromeDriver driver = null;
    public CinemaCity(ChromeDriver driver){
        this.driver = driver;
    }
    protected WebElement getElement(String method, String locator){
        if(method == "xpath"){
            return driver.findElement(By.xpath(locator));
        }else if(method == "partialLinkText"){
            return driver.findElement(By.partialLinkText(locator));
        }
        return driver.findElement(By.id(locator));
    }
}
