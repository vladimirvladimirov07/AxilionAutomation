import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestClass {
    AxilionHomePage axilionHomePage;
    WebDriver driver;

    @BeforeSuite
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://axilion.z6.web.core.windows.net/#/");
        axilionHomePage = new AxilionHomePage(driver);
    }

    @AfterMethod
    public void afterTest(){
        driver.navigate().refresh();
    }

    @Test
    public void Test1() {
        axilionHomePage.setSliderValueRoadLength(49);
        axilionHomePage.setSliderValueNSI(50);
        axilionHomePage.clickSelectServiceOption1();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(980, 70, 14));
    }

    @Test
    public void Test2() {
        axilionHomePage.setSliderValueRoadLength(70);
        axilionHomePage.setSliderValueNSI(80);
        axilionHomePage.clickSelectServiceOption1();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(1430, 65, 22));
    }

    @Test
    public void Test3() {
        axilionHomePage.setSliderValueRoadLength(155);
        axilionHomePage.setSliderValueNSI(160);
        axilionHomePage.clickSelectServiceOption1();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(3000, 60, 50));
    }

    @Test
    public void Test4() {
        axilionHomePage.setSliderValueRoadLength(49);
        axilionHomePage.setSliderValueNSI(50);
        axilionHomePage.clickSelectServiceOption2();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(1260, 90, 14));
    }

    @Test
    public void Test5() {
        axilionHomePage.setSliderValueRoadLength(70);
        axilionHomePage.setSliderValueNSI(80);
        axilionHomePage.clickSelectServiceOption2();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(1760, 80, 22));
    }

    @Test
    public void Test6() {
        axilionHomePage.setSliderValueRoadLength(155);
        axilionHomePage.setSliderValueNSI(160);
        axilionHomePage.clickSelectServiceOption2();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(3750, 75, 50));
    }

    @Test
    public void Test7() {
        axilionHomePage.setSliderValueRoadLength(49);
        axilionHomePage.setSliderValueNSI(50);
        axilionHomePage.clickSelectServiceOption3();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(1680, 120, 14));
    }

    @Test
    public void Test8() {
        axilionHomePage.setSliderValueRoadLength(70);
        axilionHomePage.setSliderValueNSI(80);
        axilionHomePage.clickSelectServiceOption3();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(2530, 115, 22));
    }

    @Test
    public void Test9() {
        axilionHomePage.setSliderValueRoadLength(155);
        axilionHomePage.setSliderValueNSI(160);
        axilionHomePage.clickSelectServiceOption3();
        Assert.assertEquals(axilionHomePage.getSaaSprice(),axilionHomePage.calculateSaaSprice(5250, 105, 50));
    }

}