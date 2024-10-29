package dxy.vs.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class ScreenshotListener extends TestListenerAdapter {

  @Override
  public void onTestFailure(ITestResult tr) {
	
	//New File with desired Name
	String fileFolderName = "Screenshots" ;
	
	//Get current time mills and convert to desired Format
	SimpleDateFormat dateFormatType = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	Date resultdate = new Date(System.currentTimeMillis());
	
	//Define File with desired format
    File screenshot = new File(fileFolderName + File.separator + dateFormatType.format(resultdate)+ "_" +  tr.getName() + ".png");
    
    //Delete the sub-files if it is already existing to let the Folder only record the last failed screenshot for the same scipt
    if (screenshot.getParentFile().isDirectory()) {
        for (File subFile : screenshot.getParentFile().listFiles()){
        	 Path pathForSubFile = Paths.get(subFile.getAbsolutePath().toString());
        	 String subFileName = pathForSubFile.getFileName().toString();
        	 if(subFileName.contains(tr.getName())){
        		 subFile.delete();
        	 }
    	}
      }
    
    //Create the folder and File when both of them are empty
    if (!screenshot.exists()) {
        new File(screenshot.getParent()).mkdirs();
        try {
          screenshot.createNewFile();
          System.out.println("Screenshot "+screenshot.getName()+" is created.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    
  
    try {
      new FileOutputStream(screenshot).write(((TakesScreenshot) SetUp.driver).getScreenshotAs(OutputType.BYTES));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
