package dxy.vs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;

public class SimPage extends SetUp {

	//Initialize Pages
	ReusableFunctions reusableFunctions;
		
	@FindBy(id="j_login_submit")
	public WebElement btn_submit;

	@FindBy(xpath="//input[@name='account']")
	public WebElement input_user;

	@FindBy(xpath="//input[@name='password']")
	public WebElement input_pw;

	@FindBy(id="j_direct_login")
	public WebElement link_ignorQR;
	
	@FindBy(xpath="//a[text()='Virtual Station']")
	public WebElement tab_VS;
	
	@FindBy(xpath="//div[text()='Virtual Station for DXY']")
	public WebElement group_VSDXY;
	
	@FindBy(xpath="//div[text()='Virtual Station for Lilly']")
	public WebElement group_VSLilly;

	@FindBy(xpath="//a[text()='进入系统']")
	public WebElement btn_enterVS;

	@FindBy(xpath="//*[contains(text(),'简洁模板')]/..")
	public WebElement div_minisite;
	
	@FindBy(xpath="//*[contains(text(),'简洁模板')]/..//i[contains(@class,'set')]")
	public WebElement icn_config;

	@FindBy(xpath="//a[contains(text(),'自动化测试组')]")
	public WebElement autoGroup;

	@FindBy(xpath="//div[contains(text(),'全选')]/input")
	public WebElement box_selectAll;

	@FindBy(xpath="//button[@ng-click='saveMenu()']")
	public WebElement btn_save;

	@FindBy(xpath="//a[@ng-click='doTheBack()']")
	public WebElement btn_back;

	@FindBy(xpath="//tr[@class='ng-scope']//input[@type='checkbox']")
	public List<WebElement> cbox_permission;

	@FindBy(xpath="//tr[@class='ng-scope']/td[@class='ng-binding']")
	public List<WebElement> name_permission;

	
	public void accessToPermissionConfig() throws Exception{

		group_VSDXY.click();
    	Thread.sleep(3000);
    	waitForElementExist(btn_enterVS);
    	
    	Actions action = new Actions(driver);
    	action.moveToElement(div_minisite).build().perform();
    	
		icn_config.click();
		waitForPageLoadComplete();	
		Thread.sleep(2000);
		
		autoGroup.click();
		Thread.sleep(500);
	}
	
	public void emptyAll() throws Exception{
		box_selectAll.click();
		Thread.sleep(500);
		box_selectAll.click();
		Thread.sleep(500);
		btn_save.click();
		Thread.sleep(3000);
		waitForPageLoadComplete();	
	}
	
	public void selectDesirePermission(String permissinName[]) throws Exception{
		//clear all permission select
		box_selectAll.click();
		Thread.sleep(500);
		box_selectAll.click();
		Thread.sleep(500);
		
		//select desire permission
		for(int i=0;i<permissinName.length;i++){
			String desirePName=permissinName[i];
			
			for(int j=0;j<name_permission.size();j++){
				String pName = name_permission.get(j).getText().trim();
				if(pName.equals(desirePName)){
					scrollToElement(cbox_permission.get(j));
					cbox_permission.get(j).click();
					Thread.sleep(500);
					break;
				}
			}
		}
		scrollToElement(btn_save);
		btn_save.click();
		Thread.sleep(3000);
		waitForPageLoadComplete();	
	}
}


