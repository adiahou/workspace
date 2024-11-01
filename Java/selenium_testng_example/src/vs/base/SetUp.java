package dxy.vs.base;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.common.base.Function;

import dxy.vs.base.LogWebDriverEventListener;


public class SetUp {

	public static WebDriver driver_origin;
	public static EventFiringWebDriver driver;
	public static String time;
	public static String date;
	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	public  Log log = LogFactory.getLog(this.getClass());
	public static LogWebDriverEventListener eventListener;
	protected static Properties prop;	
	public static String propFile = "testdata/vsproperty_cn.properties";

	static {
		// 取当前时间
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date curDate = new Date(System.currentTimeMillis());
		time = formatter1.format(curDate);
		date=formatter2.format(curDate);
	}

	@Parameters({"browserFlag"})  
	@BeforeMethod
	public void setUp(@Optional("1") String browserFlag) {
		
		if(browserFlag.equals("1")){
			// Set for ChromeDriver
			System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
			
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("user-data-dir=C:/Users/admin/AppData/Local/Google/Chrome/User Data");

			driver_origin = new ChromeDriver();
		}
		
		if(browserFlag.equals("0")){

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			
			driver_origin = new FirefoxDriver(dc);

		}


		//Add Event Listener into the driver
		eventListener = new LogWebDriverEventListener();
		driver = new EventFiringWebDriver(driver_origin);
		driver.register(eventListener);
		
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
        
		// Clean cache and cookies
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		threadDriver.set(driver);
		
		//Get Test Name
		String testCaseFullName=this.getClass().getName();
		String testCaseShortName=(testCaseFullName.split("\\.")[testCaseFullName.split("\\.").length-1]);
		
		//Add test name into the Log
		log.info("Current Test Cast Name =" + testCaseShortName);
	}

//	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception {
		driver.quit();
		driverKiller();
	}

	/**
	 * Kill drivers
	 */
	public void driverKiller() throws Exception {
		// Define Parameters
		final String KILL = "taskkill /IM ";

		// Kill the chrome driver's process
		String chormeProcess = "chromedriver.exe";
		Runtime.getRuntime().exec(KILL + chormeProcess);
		Thread.sleep(1000);
	}

	/************************************************************** public functions ********************************************************************/

	/**
	 * get property
	 */
	public String p(String property) {
		InputStream is = null;
        prop = new Properties();
        Reader reader=null;
        
        try {
            is = new FileInputStream(propFile);
        	reader = new InputStreamReader(is, "UTF-8");
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return prop.getProperty(property);
	}

	   /**
	    * Objective: fill out input field
	    * @param element : WebElement object
	    * @param inputValue: string value
	    */
	    public static void input(WebElement element, String inputValue){
	      element.clear();
	      element.sendKeys(inputValue); 
	      Assert.assertTrue(element.getAttribute("value").trim().contains(inputValue),"The diaplayed value is not match with the input value.");
	   }
	    
	/**
	 * click the element by JS
	 * 
	 * @param element
	 * @throws Exception
	 */
	public static void clickByJS(WebElement element) throws Exception {
		((JavascriptExecutor) threadDriver.get()).executeScript(
				"arguments[0].click();", element);
		Thread.sleep(1000);
	}

	   /**
     * click the element by Actions
     * @author mandy.chen
     * @param element
	 * @throws Exception 
     */
    public static void clickByAction(WebElement element) throws Exception {
        Actions actions = new Actions(threadDriver.get());
        actions.click(element).build().perform();
        actions = null;
       Thread.sleep(1000);
    }
    
	public boolean isAlertPresent() throws Exception {
		threadDriver.get().manage().timeouts()
		.implicitlyWait(5, TimeUnit.SECONDS);
		boolean isPresent = false;
		try {
			Alert alert = driver.switchTo().alert();
			if (null == alert) {
				throw new NoAlertPresentException();
			}else{
				isPresent = true;
			}
		}catch (NoAlertPresentException e) {
			System.out.println("There is no alert appear!");
		}
		return isPresent;
	}
	
	/**
	 * click yes or no from page JS alert
	 * 
	 * @param yes
	 *            or no to click
	 * @throws Exception
	 */
	public static void clickFromAlert(String value) throws Exception {
		threadDriver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			Alert alert = driver.switchTo().alert();
			if (null == alert) {
				throw new NoAlertPresentException();
			} else if (value.equals("yes")) {
				alert.accept();
			} else if (value.equals("no")) {
				alert.dismiss();
			}
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert appear!");
		}
	}

	/**
	 * convert string to date
	 * 
	 * @throws Exception
	 */
	public Date std(String datevalue, int x) throws Exception {
		Date desiredDate = null;
		DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat f2 = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat f3 = new SimpleDateFormat("yyyy-MM-dd");

		switch (x) {
		case 1:
			desiredDate = (Date) f1.parse(datevalue);
			break;
		case 2:
			desiredDate = (Date) f2.parse(datevalue);
			break;
		case 3:
			desiredDate = (Date) f3.parse(datevalue);
			break;
		}
		return desiredDate;
	}

	/**
	 * Objective: Verify if element is exist or not
	 * 
	 * @param webElement
	 */
	public boolean isElementExist(WebElement webElement) {
		try {
			threadDriver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			webElement.isDisplayed();
			return true;
		} catch (NoSuchElementException e1) {
			return false;
		} catch (NullPointerException e2) {
			return false;
		} catch (StaleElementReferenceException e3) {
			return false;
		} 
	}

	/**
	 * 判断下一页是否存在，或元素是无效状态
	 */
	public boolean isElementDisabled(WebElement element) {
		boolean isNextDisabled = true;
		try {
			isNextDisabled = element.getAttribute("class").trim()
					.equalsIgnoreCase("disabled");
		} catch (Exception e) {
			isNextDisabled = true;
		}
		return isNextDisabled;
	}

	/**
	 * 判断radio button元素是否为选中状态
	 */
	public boolean isRadioButtonChecked(WebElement element) {
		boolean isChecked = false;
		try {
			isChecked = element.getAttribute("class").contains("checked");
			threadDriver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			isChecked = false;
		}
		return isChecked;
	}

	/**
	 * 判断check box元素是否为选中状态
	 */
	public boolean isCheckboxChecked(WebElement checkBox) {
		boolean isChecked = false;
		try {
			isChecked = checkBox.getAttribute("checked").contains("true")|checkBox.getAttribute("checked").contains("checked");
			threadDriver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			isChecked = false;
		}
		return isChecked;
	}

	/**
	 * 滚动页面至指定element
	 */
	public void scrollToElement(WebElement element) throws Exception{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}

	/**
	 * 判断下一页是否存在，或元素是无效状态
	 */
	public boolean isElementReadOnly(WebElement element) {
		boolean isReadOnly = false;
		try {
			isReadOnly = element.getAttribute("readonly").trim()
					.equalsIgnoreCase("true");
			threadDriver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			isReadOnly = false;
		}
		return isReadOnly;
	}

	/**
	 * 等待某个元素显示
	 * 
	 * @throws InterruptedException
	 */
	public void waitForElementDispaly(WebElement element) throws Exception {
		boolean elementIsDisplay = false;
		int loopCount = 0;
		while (!elementIsDisplay) {
			Thread.sleep(1000);
			loopCount = loopCount + 1;
			try {
				elementIsDisplay = true;
			}catch(ElementNotVisibleException e){
				elementIsDisplay = false;
			}
			if (loopCount == 10) {
				assertFalse("Element "+element+" not display after waiting for 10 Seconds.",!elementIsDisplay);
			}
		}
	}
	
	/**
	 * 等待某个元素出现
	 * 
	 * @throws InterruptedException
	 */
	public void waitForElementExist(WebElement element) throws Exception {
		boolean elementIsExsit = false;
		int loopCount = 0;
		while (!elementIsExsit) {
			Thread.sleep(1000);
			loopCount = loopCount + 1;
			elementIsExsit = isElementExist(element);
			if (loopCount == 10) {
				assertFalse("Element "+element+" not exist after waiting for 10 Seconds.",!elementIsExsit);
			}
		}
	}

	/**
	 * 等待某个元素出现
	 * 
	 * @throws InterruptedException
	 */
	public void waitForElementExist(WebElement element, int seconds) throws Exception {
		boolean elementIsExsit = false;
		int loopCount = 0;
		while (!elementIsExsit) {
			Thread.sleep(1000);
			loopCount = loopCount + 1;
			elementIsExsit = isElementExist(element);
			if (loopCount == seconds) {
				assertFalse("Element "+element+" not exist after waiting for "+seconds+" Seconds.",!elementIsExsit);
			}
		}
	}
	
	/**
	 * Objective: wait For Page load complete
	 * 
	 * @throws Exception
	 */
	public static void waitForPageLoadComplete() {
		WebDriverWait wait = new WebDriverWait(threadDriver.get(), 20000);
		wait.until(isPageLoaded());
	}

	/**
	 * Objective: Verify Page load completed or not
	 * 
	 * @return Function<WebDriver, Boolean>
	 * @throws Exception
	 */
	public static Function<WebDriver, Boolean> isPageLoaded() {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) threadDriver.get()).executeScript(
						"return document.readyState").equals("complete");
			}
		};
	}

	/**
	 * Objective: Switch to desired window by Window Index
	 * 
	 * @param windowIndexNo
	 *            : Start from 0
	 * @throws InterruptedException
	 */
	public static void switchToWindow(int windowIndexNo)
			throws InterruptedException {
		// Initialize parameters
		boolean switchedWindow = false;
		// Get the total size of windows in current application
		int total_Size = threadDriver.get().getWindowHandles().size();
		// Switch to desired window when assign the Valid Window Index
		if (windowIndexNo < total_Size && windowIndexNo >= 0) {
			// Switch to desired window by Index No.
			Object[] windows = threadDriver.get().getWindowHandles().toArray();
			threadDriver.get().switchTo()
					.window((String) windows[windowIndexNo]);
			Thread.sleep(1000);
			switchedWindow = true;
		} else {
			assertFalse("Assigned Window Index Invalid, should be less than:"
					+ total_Size, switchedWindow);
		}

	}
	

	/**
	 * Objective: open a new window in current browser
	 * 
	 * @throws AWTException
	 */
	public static void openNewWindow() throws Exception {

		// Get the total size of windows in current application
//		WebElement body = driver.findElement(By.cssSelector("body"));
//		body.sendKeys(Keys.CONTROL  + "t");
		
		((JavascriptExecutor)driver).executeScript("window.open(\"about:blank\",'_blank')");
		
		Thread.sleep(1000);
		int total_Size = threadDriver.get().getWindowHandles().size();
		System.out.println("window Size = "+total_Size);
		switchToLastWindow();
	}

	
	public static void switchToLastWindow() throws Exception{
		// Get the total size of windows in current application
		int total_Size = threadDriver.get().getWindowHandles().size();
		int lastWindow = total_Size-1;
		
		// Switch to desired window by Index No.
		Object[] windows = threadDriver.get().getWindowHandles().toArray();
		threadDriver.get().switchTo().window((String) windows[lastWindow]);
		Thread.sleep(1000);
	}

	/**
	 * 得到某元素的序号
	 * 
	 * @throws InterruptedException
	 */
	public String getNodeNo(List<WebElement> element, String desiredText) {
		String No = null;
		for (int i = 0; i < element.size(); i++) {
			if (element.get(i).getText().contains(desiredText)) {
				No = Integer.toString(i + 1);
				break;
			}
		}
		if (No == null) {
			System.out.println("没有找到期望的节点文字" + desiredText);
		}
		return No;
	}

	/**
	 * 等待某个元素出现
	 * 
	 * @throws InterruptedException
	 */
	public void waitForElementDisappear(WebElement element) throws Exception {
		boolean elementIsExsit = true;
		int loopCount = 0;
		while (elementIsExsit) {
			Thread.sleep(1000);
			loopCount = loopCount + 1;
			elementIsExsit = isElementExist(element);
			if (loopCount == 10) {
				assertFalse("Element "+element+" still exist after waiting for 10 Seconds.",elementIsExsit);
			}
		}
	}

	/*
	 * get the value between the from valueFromText between String afterString
	 * and String beforeString
	 */
	public static String getTheValueBetweenAAndB(String originValue,
			String afterString, String beforeString) {
		String theValueGot = null;
		String showEntriesText1 = originValue.split(afterString)[1].trim();
		String showEntriesText = showEntriesText1.split(beforeString)[0].trim();
		theValueGot = showEntriesText;
		return theValueGot;
	}

}
