package utility_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Path;

/*
* Utility class that gets user email and loads the page
* */

public class PageLoader {
    private WebDriver driver;
    private String url;
    private String country;
    private Path path;

    public PageLoader(String country,Path path, ChromeDriver driver){
        this.country = country;
        this.path = path;
        this.driver = driver;
    }

    public String loadEmail() throws IOException {
        String userEmail = (new EmailLoader(path.toString())).getEmail();
        return userEmail;
    }

    public void loadPage(){
        if(country.contains("PL")){
            url = "https://plstg.cci-dev.pl/";
        }else{
            url = "https://czstg.cci-dev.pl/";
        }
        driver.get(url);
    }
}
