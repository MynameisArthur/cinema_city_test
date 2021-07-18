import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.HomePage;
import page_objects.RegisterPage;
import page_objects.SummaryPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class CinemaCityTest {
    WebDriver driver;
    String url;
    String country;
    String userEmail;
    HomePage homePage;
    RegisterPage registerPage;
    SummaryPage summaryPage;
    @Before
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        country = "PL";
        if(country == "PL"){
            url = "https://plstg.cci-dev.pl/";
        }else{
            url = "https://czstg.cci-dev.pl/";
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void mainTest() throws URISyntaxException, InterruptedException, IOException {
        Path path = Paths.get(CinemaCityTest.class.getResource("email_list.txt").toURI());
        String emails = (new EmailLoader(path.toString())).getText();
        String[] emailList = emails.split("\n");
        userEmail = emailList[0].toString();
        driver.get(url);
        homePage = new HomePage(country,(ChromeDriver) driver);
        homePage.goToRegisterPage();
        registerPage = new RegisterPage(userEmail,(ChromeDriver) driver);
        registerPage.registerUser();
        Thread.sleep(10000);
//        summaryPage = new SummaryPage(userEmail,(ChromeDriver) driver);
//        summaryPage.checkEmail();
    }
}