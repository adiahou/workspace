package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Template_ManageTemplatePage extends SetUp{

	@FindBy(xpath="//span[text()='模板系统']/../..")
	public WebElement menu_Template;

	@FindBy(xpath="//a[contains(text(),'模板管理')]")
	public WebElement submenu_manageTemplate;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	
	 public  void openManageTemplagePage() throws Exception{
			
		 if(!menu_Template.getAttribute("class").contains("active")){
			 menu_Template.click();
				Thread.sleep(1000);
		 }
		 submenu_manageTemplate.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("模板管理"));
	}
}
