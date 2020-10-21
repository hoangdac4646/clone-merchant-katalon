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
String place_option_group_url = GlobalVariable.domain + GlobalVariable.place_option_group_url
place_option_group_url = place_option_group_url.replaceAll("place_id", GlobalVariable.current_place_id)

DataFile file = new DataFile()
def data = file.getStepData(6, GlobalVariable.data)

// Start
WebUI.navigateToUrl(place_option_group_url)
WebUI.verifyElementPresent(findTestObject('Place Option Group page/identify_element'), GlobalVariable.wait_timeout)

for(int i = 0; i < data.size(); i++) {
	WebUI.click(findTestObject('Place Option Group page/btn_setup'))
	
	// Tiêu đề
	WebUI.setText(findTestObject('Place Option Group page/input_title'), data[i][0])
	
	// Tùy chọn
	String[] split_string = data[i][1].split(',')
	for(int j = 0; j < split_string.size(); j++) {
		println(split_string[j])
		WebUI.setText(findTestObject('Place Option Group page/input_option'), split_string[j])
		WebUI.sendKeys(findTestObject('Place Commission Rule page/input_option'), Keys.chord(Keys.ENTER))
	}
	
	// Số lượng tối thiểu
	WebUI.setText(findTestObject('Place Option Group page/input_min_quantity'), data[i][2])
	
	// Số lượng tối đa
	WebUI.setText(findTestObject('Place Option Group page/input_max_quantity'), data[i][3])
	
	try {
		// Tạo
		//WebUI.click(findTestObject('Place Option Group page/btn_create'))
		
	} catch(Exception e) {
		println(e)
		//WebUI.click(findTestObject('Place Option Group page/btn_update'))
		// Cập nhật
	}
	
	if (i < data.size() - 1){
		WebUI.refresh()
	}
}
