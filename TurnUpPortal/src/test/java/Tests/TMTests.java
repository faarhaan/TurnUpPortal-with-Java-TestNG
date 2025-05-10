package Tests;

import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;

public class TMTests {
    WebDriver driver;
    @BeforeMethod

    public void SetUpSteps() throws InterruptedException {
        // To Handle leak password Detection
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        //options.AddUserProfilePreference("profile.password_manager_leak_detection", false);
       // options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.valueOf("profile.password_manager_leak_detection"));
        HashMap<Object, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        //  Open Chrome Browser
        driver = new ChromeDriver(chromeOptions);
        // LoginPage Object initiallization and definition

        LoginPage loginpageObj = new LoginPage();
        loginpageObj.LoginActions(driver);

        // HomePage Object initilization and definition
        HomePage homePageObj = new HomePage();
        homePageObj.VerifyUserInHomePage(driver);
        homePageObj.NavigateToTMPage(driver);
    }
    @Test(priority = 0, description ="this test is designed to create Time record")
    public void CreateTime_Test() throws InterruptedException {

        // TMpage Object initialization and definition
        TMpage tMpageObj = new TMpage();
        tMpageObj.CreateTimeRecord(driver);
    }
    @Test(priority = 1, description ="this test is designed to Edit Time record")

    public void EditTime_Test() throws InterruptedException {
            // Edit Time Record
            TMpage tMpageObj = new TMpage();
            tMpageObj.EditTimeRecord(driver);
    }
    @Test(priority = 2, description ="this test is designed to delete Time record")
    public void DeleteTimeRecord() throws InterruptedException {
        TMpage tMpageObj = new TMpage();
        tMpageObj.DeleteTimeRecord(driver);
    }

     @AfterMethod
     public void CloseRun() throws InterruptedException {
       driver.close();

     }

}
