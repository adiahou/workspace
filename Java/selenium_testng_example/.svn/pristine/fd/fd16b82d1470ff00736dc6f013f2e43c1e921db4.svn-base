package dxy.vs.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import dxy.vs.base.SetUp;

public class OpsDataPage extends SetUp{

	//下一页按钮
	@FindBy(xpath="//span[contains(text(),'下一页')]")
	public WebElement btn_next;

	//下一页
	@FindBy(xpath="//span[contains(text(),'下一页')]/../..")
	public WebElement li_next;

	//首页按钮
	@FindBy(xpath="//span[contains(text(),'首页')]")
	public WebElement btn_first;

	//表格各字段
	@FindBy(xpath=".//*[@id='opdata-list']/thead/tr/th")
	public List<WebElement> titles;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath="//span[text()='运营管理']/../..")
	public WebElement menu_ops;
	
	@FindBy(xpath=".//input[@value='搜索']")
	public WebElement btn_search;

	@FindBy(xpath=".//input[@id='clear']")
	public WebElement btn_clear;
	
	@FindBy(xpath="//a[contains(text(),'互动管理')]")
	public WebElement submenu_ops;
	
	//浏览链接
	@FindBy(xpath=".//*[@id='opdata-list']//a[text()='浏览']")
	public List<WebElement> links_view;
	
	//评论选项
	@FindBy(xpath="//option[text()='评论']")
	public WebElement option_comment;

	//支持请求选项
	@FindBy(xpath="//option[text()='支持请求']")
	public WebElement option_support;

	//评论选项
	@FindBy(xpath="//option[text()='未处理']")
	public WebElement option_new;

	//开始时间
	@FindBy(xpath=".//*[@id='start']")
	public WebElement field_start;

	//结束时间
	@FindBy(xpath=".//*[@id='end']")
	public WebElement field_end;

	//日历
	@FindBy(xpath="//td[@class='day']")
	public List<WebElement> days;	
	
	
	 public  void openOpsDataManagePage() throws Exception{
		
		//点击运维数据菜单
		 if(!menu_ops.getAttribute("class").contains("active")){
				menu_ops.click();
				Thread.sleep(1000);
		 }
		submenu_ops.click();
		
		//wait for search fields display
		waitForPageLoadComplete();
		waitForElementExist(btn_search);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("互动管理"));
	}
	

	public void verifyOpsDataManagePageDisplay() throws Exception {
		//验证按日期由新到旧排序
		String dateColumnNo = getNodeNo(titles,"时间");
//		System.out.println(dateColumnNo);
		List<WebElement> date;
		boolean isNextExist = false;
		do{
			if(isElementExist(li_next)){
				isNextExist = !isElementDisabled(li_next);
			}else{
				isNextExist = false;
			}
			
			date = driver.findElements(By.xpath(".//*[@id='opdata-list']/tbody/tr/td["+dateColumnNo+"]"));
			for(int i=1;i<date.size();i++){
				String dateValue_Previous = date.get(i).getText();
				String dateValue_Current = date.get(i).getText();
			
				Date d_current = std(dateValue_Current,1);
				Date d_previous=std(dateValue_Previous,1);
				
				assertFalse("第"+i+"条数据未按日期由新到旧排序",d_current.after(d_previous));
			}
			
			if(isNextExist == true){
				btn_next.click();
				waitForPageLoadComplete();
				waitForElementExist(btn_search);
			}
		}while(isNextExist);
		
		//返回首页
		if(isElementExist(btn_first)){
			btn_first.click();
			waitForPageLoadComplete();
		}
	}
	

	public void filterOps_time() throws Exception{
		//重置清空搜索条件
		btn_clear.click();
		Thread.sleep(1000);
		
		//选择时间搜索
		field_start.click();
		Thread.sleep(1000);
		days.get(0).click();//当前月第一天
		
		field_end.click();
		Thread.sleep(1000);
		days.get(days.size()-1).click();//当前月最后一天
		
		btn_search.click();
		waitForPageLoadComplete();
		
		//验证时间
		String dateColumnNo = getNodeNo(titles,"时间");
		List<WebElement> date=driver.findElements(By.xpath(".//*[@id='opdata-list']/tbody/tr/td["+dateColumnNo+"]"));
		Date start = std(field_start.getAttribute("value"),2);
		Date end = std(field_end.getAttribute("value"),2);
		
		if(date.size()>0){
			for(WebElement d: date){
				Date dd = std(d.getText().trim(),1);	
				assertTrue("搜索日期出错: "+ dd, dd.after(start)&&dd.before(end));
			}
		}
	}
	

	public void filterOps_type(String optionTitle, String optionValue) throws Exception{
		//重置清空搜索条件
		btn_clear.click();
		Thread.sleep(1000);
				
		//搜索
		WebElement option_type = driver.findElement(By.xpath("//option[text()='"+optionValue+"']"));
		option_type.click();
		btn_search.click();
		waitForPageLoadComplete();
				
		//验证
		String typeColumnNo = getNodeNo(titles,optionTitle);
		List<WebElement> typeValue=driver.findElements(By.xpath(".//*[@id='opdata-list']/tbody/tr/td["+typeColumnNo+"]"));
		if(typeValue.size()>0){
			for(WebElement t: typeValue){
				assertTrue("搜索"+optionValue+"出错: "+t, t.getText().contains(optionValue));
			}
		}
	}
	
	public void verifyManageOpsDataPermission_client() throws Exception{
		for(WebElement eachTitle:titles){
			Assert.assertFalse(eachTitle.getText().trim().equals("用户名"),"运营管理权限：用户名仍显示");
		}
		filterOps_type("类型","支持请求");
		links_view.get(0).click();
		waitForPageLoadComplete();
	}
	
	public void verifyManageOpsDataPermission_DXY() throws Exception{
		boolean isUserNameDisplay=false;
		for(WebElement eachTitle:titles){
			if(eachTitle.getText().trim().equals("用户名")){
				isUserNameDisplay=true;
				break;
			}
		}
		Assert.assertTrue(isUserNameDisplay, "DXY运营管理权限：用户名未显示");
		filterOps_type("类型","支持请求");
		links_view.get(0).click();
		waitForPageLoadComplete();
	}
	
	public void verifyManageOpsDataPermission_other() throws Exception{
		boolean isManageDisplay = false;
		for(WebElement eachTitle: titles){
			if(eachTitle.getText().contains("管理")){
				isManageDisplay = true;
				break;
			}
		}
		Assert.assertFalse(isManageDisplay,"有 查看互动数据 而无 互动管理 权限： 管理列仍存在");
		Assert.assertTrue(links_view.size()==0,"有 查看互动数据 而无 互动管理 权限： 浏览链接仍存在");
	}
}
