package dxy.vs.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import dxy.vs.base.SetUp;

public class OpsViewHCPPage extends SetUp{

	@FindBy(xpath="//span[text()='用户管理']/../..")
	public WebElement menu_user;
	
	@FindBy(xpath="//a[contains(text(),'HCP管理')]")
	public WebElement submenu_viewHCP;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath="//span[contains(text(),'下一页')]")
	public WebElement btn_next;

	@FindBy(xpath="//span[contains(text(),'上一页')]")
	public WebElement btn_previous;

	@FindBy(xpath="//span[contains(text(),'首页')]")
	public WebElement btn_first;

	@FindBy(xpath="//span[contains(text(),'末页')]")
	public WebElement btn_last;

	@FindBy(xpath=".//*[@id='opdata-list']/tbody/tr[1]/td[2]")
	public WebElement content_table;

	@FindBy(xpath=".//*[@id='opdata-list']//a[text()='浏览']")
	public List<WebElement> link_detail;

	@FindBy(xpath="//dl[@class='vertical-list']/dt")
	public List<WebElement> fields_detailInfo;

	//表格各字段
	@FindBy(xpath=".//*[@id='opdata-list']/thead/tr/th")
	public List<WebElement> titles;

	
	
	

	 public  void openViewHCPPage() throws Exception{
		
		//点击运维数据菜单
		 if(!menu_user.getAttribute("class").contains("active")){
			 menu_user.click();
				Thread.sleep(1000);
		 }
		submenu_viewHCP.click();
		
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("HCP管理"));
	}
	
	 public  void verifyViewHCPPermission_client() throws Exception{
		 for(WebElement eachField: fields_detailInfo){
			 assertFalse("查看HCP互动数据详细权限：可查看用户名",eachField.getText().trim().contains("用户名"));
		 }
	 }
	 
	 public  void verifyViewHCPPermission_DXY() throws Exception{
		 boolean isUsernameDisplay=false;
		 for(WebElement eachField: fields_detailInfo){
			 if(eachField.getText().trim().contains("用户名")){
				 isUsernameDisplay=true;
				 break;
			 }
		 }
		 assertTrue("DXY查看HCP互动数据详细权限：用户名不显示",isUsernameDisplay);
	 }
	 
	 public  void verifyViewHCPPermission_other() throws Exception{
		 boolean isManageDisplay = false;
			for(WebElement eachTitle: titles){
				if(eachTitle.getText().contains("操作")){
					isManageDisplay = true;
					break;
				}
			}
			Assert.assertFalse(isManageDisplay,"有 查看HCP互动数据 而无 查看HCP互动数据详细 权限： 管理列仍存在");
			Assert.assertTrue(link_detail.size()==0,"有 查看HCP互动数据 而无 查看HCP互动数据详细 权限： 浏览链接仍存在");
	 }
}
