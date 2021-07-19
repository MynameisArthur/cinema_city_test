import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.HomePage;
import page_objects.RegisterPage;
import page_objects.SummaryPage;
import utility_classes.EmailLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void mainTest() throws URISyntaxException, IOException, InterruptedException {
        Path path = Paths.get(CinemaCityTest.class.getResource("email_list.txt").toURI());
        String userEmail = (new EmailLoader(path.toString())).getRandomEmail();
        System.out.println("test");
        driver.get(url);
        homePage = new HomePage(country,(ChromeDriver) driver);
        homePage.goToRegisterPage();
        registerPage = new RegisterPage(userEmail,(ChromeDriver) driver);
        registerPage.registerUser();
        summaryPage = new SummaryPage(userEmail,(ChromeDriver) driver);
        summaryPage.checkEmail();
    }
}