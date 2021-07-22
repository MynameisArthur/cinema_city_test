package utility_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URISyntaxException;

/*
* Utility class that gets user email and loads the page
* */

public class PageLoader {
    private WebDriver driver;
    private String url;
    private String country;
    DataReader reader;

    public PageLoader(DataReader reader, String country, ChromeDriver driver){
        this.country = country;
        this.driver = driver;
        this.reader = reader;
    }

    public String loadEmail() throws IOException, URISyntaxException {
        return  reader.getEmail();
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
