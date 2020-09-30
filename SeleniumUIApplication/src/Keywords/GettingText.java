package Keywords;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class GettingText implements Information{

	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	String displayResult = "";
	
	public GettingText(WebDriver driver,ExtentTest test) throws Exception{
		this.driver=driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			
			WebElement valueText=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
			obj.getJavaScript().highlight(valueText);	
			String valueFromCell=valueText.getText();
			System.out.println("text:"+valueFromCell);
			double convertedValue;
			valueFromCell = valueFromCell.replaceAll("\\$", "");
			valueFromCell = valueFromCell.replaceAll("k", "");
			valueFromCell=valueFromCell.replaceAll(",", "");
			valueFromCell=valueFromCell.replaceAll("%", "");
			
			if (valueFromCell.equals("-") || valueFromCell.equals(" ")) {
				convertedValue = 0;
			}     
			else {
				if (valueFromCell != null && valueFromCell.contains("(")) {
					System.out.println("Before paranthesis removal :: " + valueFromCell);
					valueFromCell = valueFromCell.replaceAll("\\(", "");
					valueFromCell = valueFromCell.replaceAll("\\)", "");
					valueFromCell = "-" + valueFromCell;
					System.out.println("After paranthesis removal :: " + valueFromCell);
				}
			
				convertedValue = Double.parseDouble(valueFromCell);
				
				
				System.out.println("Data for the xpath"+valueFromCell);
			}
			
			TABLE_DATA.put(record[OBJECTNAME], convertedValue);
			
			//System.out.println("Table Data:"+TABLE_DATA);
		return Information.PASS;
		}
		
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}
}
