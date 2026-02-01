import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
//import io.appium.java_client.WebElement;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class vanilla_android {
    public static String userName = System.getenv("LT_USERNAME") == null ? "YOUR_USERNAME" : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

    static AppiumDriver driver;

    public static void main(String args[]) throws MalformedURLException, InterruptedException {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("deviceName", "Galaxy.*"); // You can specify a particular model, or use a wildcard.
            ltOptions.put("platformVersion", "14"); // Make sure this matches the device's OS version.
            ltOptions.put("platformName", "Android");
            ltOptions.put("isRealMobile", true); // Ensures that the test runs on a real device.
            ltOptions.put("app", "lt://APP10160622431766424164986229"); // Your app URL from LambdaTest.
            ltOptions.put("deviceOrientation", "PORTRAIT");
            ltOptions.put("build", "Java Vanilla - Android");
            ltOptions.put("name", "Sample Test Java");
            ltOptions.put("console", true);
            ltOptions.put("autoGrantPermissions", true); // Auto grants permissions on the app.
            ltOptions.put("network", false);
            ltOptions.put("visual", true);
            ltOptions.put("devicelog", true);
            capabilities.setCapability("lt:options", ltOptions);

            // Initialize Appium driver with the LambdaTest server URL and capabilities
            driver = new AndroidDriver(
                    new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
                    capabilities);

            // Interact with elements using their IDs
            WebElement color = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/color"));
            color.click();

            WebElement text = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/Text"));
            text.click();

            // Interact with Toast message element
            WebElement toast = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/toast"));
            toast.click();

            // Interact with Notification element
            WebElement notification = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/notification"));
            notification.click();

            // Open the Geolocation page
            WebElement geo = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/geoLocation"));
            geo.click();
            Thread.sleep(5000);

            // Go back to the home page
            driver.navigate().back();
            Thread.sleep(2000);

            // Navigate to the Speed Test page
            WebElement speedtest = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/speedTest"));
            speedtest.click();
            Thread.sleep(5000);

            driver.navigate().back();

            // Open the Browser and perform actions
            WebElement browser = driver.findElement(MobileBy.AccessibilityId("Browser"));
            browser.click();

            WebElement url = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/url"));
            url.sendKeys("https://www.lambdatest.com");

            WebElement find = driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/find"));
            find.click();

        } catch (AssertionError a) {
            // Mark the status as failed if there's an error
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            a.printStackTrace();
        } finally {
            // Ensure the driver quits to avoid the session hanging
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
