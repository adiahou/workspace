package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Exam_ManageModePage extends SetUp{

	@FindBy(xpath="//span[text()='答题系统']/../..")
	public WebElement menu_Exam;

	@FindBy(xpath="//a[contains(text(),'问卷管理')]")
	public WebElement submenu_manageMode;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;

	@FindBy(xpath="//a[contains(text(),'编辑')]")
	public List<WebElement> btn_edit;

	@FindBy(xpath="//a[contains(text(),'删除')]")
	public List<WebElement> btn_delete;

	@FindBy(xpath="//table/tbody/tr/td[2]")
	public List<WebElement> values_title;

	@FindBy(xpath=".//*[@id='title']")
	public WebElement input_title;

	@FindBy(xpath=".//*[@id='show_title-element']/label/div")
	public List<WebElement> option_isTitleDisplay;

	@FindBy(xpath=".//*[@id='show_order_nu-element']/label/div")
	public List<WebElement> option_isNoDisplay;

	@FindBy(xpath=".//*[@id='type']/option")
	public List<WebElement> option_type;

	@FindBy(xpath=".//*[@id='score_line']")
	public WebElement input_scoreLine;

	@FindBy(xpath=".//*[@id='emoney']/option")
	public List<WebElement> option_eMoney;

	@FindBy(xpath=".//*[@id='times']/option")
	public List<WebElement> option_time;

	@FindBy(xpath=".//*[@id='feedback']")
	public WebElement input_feedback;

	@FindBy(xpath=".//*[@id='show_answer-element']/label/div")
	public List<WebElement> option_isAnwserDisplay;

	@FindBy(xpath=".//*[@id='status-element']/label/div")
	public List<WebElement> option_status;
	
	@FindBy(xpath=".//*[@id='submitbutton']")
	public WebElement btn_save;

	
	
	 public  void openManageExamPage() throws Exception{
			
		 if(!menu_Exam.getAttribute("class").contains("active")){
			 menu_Exam.click();
				Thread.sleep(1000);
		 }
		 submenu_manageMode.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("问卷管理"));
	}
	 
	 public  void clearOldData() throws Exception{
		 for(int i=values_title.size()-1;i>-1;i--){
				if(values_title.get(i).getText().contains("Auto_")){
					btn_delete.get(i).click();
					Thread.sleep(1000);
					clickFromAlert("yes");
					Thread.sleep(1000);
					waitForPageLoadComplete();
				}
			}
	 }
	 
	 public String createExam(String type) throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save);
		 
		 String title=p("examTitle");
		
		 switch(type){
		 case "答题": 
			 title=title+"_答题";
			 option_type.get(0).click();
			 input_feedback.sendKeys(p("examFeedback"));
			 break;
		 case "调研":
			 title=title+"_调研";
			 option_type.get(1).click();
			 input_feedback.sendKeys(p("surveyFeedback"));
			 option_isAnwserDisplay.get(1).click();
			 break;
		 case "投票":
			 title=title+"_投票";
			 option_type.get(2).click();
			 input_feedback.sendKeys(p("surveyFeedback"));
			 break;
		 }
		 
		 option_isTitleDisplay.get(1).click();
		 option_eMoney.get(3).click();
		 option_time.get(1).click();
		 title=title+"_一次";
		 input_title.sendKeys(title);
		 
		 btn_save.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);
		 
		 return title;
	 }
	 
	 public void updateExam(boolean isTitleDisplay,boolean isOnce,boolean isAnwserDisplay,boolean isEnable,boolean isNoDisplay) throws Exception{
		 for(int i=0;i<values_title.size();i++){
				if(values_title.get(i).getText().contains(p("examTitle"))){
					btn_edit.get(i).click();
					waitForPageLoadComplete();
					waitForElementExist(btn_save);
				}
			}
		 String titleUpdated = p("examTitle")+"_Update";
		 if(isTitleDisplay){
			 option_isTitleDisplay.get(1).click();
			 titleUpdated = titleUpdated+"_显示标题";
		 }else{
			 option_isTitleDisplay.get(0).click();
			 titleUpdated = titleUpdated+"_不显示标题";
		 }
		 
		 if(isNoDisplay){
			 option_isNoDisplay.get(1).click();
			 titleUpdated = titleUpdated+"_显示序号";
		 }else{
			 option_isNoDisplay.get(0).click();
			 titleUpdated = titleUpdated+"_不显示序号";
		 }
		 
		 if(isOnce){
			 option_time.get(1).click();
			 titleUpdated = titleUpdated+"_一次";
		 }else{
			 option_time.get(0).click();
			 titleUpdated = titleUpdated+"_多次";
		 }
		 
		 if(isAnwserDisplay){
			 option_isAnwserDisplay.get(1).click();
			 titleUpdated = titleUpdated+"_显示答案";
		 }else{
			 option_isAnwserDisplay.get(0).click();
			 titleUpdated = titleUpdated+"_不显示答案";
		 }
		 
		 if(isEnable){
			 option_status.get(1).click();
			 titleUpdated = titleUpdated+"_启用";
		 }else{
			 option_status.get(0).click();
			 titleUpdated = titleUpdated+"_禁用";
		 }
		 
		 input_title.clear();
		 input_title.sendKeys(titleUpdated);
		 btn_save.click();
		 waitForPageLoadComplete();
	 }
	 
	 public void createSurvey(String type) throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save);
		 
		 String title=p("examTitle");
		 if(type.equals("调研")){
			 title=title+"_调研";
			 option_type.get(1).click();
		 }else if(type.equals("投票")){
			 title=title+"_投票";
			 option_type.get(2).click();
		 }
		 
		 if(time.equals("1")){
			 title=title+"_一次";
		 }else if(time.equals("0")){
			 title=title+"_无数次";
		 }
		 
		 input_title.sendKeys(title);
		 input_feedback.sendKeys(p("examFeedback"));
		 btn_save.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);
	 }
	 
}
