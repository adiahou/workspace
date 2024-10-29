package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Report_ViewOpsReportPage  extends SetUp{

	@FindBy(xpath="//span[text()='报表系统']/../..")
	public WebElement menu_Report;
	
	@FindBy(xpath="//a[contains(text(),'运营报表')]")
	public WebElement submenu_opsReport;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	
	 public  void openOpsReportPage() throws Exception{
		 boolean isMenuExpand = menu_Report.getAttribute("class").contains("active");
		 if(!isMenuExpand){
			 menu_Report.click();
			 Thread.sleep(1000);
		 }
		 
		 submenu_opsReport.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		assertTrue("页面标题错误",pageTitle.getText().contains("运营报表"));
	}
}
