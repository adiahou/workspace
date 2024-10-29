package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Report_CustomReportPage  extends SetUp{

	@FindBy(xpath="//span[text()='报表系统']/../..")
	public WebElement li_Report;
	
	@FindBy(xpath="//span[text()='报表系统']")
	public WebElement menu_Report;
	
	@FindBy(xpath="//a[contains(text(),'自定义报表')]")
	public WebElement submenu_customReport;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//input[@value='运行报表']")
	public WebElement btn_submit;

	@FindBy(xpath=".//*[@id='dimensionality-element']/label")
	public List<WebElement> labels_dimensionality;

	@FindBy(xpath=".//*[@id='datetime-element']/label[contains(text(),'QTD')]")
	public WebElement label_QTD;

	@FindBy(xpath=".//*[@id='hcp-element']/label[text()='Target HCP']")
	public WebElement label_Target;

	@FindBy(xpath=".//*[@id='tp-custom']/div/canvas[7]")
	public WebElement report_custom;
	

	
	 public  void openCustomReportPage() throws Exception{
		 boolean isMenuExpand = li_Report.getAttribute("class").contains("active");
		 if(!isMenuExpand){
			 menu_Report.click();
			 Thread.sleep(1000);
		 }
		 
		 submenu_customReport.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_submit);
		assertTrue("页面标题错误",pageTitle.getText().contains("自定义报表"));
	}
	 
	 public  void verifyCustomReport(){
		 labels_dimensionality.get(0).click();
		 labels_dimensionality.get(2).click();
		 label_QTD.click();
		 label_Target.click();
		 btn_submit.click();
		 waitForPageLoadComplete();
		 assertTrue("自定义报表未显示",isElementExist(report_custom));
	 }
	 
	 
}
