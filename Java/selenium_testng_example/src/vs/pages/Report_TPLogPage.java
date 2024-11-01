package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Report_TPLogPage extends SetUp{

	@FindBy(xpath="//span[text()='报表系统']/../..")
	public WebElement menu_Report;
	
	@FindBy(xpath="//a[contains(text(),'TP 日志')]")
	public WebElement submenu_TPLog;
	
	@FindBy(xpath="//a[contains(text(),'DXY TP')]")
	public WebElement submenu_DXYTPLog;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	
	 public  void openTPLogPage() throws Exception{
		 boolean isMenuExpand = menu_Report.getAttribute("class").contains("active");
		 if(!isMenuExpand){
			 menu_Report.click();
			 Thread.sleep(1000);
		 }
		 
		 submenu_TPLog.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		assertTrue("页面标题错误",pageTitle.getText().contains("TP 日志"));
	}
	 
	 public  void openDXYTPLogPage() throws Exception{
		 boolean isMenuExpand = menu_Report.getAttribute("class").contains("active");
		 if(!isMenuExpand){
			 menu_Report.click();
			 Thread.sleep(1000);
		 }
		 
		 submenu_DXYTPLog.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		assertTrue("页面标题错误",pageTitle.getText().contains("DXY TP 日志"));
	}
}
