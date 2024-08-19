import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URL;


public class Lookfantastic {
    public static void main(String[] args) {
        // Desired Capabilities tanımlayın


        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"11.0"); // Android version
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");  // Android için UIAutomator2, iOS için XCUITest
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "PIXEL 8 API 30");  // Cihaz adı (emülatör veya gerçek cihaz)
        caps.setCapability(MobileCapabilityType.APP, "C:\\javademos\\Appium\\Apps\\lookfantastic-2-33-0.apk");  // Test edilecek uygulamanın yolu

        try {
            // Appium sunucusuna bağlanın

            URL url = new URI("http://localhost:4723/wd/hub").toURL();
            AppiumDriver<MobileElement> driver = new AndroidDriver<>(url, caps);

            // Allow butonu

            Thread Tread;
            Thread.sleep(30000);
            driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();
            Thread.sleep(1000);

            // Arama butonu

            WebDriverWait wait = new WebDriverWait(driver, 10);
            MobileElement search_Button = (MobileElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Open search']")));
            search_Button.click();

            //Texbox Arama Butonu

            MobileElement textBox = (MobileElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@text='Search for a product or brand...']")));
            textBox.click();
            String adbCommand = "adb shell input text 'lipstick'"; //sendkeys ile metin girişi yapılamaığı için adb kullandım.
            Runtime.getRuntime().exec(adbCommand);
            Thread.sleep(2000);

            MobileElement search_Button2 =(MobileElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Start search']")));
            search_Button2.click();

            Thread.sleep(5000);

            //Sıralama seçimi
            MobileElement Popularity_button = (MobileElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@resource-id='mainContent']/android.view.View[4]/android.widget.Image")));
            Popularity_button.click();
            Thread.sleep(2000);

            // Açılır menü sıralama
            MobileElement high_button = (MobileElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Price: High to low']")));
            high_button.click();

            Thread.sleep(3000);

            //Aşağı indir
            String swipeCommand = "adb shell input swipe 300 2270 300 500";

            Process swipeProcess = Runtime.getRuntime().exec(swipeCommand);

            Thread.sleep(8000);




            // Oturumu kapatın
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





