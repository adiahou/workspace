package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class CMS_ManageContentTypePage extends SetUp{

	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;

	@FindBy(xpath="//a[contains(text(),'内容类型')]")
	public WebElement submenu_contentType;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	
	 public  void openContentManagePage() throws Exception{
			
		 if(!menu_CMS.getAttribute("class").contains("active")){
				menu_CMS.click();
				Thread.sleep(1000);
		 }
		 submenu_contentType.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("内容类型"));
	}
}
