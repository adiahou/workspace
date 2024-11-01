package dxy.vs.pages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.ReusableFunctions;
import dxy.vs.base.SetUp;

public class System_ManageTargetHCPPage extends SetUp{

/*******************************************************目标HCP列表页*******************************************************/
	@FindBy(xpath="//span[text()='用户管理']")
	public WebElement menu_user;

	@FindBy(xpath="//a[contains(text(),'目标HCP管理')]")
	public WebElement submenu_targetHCP;

	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;

	@FindBy(xpath=".//input[@value='搜索']")
	public WebElement btn_search;

	@FindBy(xpath=".//*[@id='clear']")
	public WebElement btn_clear;

	@FindBy(xpath=".//*[@id='dxy_id']")
	public WebElement filter_username;

	@FindBy(xpath=".//*[@id='effective_date']")
	public WebElement filter_effectiveDate;

	@FindBy(xpath=".//*[@id='is_blacklist-element']/label/div/ins")
	public List<WebElement> filter_isBlackList;

	@FindBy(xpath=".//*[@id='login']/option")
	public List<WebElement> filter_loginTime;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[1]")
	public List<WebElement> values_username;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[2]")
	public List<WebElement> values_site;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[3]")
	public List<WebElement> values_startDate;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[4]")
	public List<WebElement> values_endDate;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[5]")
	public List<WebElement> values_isBlack;

	@FindBy(xpath=".//*[@id='project-list']/tbody/tr/td[6]")
	public List<WebElement> values_loginTime;

	@FindBy(xpath="//a[text()='编辑']")
	public List<WebElement> links_edit;

	@FindBy(xpath="//a[text()='删除']")
	public List<WebElement> links_delete;

	
/*******************************************************新增、编辑目标HCP页*******************************************************/	

	@FindBy(xpath="//input[@id='dxy_id']")
	public WebElement input_username;

	@FindBy(xpath="//*[@id='effective_start_date']")
	public WebElement input_startDate;

	@FindBy(xpath="//*[@id='effective_end_date']")
	public WebElement input_endDate;

	@FindBy(xpath="//td[@class='day']")
	public List<WebElement> dateIcns;

	@FindBy(xpath="//div[@class='datepicker-days']/table//th[@class='prev']")
	public WebElement icon_prevMonth;

	@FindBy(xpath="//div[@class='datepicker-days']/table//th[@class='next']")
	public WebElement icon_nextMonth;
	
	@FindBy(xpath="//label[text()='黑名单']/../label/div/ins")
	public List<WebElement> option_isBlackList;

	@FindBy(xpath="//section[@class='content']//*[@id='submit']")
	public WebElement btn_submit;

	
	

	Front_Demo1Page front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);
	ReusableFunctions reusableFunctions = PageFactory.initElements(threadDriver.get(), ReusableFunctions.class);

	 public  void openTargetHCPManagePage(String siteName) throws Exception{
		
		//点击系统管理菜单
			//点击用户管理菜单
		 if(!menu_user.getAttribute("class").contains("active")){
			 menu_user.click();
			 Thread.sleep(1000);
		 }
		submenu_targetHCP.click();
		
		waitForPageLoadComplete();
		waitForElementExist(btn_search);
		
		//验证页面显示
		assertTrue("页面标题错误",pageTitle.getText().contains("目标HCP管理"));
		for(WebElement eachValue: values_site){
			assertTrue(eachValue.getText().trim().contains(siteName));
		}
	}
	
	 public void clearOldData() throws Exception {
		 
		 for(int i = 0; i<values_username.size(); i++){
			 String name = values_username.get(i).getText().trim();
			 if(name.equalsIgnoreCase(p("targetHCP_name"))){
				 links_delete.get(i).click();
				 Thread.sleep(1000);
				 clickFromAlert("yes");
				 Thread.sleep(1000);
				 waitForPageLoadComplete();
			 }
		 }
	 }
	 

	 public  void newTargetHCP() throws Exception{
		
		//打开新增页面
		btn_new.click();
		waitForPageLoadComplete();
		waitForElementExist(btn_submit);
		
		//输入各字段值
		input_username.sendKeys(p("targetHCP_name"));
		//选择上月第一天
		input_startDate.click();
		Thread.sleep(1000);
		icon_prevMonth.click();
		Thread.sleep(1000);
		dateIcns.get(0).click();
		//选择上月最后一天
		input_endDate.click();
		Thread.sleep(1000);
		icon_prevMonth.click();
		Thread.sleep(1000);
		dateIcns.get(dateIcns.size()-1).click();
		option_isBlackList.get(0).click();
		
		btn_submit.click();
		waitForPageLoadComplete();
		
		//列表页验证
		assertTrue(values_username.get(0).getText().trim().equalsIgnoreCase(p("targetHCP_name")));
		assertTrue(values_site.get(0).getText().trim().equalsIgnoreCase("标准模板 Demo"));
		assertTrue(values_isBlack.get(0).getText().trim().equals("否"));
		
//		//前台页面验证
//		openNewWindow();
//		front_Demo1Page.openDemo1Page(p("targetHCP_name"), p("targetHCP_pw"),true,false);
		
	}
	
	 public  void editTargetHCP() throws Exception{
		
		String desiredRow = getNodeNo(values_username,p("targetHCP_name"));
		
		int index=Integer.parseInt(desiredRow)-1;
		//打开编辑页面
		links_edit.get(index).click();
		waitForPageLoadComplete();
		//选择本月最后一天
		input_endDate.click();
		Thread.sleep(1000);
		icon_nextMonth.click();
		Thread.sleep(1000);
		dateIcns.get(dateIcns.size()-1).click();
		
		btn_submit.click();
		waitForPageLoadComplete();
		
//		//前台页面验证
//		openNewWindow();
//		front_Demo1Page.openDemo1Page(p("targetHCP_name"), p("targetHCP_pw"),true,false);
	}
	
	 public  void filterTargetHCP() throws Exception{

		int totalSize_HCP = values_username.size();
		
		submenu_targetHCP.click();
		waitForPageLoadComplete();
		
		//搜索条件“用户名=test，有效期=当前日期，黑名单为否”
		filter_username.sendKeys(p("targetHCP_searchName"));
		Thread.sleep(500);
		filter_effectiveDate.click();
		Thread.sleep(500);
		dateIcns.get(0).click();	
		Thread.sleep(500);
		//取得输入的有效期值
		String searchDay = filter_effectiveDate.getAttribute("value").trim();
		System.out.println(searchDay);
		Date searchDate = std(searchDay,3);

		filter_isBlackList.get(0).click();
		Thread.sleep(500);
		btn_search.click();
		Thread.sleep(2000);
		waitForPageLoadComplete();
		
		//验证搜索结果
		for(int i=0;i<values_username.size();i++){
			
			Date start = std(values_startDate.get(i).getText().trim(),3);
			Date end = std(values_endDate.get(i).getText().trim(),3);
			
			assertTrue("搜索结果中第"+i+"个用户名错误。",values_username.get(i).getText().contains(p("targetHCP_searchName")));
			assertFalse("搜索结果中第"+i+"个开始时间错误。",start.after(searchDate));
			assertFalse("搜索结果中第"+i+"个结束时间错误。",end.before(searchDate));
			assertTrue("搜索结果中第"+i+"个是否黑名单错误。",values_isBlack.get(i).getText().contains("否"));
		}
		
		//清空搜索条件
		btn_clear.click();
		Thread.sleep(1000);
		btn_search.click();
		waitForPageLoadComplete();
		
		assertTrue("未成功清空。",values_username.size()==totalSize_HCP);
		
		//搜索登陆次数
		filter_loginTime.get(1).click();
		btn_search.click();
		waitForPageLoadComplete();
		Thread.sleep(2000);
		
		//验证结果
		for(int i=0;i<values_username.size();i++){
			int index = i+1;
			assertTrue("搜索结果中第"+index+"个登陆次数错误。",values_loginTime.get(i).getText().trim().equals("0"));
		}
		
		//返回原始列表页
		submenu_targetHCP.click();
		waitForPageLoadComplete();
	}
	
	 public  void deleteTargetHCP() throws Exception{
		String desiredRow = getNodeNo(values_username,p("targetHCP_name"));
		int index=Integer.parseInt(desiredRow)-1;
		//点击删除链接
		links_delete.get(index).click();
		Thread.sleep(1000);
		clickFromAlert("yes");
		Thread.sleep(1000);
		waitForPageLoadComplete();
		
		//列表页验证
		for(WebElement eachName: values_username){
			assertFalse("HCP："+eachName.getText()+" 未被成功删除。", eachName.getText().trim().equals(p("targetHCP_name")));
		}
		
//		//前台验证
//		openNewWindow();
//		front_Demo1Page.openDemo1Page(p("targetHCP_name"), p("targetHCP_pw"),false,false);
	}
	
	
}
