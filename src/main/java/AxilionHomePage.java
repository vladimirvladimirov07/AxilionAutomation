import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AxilionHomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div")
    WebElement selectService;

    @FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[1]")
    WebElement selectServiceOption1;

    @FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[2]")
    WebElement selectServiceOption2;

    @FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[3]")
    WebElement selectServiceOption3;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[1]/span/span[7]")
    WebElement roadLength;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[2]/span/span[7]")
    WebElement NSI;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[1]/span/span[7]/span/span/span")
    WebElement RoadLengthValue;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[2]/span/span[7]/span/span/span")
    WebElement NumberOfSignalizedIntersectionsValue;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[3]/div[1]")
    WebElement SaaSpriceString;

    double WeightLength = 0.01;
    double WeightJunctions = 0.02;
    double XWUref = 5.625;

    public AxilionHomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public int RoadLengthValue(){
        String[] parts = RoadLengthValue.getText().split(" ");
        String rlValue = parts[0];
        return Integer.parseInt(rlValue);
    }

    public int NumberOfSignalizedIntersectionsValue(){

        return Integer.parseInt(NumberOfSignalizedIntersectionsValue.getText());
    }

    public double TrafficComplexity(){
        double TrafficComplexity = WeightLength * RoadLengthValue() + WeightJunctions * NumberOfSignalizedIntersectionsValue();
        return TrafficComplexity;
    }

    public int XWU(){
        int XWU = (int) Math.round(XWUref*TrafficComplexity());
        return XWU;
    }

    public int calculateSaaSprice(int FlatRate, int UnitRate, int UnitsIncluded){
        int SaaSprice = 0;
        if (XWU() <= UnitsIncluded) {
            SaaSprice = FlatRate;
        }
        else if (XWU() > UnitsIncluded) {
            SaaSprice = FlatRate + (XWU() - UnitsIncluded) * UnitRate;
        }
        return SaaSprice;
    }

    public int getSaaSprice(){
        return Integer.parseInt(SaaSpriceString.getText().replaceAll("[^0-9]", ""));
    }

    public void clickSelectServiceOption1(){
        selectService.click();
        wait.until(ExpectedConditions.elementToBeClickable(selectServiceOption1));
        selectServiceOption1.click();
    }

    public void clickSelectServiceOption2(){
        selectService.click();
        wait.until(ExpectedConditions.elementToBeClickable(selectServiceOption2));
        selectServiceOption2.click();
    }

    public void clickSelectServiceOption3(){
        selectService.click();
        wait.until(ExpectedConditions.elementToBeClickable(selectServiceOption3));
        selectServiceOption3.click();
    }

    public void setSliderValueRoadLength(int xOffset){
        Actions move = new Actions(driver);
        Action action = (Action) move.dragAndDropBy(roadLength, xOffset, 0).build();
        action.perform();
    }

    public void setSliderValueNSI(int xOffset){
        Actions move = new Actions(driver);
        Action action = (Action) move.dragAndDropBy(NSI, xOffset, 0).build();
        action.perform();
    }

}
