package Keywords;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class WebElementList implements Information {
	
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public WebElementList(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj= new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try{
	System.out.println(record[OBJECTNAME]);
	System.out.println(record[OBJECTTYPE]);
	List<WebElement> eiElement=driver.findElements(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	ELEMENTS_LIST.put(record[OBJECTNAME], eiElement);
	System.out.println(ELEMENTS_LIST.get(record[OBJECTNAME]).get(0).getText());
	cond= true;
	ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
	return Information.PASS;
	}
	catch(Exception ne){
		ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		ne.printStackTrace();
		return Information.FAIL;
	}
	
}

}


