package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import dxy.vs.base.SetUp;

public class OpsDetail_SupportPage extends SetUp{


	//支持请求详情表
	@FindBy(xpath="//dl[@class='vertical-list']")
	public WebElement form_supportFields;
	
	//支持请求详情字段
	@FindBy(xpath="//dl[@class='vertical-list']/dt")
	public List<WebElement> supportFields;
	
	//未处理选项
	@FindBy(xpath=".//*[@id='status']/option[text()='未处理']")
	public WebElement option_untreaded;
	
	//编辑链接
	@FindBy(xpath="//a[text()='编辑']")
	public WebElement btn_edit;
	
	
	//Initialize Pages
	OpsDataPage opsDataPage;

	public void verifyDetail_UntreatedSupport() throws Exception {
		
		opsDataPage = PageFactory.initElements(threadDriver.get(), OpsDataPage.class);
		
		//重置清空搜索条件
		opsDataPage.btn_clear.click();
		Thread.sleep(1000);
						
		//搜索
		opsDataPage.option_support.click();
		opsDataPage.option_new.click();
		opsDataPage.btn_search.click();
		waitForPageLoadComplete();	

		if(opsDataPage.links_view.size()>0){		
			//点击浏览链接
			opsDataPage.links_view.get(0).click();
			waitForPageLoadComplete();	
			waitForElementExist(form_supportFields);
			
			//验证状态为“未处理”
			assertTrue("支持请求详细页状态非‘未处理’。",option_untreaded.getAttribute("selected").trim().equals("true"));
		}
	}
	
	public void verifyManageOpsDataPermission_client() throws Exception{
		for(WebElement eachField: supportFields){
			Assert.assertFalse(eachField.getText().contains("用户名"), "运营管理权限：用户名仍显示");
		}
		Assert.assertFalse(isElementExist(btn_edit),"运营管理权限：支持请求编辑详细按钮存在");	
	}
	
	public void verifyManageOpsDataPermission_DXY() throws Exception{
		boolean isUsernameDisplay=false;
		for(WebElement eachField: supportFields){
			if(eachField.getText().contains("用户名")){
				isUsernameDisplay=true;
				break;
			}
		}
		Assert.assertTrue(isUsernameDisplay, "DXY运营管理权限：用户名未显示");
		Assert.assertTrue(isElementExist(btn_edit),"DXY运营管理权限：支持请求编辑详细按钮不存在");	
	}
}
