package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;

public class OpsLogPage extends SetUp{

	
	@FindBy(xpath="//span[text()='运营管理']/../..")
	public WebElement menu_ops;
	
	@FindBy(xpath="//a[contains(text(),'互动日志')]")
	public WebElement submenu_opsLog;
	
	@FindBy(xpath=".//input[@value='搜索']")
	public WebElement btn_search;

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

	@FindBy(xpath="//div[contains(text(),'INFO')]")
	public WebElement content_table;

	@FindBy(xpath=".//*[@id='year']/option")
	public List<WebElement> options_year;

	@FindBy(xpath=".//*[@id='month']/option")
	public List<WebElement> options_month;
	
	
	ReusableFunctions reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);
	

	 public  void openOpsLogPage() throws Exception{
		
		//点击运维数据菜单
		 if(!menu_ops.getAttribute("class").contains("active")){
				menu_ops.click();
				Thread.sleep(1000);
		 }
		submenu_opsLog.click();
		
		//wait for search fields display
		waitForPageLoadComplete();
		waitForElementExist(btn_search);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("互动日志"));
	}
	

	public  void verifyFilters() throws Exception{
		//过滤2015-03月数据
		options_year.get(2).click();
		options_month.get(3).click();
		btn_search.click();
		waitForPageLoadComplete();
		
		//过滤内容验证
		if(isElementExist(content_table)){
			String firstLogDate = content_table.getText().substring(9, 16);
			System.out.println(firstLogDate);
			assertTrue(firstLogDate.equals("2015-03"));
		}
		
		
	}
}
