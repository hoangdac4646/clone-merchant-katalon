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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

// Validation
WebUI.callTestCase(findTestCase('Validator/Validate'), [:], FailureHandling.STOP_ON_FAILURE)

//Define
String place_product_url = GlobalVariable.domain + GlobalVariable.place_product_url
place_product_url = place_product_url.replaceAll("place_id", GlobalVariable.current_place_id)

DataFile file = new DataFile()
def data = file.getStepData(7, GlobalVariable.data)

// Start
WebUI.navigateToUrl(place_product_url)
WebUI.verifyElementPresent(findTestObject('Place Product page/identify_element'), GlobalVariable.wait_timeout)

//// Catalog
//for(int i = 0; i < data.size(); i++) {
//	WebUI.click(findTestObject('Place Product page/Catalog/btn_setup'))
//	
//	// Tên
//	WebUI.setText(findTestObject('Place Product page/Catalog/input_name'), data[i][0])
//	
//	// Thứ tự
//	WebUI.setText(findTestObject('Place Product page/Catalog/input_weight'), data[i][1])
//	
//	try {
//		// Tạo
//		//WebUI.click(findTestObject('Place Product page/Catalog/btn_create'))
//	} catch(Exception e) {
//		println(e)
//		//WebUI.click(findTestObject('Place Product page/Catalog/btn_update'))
//		// Cập nhật
//	}
//}

//Product
data = file.getStepData(8, GlobalVariable.data)
for(int i = 0; i < data.size(); i++) {
	
	String xpath  = '//p[text()="' + data[i][0] + '"]/following::div[@class="icon-create-product"]'
	TestObject btn_setup = new TestObject("btn_setup")
	btn_setup.addProperty("xpath", ConditionType.EQUALS, xpath)
	WebUI.click(btn_setup)

	// Tên
	WebUI.setText(findTestObject('Place Product page/Product/input_name'), data[i][1])
	
	// Mô tả
	if (data[i][2] == null || data[i][2] == ''){
		data[i][2] = 'updating'
	}
	WebUI.setText(findTestObject('Place Product page/Product/input_description'), data[i][2])
	
	// Tùy chọn thêm
	if (data[i][3] == null){
		data[i][3] = ''
	}
	WebUI.setText(findTestObject('Place Product page/Product/input_more_option'), data[i][3])
	
	// Thứ tự
	WebUI.setText(findTestObject('Place Product page/Product/input_weight'), data[i][4])
	
	// Giá bán
	WebUI.setText(findTestObject('Place Product page/Product/input_old_price'), data[i][5])
	
	// Giá sale
	if (data[i][6] == null){
		data[i][6] = ''
	}
	WebUI.setText(findTestObject('Place Product page/Product/input_price'), data[i][6])
	
	// Số lượng tối thiểu
	WebUI.setText(findTestObject('Place Product page/Product/input_min_quantity'), data[i][7])
	
	// Số lượng tối đa
	WebUI.setText(findTestObject('Place Product page/Product/input_max_quantity'), data[i][8])
	
	// Phần trăm giảm
	//WebUI.setText(findTestObject('Place Product page/Product/input_discount_percent'), data[i][9])
	
	//Click upload
	WebUI.click(findTestObject('Place Product page/Product/btn_upload'))
	WebUI.click(findTestObject('Place Product page/Product/btn_upload_tab'))
	
	//Upload
	WebUI.setAttribute(findTestObject('Place Product page/Product/input_file'), data[i][10])
	
	try {
		// Tạo
		//WebUI.click(findTestObject('Place Product page/Catalog/btn_create'))
	} catch(Exception e) {
		println(e)
		//WebUI.click(findTestObject('Place Product page/Catalog/btn_update'))
		// Cập nhật
	}
}