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
String rule_order_url = GlobalVariable.domain + GlobalVariable.rule_order_url

rule_order_url = rule_order_url.replaceAll("mer_id", GlobalVariable.current_mer_id)

DataFile file = new DataFile()
def data = file.getStepData(1, GlobalVariable.data)

// Start
WebUI.navigateToUrl(rule_order_url)

WebUI.verifyElementPresent(findTestObject('Rule Order page/identify_element'), GlobalVariable.wait_timeout)

try {
	WebUI.verifyElementPresent(findTestObject('Rule Order page/btn_setup'), 1)

	WebUI.click(findTestObject('Rule Order page/btn_setup'))
} catch(Exception e) {
	println("Exception: ${e}")
}

// Clear text
WebUI.clearText(findTestObject('Rule Order page/input_base_fee'))
WebUI.clearText(findTestObject('Rule Order page/input_base_distance'))
WebUI.clearText(findTestObject('Rule Order page/input_fee_per_km'))
WebUI.clearText(findTestObject('Rule Order page/input_fee_increment'))
WebUI.clearText(findTestObject('Rule Order page/input_fee_decrement'))
WebUI.clearText(findTestObject('Rule Order page/input_distance_increment'))
WebUI.clearText(findTestObject('Rule Order page/input_commission_type'))
WebUI.clearText(findTestObject('Rule Order page/input_commission_value'))


// Phí cơ sở
WebUI.setText(findTestObject('Rule Order page/input_base_fee'), data[0])

// Quãng đường cơ sở
WebUI.setText(findTestObject('Rule Order page/input_base_distance'), data[1])

// Cước phí 1km
WebUI.setText(findTestObject('Rule Order page/input_fee_per_km'), data[2])

// Giá trị gia tăng của đơn hàng
WebUI.setText(findTestObject('Rule Order page/input_fee_increment'), data[3])

// Mức giảm cho phí cơ sở
WebUI.setText(findTestObject('Rule Order page/input_fee_decrement'), data[4])

// Mức tăng cho quãng đường cơ sở
WebUI.setText(findTestObject('Rule Order page/input_distance_increment'), data[5])

// Loại hoa hồng
WebUI.sendKeys(findTestObject('Rule Order page/input_commission_type'), Keys.chord(Keys.ENTER))

// Phí hoa hồng
WebUI.sendKeys(findTestObject('Rule Order page/input_commission_value'), data[7])

try {
	// Tạo
	WebUI.click(findTestObject('Rule Order page/btn_setup'))
	
} catch(Exception e) {
	println(e)
	// Cập nhật
	WebUI.click(findTestObject('Rule Order page/btn_update'))
}
