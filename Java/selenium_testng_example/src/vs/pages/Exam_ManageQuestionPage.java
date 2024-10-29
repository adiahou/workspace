package dxy.vs.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dxy.vs.base.SetUp;

public class Exam_ManageQuestionPage extends SetUp{

	@FindBy(xpath="//span[text()='答题系统']/../..")
	public WebElement menu_Exam;

	@FindBy(xpath="//a[contains(text(),'题库管理')]")
	public WebElement submenu_manageQuestion;

	@FindBy(xpath="//section[@class='content-header']/h1")
	public WebElement pageTitle;

	@FindBy(xpath="//a[text()='新增']")
	public WebElement btn_new;

	@FindBy(xpath="//a[text()='删除']")
	public List<WebElement> btn_delete;

	@FindBy(xpath="//table/tbody/tr/td[2]")
	public List<WebElement> values_title;

	@FindBy(xpath=".//*[@id='share-element']/label/div")
	public List<WebElement> option_Qtype;

	@FindBy(xpath=".//*[@id='type-element']/label/div")
	public List<WebElement> option_type;

	@FindBy(xpath=".//*[@id='is_must-element']/label/div")
	public List<WebElement> option_isRequired;

	@FindBy(xpath=".//*[@id='is_pageable-element']/label/div")
	public List<WebElement> option_isPageable;

	@FindBy(xpath=".//*[@id='is_show_else_field-element']/label/div")
	public List<WebElement> option_isOther;

	@FindBy(xpath=".//*[@id='mode_id']")
	public WebElement dropdown_mode;

	@FindBy(xpath=".//*[@id='score']")
	public WebElement input_score;

	@FindBy(xpath="//input[contains(@id,'answer')]")
	public List<WebElement> input_options;

	@FindBy(xpath="//a[contains(@class,'option-add')]")
	public WebElement link_addOption;

	@FindBy(xpath=".//*[@id='correct']")
	public WebElement input_correct;

	@FindBy(xpath=".//*[@id='weight']")
	public WebElement input_sequence;

	@FindBy(xpath=".//*[@id='placeholder']")
	public WebElement input_placeholder;

	@FindBy(xpath=".//*[@id='question']")
	public WebElement input_title;

	@FindBy(xpath=".//button[contains(@class,'J_content_confirm')]")
	public WebElement btn_save1;
	
	@FindBy(xpath=".//*[@id='submitbutton']")
	public WebElement btn_save2;
	
	
	 public  void openManageQuestionPage() throws Exception{
			
		 if(!menu_Exam.getAttribute("class").contains("active")){
			 menu_Exam.click();
				Thread.sleep(1000);
		 }
		 submenu_manageQuestion.click();
		waitForPageLoadComplete();
		waitForElementExist(pageTitle);
		
		assertTrue("页面标题错误",pageTitle.getText().contains("题库管理"));
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
	 
	 public  void creatQuestion_selection(String type,boolean isRequired,String correct,String squ,boolean isOtherOption, boolean isPaging) throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save1);
		 
		 String title=p("quesTitle");
		 
		 title=title+"_选择题";
		 option_Qtype.get(0).click();

		 btn_save1.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save2);
		 
		 if(type.equals("单选")){
			 title=title+"_单选";
			 option_type.get(0).click();
		 }else if(type.equals("多选")){
			 title=title+"_多选";
			 option_type.get(1).click();
		 }
		 
		 if(isRequired){
			 title=title+"_必填";
			 option_isRequired.get(1).click();
		 }else{
			 title=title+"_选填";
			 option_isRequired.get(0).click();
		 }
		 
		 if(isPaging){
			 title=title+"_分页";
			 option_isPageable.get(1).click();
		 }else{
			 title=title+"_不分页";
			 option_isPageable.get(0).click();
		 }
		 
		 WebElement mode = driver.findElement(By.xpath(".//*[@id='mode_id']/option[contains(text(),'"+p("examTitle")+"')]"));
		 mode.click();
		 input_score.clear();
		 input_score.sendKeys("30");
		 
		 input_options.get(0).sendKeys(p("option1"));
		 input_options.get(1).sendKeys(p("option2"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(2).sendKeys(p("option3"));
		 
		 link_addOption.click();
		 Thread.sleep(500);;
		 input_options.get(3).sendKeys(p("option4"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(4).sendKeys(p("option5"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(5).sendKeys(p("option6"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(6).sendKeys(p("option7"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(7).sendKeys(p("option8"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(8).sendKeys(p("option9"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(9).sendKeys(p("option10"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(10).sendKeys(p("option11"));
		 
		 if(correct!=null){
			 input_correct.clear();
			 input_correct.sendKeys(correct);
		 }
		 
		 input_sequence.clear();
		 input_sequence.sendKeys(squ);
		 
		 if(isOtherOption){
			 option_isOther.get(1).click();
			 title=title+"_有其他";
		 }else{
			 option_isOther.get(0).click();
		 }
		 
		 title=squ+" "+title;
		 input_title.clear();
		 input_title.sendKeys(title);
		 
		 btn_save2.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);
	 }
	 
	 public  void creatQuestion_dropdown(boolean isRequired,String correct,String squ,boolean isPaging) throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save1);
		 
		 String title=p("quesTitle");
		 
		 title=title+"_下拉列表";
		 option_Qtype.get(1).click();

		 btn_save1.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save2);

		 if(isRequired){
			 title=title+"_必填";
			 option_isRequired.get(1).click();
		 }else{
			 title=title+"_选填";
			 option_isRequired.get(0).click();
		 }
		 
		 if(isPaging){
			 title=title+"_分页";
			 option_isPageable.get(1).click();
		 }else{
			 title=title+"_不分页";
			 option_isPageable.get(0).click();
		 }
		 
		 WebElement mode = driver.findElement(By.xpath(".//*[@id='mode_id']/option[contains(text(),'"+p("examTitle")+"')]"));
		 mode.click();
		 input_score.clear();
		 input_score.sendKeys("30");
		 
		 input_options.get(0).sendKeys(p("option1"));
		 input_options.get(1).sendKeys(p("option2"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(2).sendKeys(p("option3"));
		 
		 link_addOption.click();
		 Thread.sleep(500);;
		 input_options.get(3).sendKeys(p("option4"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(4).sendKeys(p("option5"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(5).sendKeys(p("option6"));
		 
		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(6).sendKeys(p("option7"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(7).sendKeys(p("option8"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(8).sendKeys(p("option9"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(9).sendKeys(p("option10"));

		 link_addOption.click();
		 Thread.sleep(500);
		 input_options.get(10).sendKeys(p("option11"));
		 
		 if(correct!=null){
			 input_correct.clear();
			 input_correct.sendKeys(correct);
		 }
		 
		 input_sequence.clear();
		 input_sequence.sendKeys(squ);
		 
		 title=squ+" "+title;
		 input_title.clear();
		 input_title.sendKeys(title);
		 
		 btn_save2.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);
	 }
	 
	 public  void creatQuestion_input(boolean isRequired,String type,String squ,boolean isPaging) throws Exception{
		 btn_new.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save1);
		 
		 String title=p("quesTitle");
		 
		 title=title+"_输入框";
		 option_Qtype.get(2).click();

		 btn_save1.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_save2);

		 if(isRequired){
			 title=title+"_必填";
			 option_isRequired.get(1).click();
		 }else{
			 title=title+"_选填";
			 option_isRequired.get(0).click();
		 }
		 
		 if(isPaging){
			 title=title+"_分页";
			 option_isPageable.get(1).click();
		 }else{
			 title=title+"_不分页";
			 option_isPageable.get(0).click();
		 }
		 
		 WebElement mode = driver.findElement(By.xpath(".//*[@id='mode_id']/option[contains(text(),'"+p("examTitle")+"')]"));
		 mode.click();

		 title=title+"_"+type;
		 WebElement typeOption = driver.findElement(By.xpath(".//*[@id='data_type-element']//option[contains(text(),'"+type+"')]"));
		 typeOption.click();
		 
		 input_placeholder.clear();
		 input_placeholder.sendKeys("This is Placeholder.");
		 
		 input_sequence.clear();
		 input_sequence.sendKeys(squ);
		 
		 title=squ+" "+title;
		 input_title.clear();
		 input_title.sendKeys(title);
		 
		 btn_save2.click();
		 waitForPageLoadComplete();
		 waitForElementExist(btn_new);
	 }
}



