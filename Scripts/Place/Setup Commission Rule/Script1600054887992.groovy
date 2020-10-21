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
String place_commission_rule_url = GlobalVariable.domain + GlobalVariable.place_commission_rule_url
place_commission_rule_url = place_commission_rule_url.replaceAll("place_id", GlobalVariable.current_place_id)
DataFile file = new DataFile()
def data = file.getStepData(4, GlobalVariable.data)

// Start
WebUI.navigateToUrl(place_commission_rule_url)
WebUI.verifyElementPresent(findTestObject('Place Commission Rule page/identify_element'), GlobalVariable.wait_timeout)

// Cách tính tiền hoa hồng
WebUI.setText(findTestObject('Place Commission Rule page/input_commission_type'), data[0])
WebUI.sendKeys(findTestObject('Place Commission Rule page/input_commission_type'), Keys.chord(Keys.ENTER))

// Số tiền hoa hồng
WebUI.setText(findTestObject('Place Commission Rule page/input_commission_value'), data[1])

// Số tiền hoa hồng tối đa
WebUI.setText(findTestObject('Place Commission Rule page/input_commission_max_value'), data[2])

// Cách tính Cashback
WebUI.setText(findTestObject('Place Commission Rule page/input_cashback_type'), data[3])
WebUI.sendKeys(findTestObject('Place Commission Rule page/input_cashback_type'), Keys.chord(Keys.ENTER))

// Số tiền Cashback
if (!data[3].equals('%')){
	WebUI.setText(findTestObject('Place Commission Rule page/input_cashback_value'), data[4])
} else {
	WebUI.setText(findTestObject('Place Commission Rule page/input_cashback_value_percent'), data[4])
}

// Số tiền Cashback tối đa
WebUI.setText(findTestObject('Place Commission Rule page/input_cashback_max_value'), data[5])

try {
	// Tạo
	WebUI.click(findTestObject('Place Commission Rule page/btn_update'))
	
} catch(Exception e) {
	println(e)
	// Cập nhật
}
