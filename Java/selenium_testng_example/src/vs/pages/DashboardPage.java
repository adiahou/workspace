package dxy.vs.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class DashboardPage extends SetUp{

	@FindBy(xpath="//a[@class='logo']")
	public WebElement logo;

	@FindBy(xpath="//*[text()='Dashboard']")
	public WebElement text_dashboard;

	@FindBy(xpath="//div[@class='label bg-light-blue']")
	public List<WebElement> selectedSites;

	@FindBy(xpath="//a[contains(@class,'sidebar-toggle')]")
	public WebElement btn_selectSiteExpand;

	@FindBy(xpath="//input[@type='checkbox']")
	public List<WebElement> checkBoxes_site;

	@FindBy(xpath="//input[@type='checkbox']/..")
	public List<WebElement> lable_site;

	@FindBy(xpath="//input[@type='checkbox'][@value='1']")
	public WebElement checkBox_lilly;
	
	@FindBy(xpath=".//*[@id='submit']")
	public WebElement btn_submit;

	@FindBy(xpath=".//*[@id='hcp-visit']")
	public WebElement report_TPCompleted;

	@FindBy(xpath=".//*[@id='hcp-completed-month']")
	public WebElement report_TargetTP;

	@FindBy(xpath=".//*[@id='hcp-number']")
	public WebElement report_HCPCompleted;

	@FindBy(xpath=".//*[@id='hcp-engaged-month']")
	public WebElement report_TargetHCP;

	@FindBy(xpath=".//*[@id='tp-map']")
	public WebElement report_TPMap;
	
	
	public void selectOneSite(String siteName) throws Exception{
		
		boolean isDemoOnly = selectedSites.size()==1&selectedSites.get(0).getText().contains(siteName);
		if(!isDemoOnly){
			btn_selectSiteExpand.click();
			Thread.sleep(1000);
			//uncheck each checkbox
			for(WebElement eachCheckbox: checkBoxes_site){
				if(isCheckboxChecked(eachCheckbox)){
					System.out.println("uncheck action");
					eachCheckbox.click();
					Thread.sleep(500);
				}
			}
			//check demo site
			WebElement checkBox=driver.findElement(By.xpath("//label[contains(text(),'"+siteName+"')]/input[@type='checkbox']"));
			checkBox.click();
			assertTrue(siteName+"未被选中",isCheckboxChecked(checkBox));
			
			btn_submit.click();
			waitForPageLoadComplete();
		}
	}
	
	public void selectLillySites() throws Exception{
		btn_selectSiteExpand.click();
		Thread.sleep(1000);
		if(!isCheckboxChecked(checkBox_lilly)){
			checkBox_lilly.click();
			btn_submit.click();
			waitForPageLoadComplete();
		}
	}
	
	public boolean isSiteExist(String siteName) throws Exception{
		boolean isExist = false;
		
		btn_selectSiteExpand.click();
		Thread.sleep(1000);
		
		for(WebElement eachSite: lable_site){
			if(eachSite.getText().contains(siteName)){
				isExist=true;
				break;
			}
		}
		return isExist;
	}
	
	public void verifyDashboardReports(){
		assertTrue("Dashboard - TP Completed 报表未显示",isElementExist(report_TPCompleted));
		assertTrue("Dashboard - Target HCP TP 报表未显示",isElementExist(report_TargetTP));
		assertTrue("Dashboard - HCP Completed 报表未显示",isElementExist(report_HCPCompleted));
		assertTrue("Dashboard - Target HCP 报表未显示",isElementExist(report_TargetHCP));
		assertTrue("Dashboard - TP Map 报表未显示",isElementExist(report_TPMap));
	}
	
	public void verifyTPPermission_client(){
		assertTrue("Dashboard - TP Completed 报表未显示",isElementExist(report_TPCompleted));
		assertTrue("Dashboard - Target HCP TP 报表未显示",isElementExist(report_TargetTP));
		assertFalse("Dashboard - HCP Completed 报表显示了",isElementExist(report_HCPCompleted));
		assertFalse("Dashboard - Target HCP 报表显示了",isElementExist(report_TargetHCP));
		assertFalse("Dashboard - TP Map 报表显示了",isElementExist(report_TPMap));
	}
	
	public void verifyPermission_DXY(){
		assertFalse("Dashboard - TP Completed 报表显示了",isElementExist(report_TPCompleted));
		assertFalse("Dashboard - Target HCP TP 报表显示了",isElementExist(report_TargetTP));
		assertTrue("Dashboard - HCP Completed 报表未显示",isElementExist(report_HCPCompleted));
		assertTrue("Dashboard - Target HCP 报表未显示",isElementExist(report_TargetHCP));
		assertTrue("Dashboard - TP Map 报表未显示",isElementExist(report_TPMap));
	}
}
