package pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObjects.MainScreenPageObject;
import pageObjects.MusicPadObjects;
import pageObjects.SessionObjects;

public class MultipleSessionsList extends MusicPad
{

        //List<String> sessionList = null;
        SessionObjects sessionObjects = new SessionObjects();
        MainScreenPageObject mainScreenPageObject = new MainScreenPageObject();
        MusicPadObjects musicPadObjects = new MusicPadObjects();
        MultipleSessions multipleSessions=new MultipleSessions(driver);

        public MultipleSessionsList(AppiumDriver driver)
        {
                super(driver);
                PageFactory.initElements(new AppiumFieldDecorator(driver),
                sessionObjects);
                PageFactory.initElements(new AppiumFieldDecorator(driver),
                mainScreenPageObject);
                PageFactory.initElements(new AppiumFieldDecorator(driver),
                musicPadObjects);
        }

        public boolean multipleSessionsList()
        {
                try {
                        findAndClickElementById("com.suenare.iafrobeats.afrobeats:id/img_menu");
                        wait(1);
                        List<WebElement> sessionNames = driver.findElements(org.openqa.selenium.By.id("com.suenare.iafrobeats.afrobeats:id/txt_name"));
                        return sessionNames.size() > 0;
                } catch (Exception e) {
                        System.out.println("Error in multipleSessionsList: " + e.getMessage());
                        // Fallback to original method
                        findWebElementByIDAndClick(sessionObjects.burgerMenu);
                        if(sessionObjects.sessionName.size()>0)
                                return true;
                        else
                                return false;
                }
        }

        public boolean verifyMutipleSession()
        {

                try
                {
                        boolean finalResult = true;
                        for (int i = 0; i < sessionObjects.sessionName.size(); i++)
                        {
                                
                                if(sessionObjects.sessionName.get(i).getText().equals(multipleSessions.sessionList.get(i))) {
                                        System.out.println("Sessions are present");
                                }
                                else
                                {
                                        finalResult=false;
                                        break;
                                }
                                findWebElementByIDAndClick(sessionObjects.sessionName.get(i));
                                findWebElementByIDAndClick(sessionObjects.openButtonSessions);
                                EditMusicPad ed = new EditMusicPad(driver);
                                finalResult = ed.playAllAddedTrack();
                                findWebElementByIDAndClick(sessionObjects.burgerMenu);
                }
                        return finalResult;
                        }
                        
                
                catch (Exception e)
                {
                        System.out.println(e.getMessage());
                        return false;
                }
        }

}
