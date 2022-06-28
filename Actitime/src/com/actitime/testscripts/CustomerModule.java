package com.actitime.testscripts;


import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generics.BaseCLass;
import com.actitime.generics.FileLib;
import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.TaskListPage;
@Listeners(com.actitime.generics.ListenerImplementation.class)
public class CustomerModule extends BaseCLass{

	@Test
	public void testCreateCustomer() throws InterruptedException, EncryptedDocumentException, IOException {
		FileLib f=new FileLib();
		String customerName = f.getExcelData("CreateCustomer",2,2);
		String customerDescription = f.getExcelData("CreateCustomer", 2, 3);
		Reporter.log("CreateCustomer",true);
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.setTaskTab();
		TaskListPage t=new TaskListPage(driver);
		t.getAddNewBtn().click();
		t.getNewCustOptions().click();
		t.getEnterCustNameTbx().sendKeys(customerName);
		t.getEnterCustDescTbx().sendKeys(customerDescription);
		t.getSelectCustDD().click();
		t.getOurCompany().click();
		t.getCreateCustBtn().click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(t.getActualCustomer(), customerName));
		String actualCustText = t.getActualCustomer().getText();
		Assert.assertEquals(actualCustText, customerName);
	}
}
