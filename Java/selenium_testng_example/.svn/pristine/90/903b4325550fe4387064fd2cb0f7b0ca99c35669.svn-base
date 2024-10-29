package dxy.vs.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.SetUp;

public class UserManagePage extends SetUp{

	@FindBy(xpath="//span[text()='用户管理(超管)']")
	public WebElement menu_user;

	@FindBy(xpath="//a[contains(text(),'刷新权限')]")
	public List<WebElement> btn_refresh;

	
	
	SimPage simPage;
	
	 public  void openAndRefreshUserPermission() throws Exception{
			
		simPage = PageFactory.initElements(threadDriver.get(), SimPage.class);
		 
		simPage.btn_back.click();
		waitForElementExist(simPage.btn_enterVS);
		simPage.btn_enterVS.click();
		Thread.sleep(2000);
        switchToLastWindow();
        waitForPageLoadComplete();
		
		menu_user.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_refresh.get(0));
		
		btn_refresh.get(0).click();
		waitForPageLoadComplete();		
		Thread.sleep(1000);
	}
}
