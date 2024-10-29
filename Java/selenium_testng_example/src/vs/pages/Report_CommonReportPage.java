package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Report_CommonReportPage extends SetUp{

	@FindBy(xpath="//span[text()='报表系统']/../..")
	public WebElement menu_Report;
	
	@FindBy(xpath="//a[contains(text(),'常用报表')]")
	public WebElement submenu_commonReport;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//input[@value='查看报表']")
	public WebElement btn_submit;

	@FindBy(xpath=".//*[@id='start']")
	public WebElement date_start;

	@FindBy(xpath="//div[@class='datepicker-days']//th[@class='prev']")
	public WebElement btn_preMonth;

	@FindBy(xpath="//td[@class='day']")
	public List<WebElement> btns_day;

	@FindBy(xpath=".//*[@id='tp-month']/div/canvas[8]")
	public WebElement report_tpMonth;

	@FindBy(xpath=".//*[@id='page_stay_second']/div/canvas[7]")
	public WebElement report_personStay;

	@FindBy(xpath=".//*[@id='tp-device']/div/canvas[6]")
	public WebElement report_tpDevice;

	@FindBy(xpath=".//*[@id='tp-browser']/div/canvas[6]")
	public WebElement report_tpBrowser;

	@FindBy(xpath=".//*[@id='tp-department']/div/canvas[7]")
	public WebElement report_tpDepart;

	@FindBy(xpath=".//*[@id='tp-city']/div/canvas[8]")
	public WebElement report_tpCity;

	@FindBy(xpath=".//*[@id='tp-content']/div/canvas[7]")
	public WebElement report_tpContent;

	@FindBy(xpath=".//*[@id='tp-tag']/div/canvas[7]")
	public WebElement report_tpTag;

	@FindBy(xpath=".//*[@id='tag-second']/div/canvas[8]")
	public WebElement report_tagStay;


	 public  void openCommonReportPage() throws Exception{
		 if(!menu_Report.getAttribute("class").contains("active")){
			 menu_Report.click();
				Thread.sleep(1000);
		 }
		submenu_commonReport.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_submit);
		assertTrue("页面标题错误",pageTitle.getText().contains("常用报表"));
	}
	
	public void verifyCommonReports() throws Exception{
		//选择日期
		date_start.click();
		Thread.sleep(1000);
		btn_preMonth.click();
		Thread.sleep(1000);
		btns_day.get(0).click();
		btn_submit.click();
		waitForPageLoadComplete();
		
		assertTrue("Target HCP TP 报表未显示",isElementExist(report_tpMonth));
		assertTrue("Target HCP每人平均停留时长 报表未显示",isElementExist(report_personStay));
		assertTrue("设备分布 报表未显示",isElementExist(report_tpDevice));
		assertTrue("浏览器分布 报表未显示",isElementExist(report_tpBrowser));
		assertTrue("科室分布 报表未显示",isElementExist(report_tpDepart));
		assertTrue("城市地图 报表未显示",isElementExist(report_tpCity));
		assertTrue("内容分布 报表未显示",isElementExist(report_tpContent));
		assertTrue("标签分布 报表未显示",isElementExist(report_tpTag));
		assertTrue("标签停留时长 报表未显示",isElementExist(report_tagStay));
	}
}
