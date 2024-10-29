package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Exam_ScorePage extends SetUp{

	@FindBy(xpath="//span[text()='答题系统']/../..")
	public WebElement menu_Exam;

	@FindBy(xpath="//a[contains(text(),'答题成绩')]")
	public WebElement submenu_score;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;
	
	
	 public  void openScorePage() throws Exception{
			
		 if(!menu_Exam.getAttribute("class").contains("active")){
			 menu_Exam.click();
				Thread.sleep(1000);
		 }
		 submenu_score.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("答题成绩"));
	}
}
