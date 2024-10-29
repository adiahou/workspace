package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.SetUp;

public class CommentsManagePage  extends SetUp{
	
	@FindBy(xpath="//span[text()='运营管理']/../..")
	public WebElement menu_ops;
	
	@FindBy(xpath="//span[text()='评论管理']")
	public WebElement menu_commentManage;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath=".//*[@id='keyword']")
	public WebElement input_search;

	@FindBy(xpath="//input[@value='搜索']")
	public WebElement btn_search;

	@FindBy(xpath="//a[text()='删除']")
	public List<WebElement> link_delete;
	
	//列表标题
	@FindBy(xpath=".//*[@id='comment-list']/thead/tr/th")
	public List<WebElement> titles;
	
	
	OpsDataPage opsDataPage;
	
	 public  void openCommentsManagePage() throws Exception{
		
		//点击运维数据菜单
		 if(!menu_ops.getAttribute("class").contains("active")){
				menu_ops.click();
				Thread.sleep(1000);
		 }
		menu_commentManage.click();
		
		//wait for search fields display
		waitForPageLoadComplete();
		waitForElementExist(btn_search);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("评论管理"));
	}
	

	 public  void searchComments() throws Exception{
		
		String searchValue_content = "评论";
		String searchValue_title = "青少年";
		String columnNo_content = getNodeNo(titles,"内容");
		String columnNo_title = getNodeNo(titles,"标题");
		List<WebElement> value_content = null;
		List<WebElement> value_title = null;
		
		//输入内容搜索的值并搜索
		input_search.clear();
		input_search.sendKeys(searchValue_content);
		Thread.sleep(1000);
		btn_search.click();
		waitForPageLoadComplete();
		waitForElementExist(titles.get(0));
		value_content = driver.findElements(By.xpath("//table[@id='comment-list']/tbody/tr/td["+columnNo_content+"]"));
		value_title = driver.findElements(By.xpath("//table[@id='comment-list']/tbody/tr/td["+columnNo_title+"]"));
		
		//验证搜索结果		
		for(int i=0;i<value_content.size();i++){
			boolean isMatch_content =  value_content.get(i).getText().contains(searchValue_content);
			boolean isMatch_title =  value_title.get(i).getText().contains(searchValue_content);
			assertTrue("第"+i+"条数据与搜索值（"+searchValue_content+"）不匹配",isMatch_content | isMatch_title);
		}
		
		//输入标题搜索的值并搜索
		input_search.clear();
		input_search.sendKeys(searchValue_title);
		Thread.sleep(1000);
		btn_search.click();
		waitForPageLoadComplete();
		value_content = driver.findElements(By.xpath("//table[@id='comment-list']/tbody/tr/td["+columnNo_content+"]"));
		value_title = driver.findElements(By.xpath("//table[@id='comment-list']/tbody/tr/td["+columnNo_title+"]"));
		
		//验证搜索结果		
		for(int i=0;i<value_content.size();i++){
			boolean isMatch_content =  value_content.get(i).getText().contains(searchValue_title);
			boolean isMatch_title =  value_title.get(i).getText().contains(searchValue_title);
			assertTrue("第"+i+"条数据与搜索值（"+searchValue_title+"）不匹配",isMatch_content | isMatch_title);
		}
	}
	

	 public  void deleteComments() throws Exception{
		
		opsDataPage = PageFactory.initElements(threadDriver.get(), OpsDataPage.class);
	    
		String time1 = null;
		String columnNo_status1 = getNodeNo(titles,"状态");
		List<WebElement> value_status1 = driver.findElements(By.xpath("//table[@id='comment-list']/tbody/tr/td["+columnNo_status1+"]"));
		String columnNo_time1 = getNodeNo(titles,"时间");
		List<WebElement> value_time1 = driver.findElements(By.xpath("//table[@id='comment-list']/tbody/tr/td["+columnNo_time1+"]"));
		
		//选择第一条评论点击删除
		for(int i=0;i<value_status1.size();i++){
			if(!value_status1.get(i).getText().isEmpty()){
				time1 = value_time1.get(i).getText().trim();
				System.out.println(time1);
				link_delete.get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
		clickFromAlert("yes");
		Thread.sleep(1000);
		waitForPageLoadComplete();
		
		//到互动管理页面验证评论已删除
		opsDataPage.openOpsDataManagePage();
		opsDataPage.filterOps_type("类型", "评论");
		
		int num = 0;
		String columnNo_status2 = getNodeNo(opsDataPage.titles,"状态");
		List<WebElement> value_status2 = driver.findElements(By.xpath("//table[@id='opdata-list']/tbody/tr/td["+columnNo_status2+"]"));
		String columnNo_time2 = getNodeNo(opsDataPage.titles,"时间");
		List<WebElement> value_time2 = driver.findElements(By.xpath("//table[@id='opdata-list']/tbody/tr/td["+columnNo_time2+"]"));
		
		for(int i=0;i<value_time2.size();i++){
			if(value_time2.get(i).getText().trim().equals(time1)){
				num = i;
				System.out.println("已找到："+i);
				break;
			}
		}
		assertTrue("评论未成功删除。",value_status2.get(num).getText().contains("已删除"));
	}
	
}
