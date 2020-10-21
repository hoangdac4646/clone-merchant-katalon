import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import common.DataFile as DataFile


// Validation
WebUI.callTestCase(findTestCase('Validator/Validate'), [:], FailureHandling.STOP_ON_FAILURE)

//Define
String place_option_url = GlobalVariable.domain + GlobalVariable.place_option_url
place_option_url = place_option_url.replaceAll("place_id", GlobalVariable.current_place_id)

DataFile file = new DataFile()
def data = file.getStepData(5, GlobalVariable.data)

// Start
WebUI.navigateToUrl(place_option_url)
WebUI.verifyElementPresent(findTestObject('Place Option page/identify_element'), GlobalVariable.wait_timeout)

for(int i = 0; i < data.size(); i++) {
	WebUI.click(findTestObject('Place Option page/btn_setup'))
	
	// Tiêu đề
	WebUI.setText(findTestObject('Place Option page/input_title'), data[i][0])
	
	// Giá
	WebUI.setText(findTestObject('Place Option page/input_price'), data[i][1])
	
	// Số lượng tối thiểu
	WebUI.setText(findTestObject('Place Option page/input_min_quantity'), data[i][2])
	
	// Số lượng tối đa
	WebUI.setText(findTestObject('Place Option page/input_max_quantity'), data[i][3])
	
	try {
		// Tạo
		//WebUI.click(findTestObject('Place Option page/btn_create'))
		
	} catch(Exception e) {
		println(e)
		// Cập nhật
	}
	
	if (i < data.size() - 1){
		WebUI.refresh()
	}	
}
