package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class System_HCPLoginLogPage extends SetUp{

	@FindBy(xpath="//span[text()='用户管理']/../..")
	public WebElement menu_user;

	@FindBy(xpath="//a[contains(text(),'登录日志')]")
	public WebElement submenu_HCPLogin;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath=".//input[@value='搜索']")
	public WebElement btn_search;

	@FindBy(xpath="//span[contains(text(),'下一页')]")
	public WebElement btn_next;

	@FindBy(xpath="//span[contains(text(),'上一页')]")
	public WebElement btn_previous;

	@FindBy(xpath="//span[contains(text(),'首页')]")
	public WebElement btn_first;

	@FindBy(xpath="//span[contains(text(),'末页')]")
	public WebElement btn_last;

	@FindBy(xpath=".//*[@id='year']/option")
	public List<WebElement> options_year;

	@FindBy(xpath=".//*[@id='month']/option")
	public List<WebElement> options_month;
	
	@FindBy(xpath=".//*[@id='is_target_hcp']/option")
	public List<WebElement> options_isTarget;

	@FindBy(xpath=".//input[@id='dxy_id']")
	public WebElement input_dxyId;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr[1]/td[1]")
	public WebElement content;
	
	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[1]")
	public List<WebElement> values_dxyid;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[2]")
	public List<WebElement> values_loginTime;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[3]")
	public List<WebElement> values_username;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[5]")
	public List<WebElement> values_isTarget;


	
	
	 public  void openHCPLoginLogPage() throws Exception{
		
		//点击系统管理菜单
		 if(!menu_user.getAttribute("class").contains("active")){
			 menu_user.click();
				Thread.sleep(1000);
		 }
		submenu_HCPLogin.click();
		
		waitForPageLoadComplete();
		waitForElementExist(btn_search);
		
		//验证页面显示
		assertTrue("页面标题错误",pageTitle.getText().contains("登录日志"));
	}
	

	 public  void verifyFilters() throws Exception{
		
		//搜索2015-5月，不是目标HCP，用户名为ddxxyyss
		options_year.get(1).click();
		options_month.get(5).click();
		options_isTarget.get(1).click();
		input_dxyId.sendKeys(p("searchHCP_name"));
		btn_search.click();
		waitForPageLoadComplete();
		
		//验证结果
		for(int i=0;i<values_loginTime.size();i++){
			assertTrue("首页搜索结果时间错误。",values_loginTime.get(i).getText().contains("2015-05"));
			assertTrue("首页搜索结果用户名错误。",values_username.get(i).getText().contains(p("searchHCP_name")));
			assertTrue("首页搜索结果是否目标HCP错误。",values_isTarget.get(i).getText().contains("否"));
		}
		if(isElementExist(btn_last)){
			btn_last.click();
			waitForPageLoadComplete();
			for(int i=0;i<values_loginTime.size();i++){
				assertTrue("末页搜索结果时间错误。",values_loginTime.get(i).getText().contains("2015-05"));
				assertTrue("末页搜索结果用户名错误。",values_username.get(i).getText().contains(p("searchHCP_name")));
				assertTrue("末页搜索结果是否目标HCP错误。",values_isTarget.get(i).getText().contains("否"));
			}
		}
		
		//搜索是目标HCP，dxy ID为80c6b89b76f35df27fe1f36d7362fd0e
		submenu_HCPLogin.click();
		waitForPageLoadComplete();
		options_isTarget.get(2).click();
		input_dxyId.sendKeys(p("searchHCP_dxyid"));
		btn_search.click();
		waitForPageLoadComplete();
		
		//验证结果
		for(int i=0;i<values_dxyid.size();i++){
			assertTrue("首页搜索结果用户名错误。",values_dxyid.get(i).getText().contains(p("searchHCP_dxyid")));
			assertTrue("首页搜索结果是否目标HCP错误。",values_isTarget.get(i).getText().contains("是"));
		}
		if(isElementExist(btn_last)){
			btn_last.click();
			waitForPageLoadComplete();
			for(int i=0;i<values_loginTime.size();i++){
				assertTrue("末页搜索结果用户名错误。",values_dxyid.get(i).getText().contains(p("searchHCP_dxyid")));
				assertTrue("末页搜索结果是否目标HCP错误。",values_isTarget.get(i).getText().contains("是"));
			}
		}
	}
		
}
