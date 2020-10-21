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
String merchant_carrier_url = GlobalVariable.domain + GlobalVariable.merchant_carrier_url
merchant_carrier_url = merchant_carrier_url.replaceAll("mer_id", GlobalVariable.current_mer_id)
DataFile file = new DataFile()
def data = file.getStepData(3, GlobalVariable.data)

// Start
WebUI.navigateToUrl(merchant_carrier_url)
WebUI.verifyElementPresent(findTestObject('Merchant Carrier page/identify_element'), GlobalVariable.wait_timeout)
WebUI.click(findTestObject('Merchant Carrier page/btn_setup'))

// Đối tác vận chuyển
WebUI.setText(findTestObject('Merchant Carrier page/input_carrier_partner'), data[0])
WebUI.sendKeys(findTestObject('Merchant Carrier page/input_carrier_partner'), Keys.chord(Keys.ENTER))

// Thứ tự ưu tiên
WebUI.setText(findTestObject('Merchant Carrier page/input_weight'), data[1])

try {
	// Tạo
	WebUI.click(findTestObject('Merchant Carrier page/btn_create'))
	
} catch(Exception e) {
	println(e)
	// Cập nhật
}
