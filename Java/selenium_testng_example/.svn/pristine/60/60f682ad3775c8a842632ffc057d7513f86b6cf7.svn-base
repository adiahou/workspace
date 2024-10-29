package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class GrowthPage extends SetUp{

	@FindBy(xpath="//span[text()='成长计划']")
	public WebElement menu_Growth;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	
	 public  void openGrowthPage() throws Exception{
			
		 menu_Growth.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("成长计划"));
	}
}
