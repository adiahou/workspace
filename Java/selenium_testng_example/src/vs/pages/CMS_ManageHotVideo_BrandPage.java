package dxy.vs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import dxy.vs.base.SetUp;

public class CMS_ManageHotVideo_BrandPage extends SetUp{

	/*****************************热点视频页**************************************/
	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;

	@FindBy(xpath="//a[contains(text(),'热点视频管理')]")
	public WebElement submenu_hotVideo;

	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//a[text()='属性']")
	public WebElement tab_property;

	@FindBy(xpath=".//*[@id='status-element']/label[2]/div")
	public WebElement icon_publish;

	@FindBy(xpath=".//*[@id='status-element']/label[1]/div")
	public WebElement icon_draft;

	/*****************************品牌信息页**************************************/

	@FindBy(xpath="//a[contains(text(),'品牌信息管理')]")
	public WebElement submenu_brand;

	
	 public  void openHotVideoPage() throws Exception{
			
		 if(!menu_CMS.getAttribute("class").contains("active")){
			 menu_CMS.click();
			 Thread.sleep(1000);
		 }
		 submenu_hotVideo.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_new);
		
//		assertTrue("页面标题错误",pageTitle.getText().contains("热点视频管理"));
	}
	 
	 public  void openBrandPage() throws Exception{
			
		 if(!menu_CMS.getAttribute("class").contains("active")){
			 menu_CMS.click();
			 Thread.sleep(1000);
		 }
		 submenu_brand.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_new);
		
//		assertTrue("页面标题错误",pageTitle.getText().contains("品牌信息管理"));
	}
	 
	 public void verifyHotVideoAndBrandPermission_client() throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(tab_property);
		 
		 tab_property.click();
		 Thread.sleep(1000);
		 Assert.assertTrue(icon_publish.getAttribute("aria-disabled").trim().equals("true"),"内容管理权限：发布状态可选。");
	 }
	 
	 public void verifyHotVideoAndBrandPermission_DXY() throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(tab_property);
		 
		 tab_property.click();
		 Thread.sleep(1000);
		 Assert.assertTrue(icon_publish.getAttribute("aria-disabled").trim().equals(icon_draft.getAttribute("aria-disabled").trim()),"DXY内容管理权限：发布状态不可选。");
	 }
}
