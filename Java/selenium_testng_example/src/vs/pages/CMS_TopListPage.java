package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dxy.vs.base.SetUp;

public class CMS_TopListPage  extends SetUp{

	@FindBy(xpath="//span[text()='内容系统']/../..")
	public WebElement menu_CMS;
	
	@FindBy(xpath="//a[contains(text(),'热点内容')]")
	public WebElement submenu_top;
	
	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//th[text()='标题']/../../tr/td[5]")
	public List<WebElement> values_readCount;

	@FindBy(xpath="//th[text()='标题']/../../tr/td[1]")
	public List<WebElement> values_contentTitles;
	
	

	Front_Demo1Page front_Demo1Page = PageFactory.initElements(threadDriver.get(), Front_Demo1Page.class);


	 public  void openTopListPage() throws Exception{
		 if(!menu_CMS.getAttribute("class").contains("active")){
				menu_CMS.click();
				Thread.sleep(1000);
		 }
		submenu_top.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("热点内容"));
	}
	
	 public  void verifyTopListPage() throws Exception{
		//验证按阅读次数排序
		for(int i=0;i<values_readCount.size()-1;i++){
			int count1 = Integer.parseInt(values_readCount.get(i).getText().replaceAll("\\D+",""));
			int count2 = Integer.parseInt(values_readCount.get(i+1).getText().replaceAll("\\D+",""));
			assertTrue(count1>=count2);
		}
		
		List<String> contentTitles_backs = new ArrayList<String>();
		for(int i=0;i<values_contentTitles.size();i++){
			contentTitles_backs.add(values_contentTitles.get(i).getText().trim());
		}
	
		front_Demo1Page.openDemo1Page();
		for(int i=0;i<10;i++){
			String eachTitle_front = front_Demo1Page.links_hotList.get(i).getAttribute("title").trim();
			assertTrue(contentTitles_backs.get(i).equals(eachTitle_front));
		}
	}
	
}
