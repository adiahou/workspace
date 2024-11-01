package dxy.vs.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Template_AuditTemplatePage extends SetUp{
	
	@FindBy(xpath="//span[text()='模板系统']/../..")
	public WebElement menu_Template;

	@FindBy(xpath="//a[contains(text(),'模板审核')]")
	public WebElement submenu_auditTemplate;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//a[text()='拒绝']")
	public WebElement link_refuse;

	@FindBy(xpath="//a[text()='通过']")
	public WebElement link_pass;

	@FindBy(xpath="//table/tbody/tr[2]/td[2]")
	public WebElement request1_name;

	@FindBy(xpath="//table/tbody/tr[2]/td[3]")
	public WebElement request1_color;

	@FindBy(xpath="//table/tbody/tr[2]/td[7]")
	public WebElement request1_status;

	
	
	 public  void openAuditTemplatePage() throws Exception{
		
		//点击系统管理菜单
		 if(!menu_Template.getAttribute("class").contains("active")){
				menu_Template.click();
				Thread.sleep(1000);
		 }
		submenu_auditTemplate.click();
		
		waitForPageLoadComplete();
		
		//验证页面显示
		assertTrue("页面标题错误",pageTitle.getText().contains("模板审核"));
	}
	
	 public  void verifyTemplatePermission_client() throws Exception{
		assertFalse("模板审核权限：拒绝按钮仍存在",isElementExist(link_refuse));
		assertTrue(request1_status.getText().trim().equals("正在审核"));
	 }

	 public  void verifyTemplatePermission_DXY() throws Exception{
		 assertTrue("DXY模板审核权限：拒绝按钮不存在",isElementExist(link_refuse));
		assertTrue("DXY模板审核权限：通过按钮不存在",isElementExist(link_pass));
	 }

	 public  void clearRequest() throws Exception{
		if(isElementExist(link_refuse)){
			link_refuse.click();
			Thread.sleep(1000);
			clickFromAlert("yes");
			Thread.sleep(1000);
			waitForPageLoadComplete();
			assertFalse(isElementExist(link_refuse));
		}
	}
	
	public void verifySubmitRequst() {
		//打开模板审核页面
		submenu_auditTemplate.click();
		waitForPageLoadComplete();
		//验证模板申请
		assertTrue(isElementExist(link_refuse));
		assertTrue(request1_name.getText().trim().equals("标准模板"));
		assertTrue(request1_color.getText().trim().equals("中国红"));
		assertTrue(request1_status.getText().trim().equals("正在审核"));
	}
	
}
