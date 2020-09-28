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
import common.helper as helper

String login_url = GlobalVariable.domain + GlobalVariable.login_url

WebUI.openBrowser(login_url)

if (GlobalVariable.current_url == '') {

    GlobalVariable.current_url = login_url

    GlobalVariable.is_login = true
}

WebUI.verifyElementPresent(findTestObject('Login page/identify_element'), GlobalVariable.wait_timeout)

WebUI.setText(findTestObject('Login page/input_username'), GlobalVariable.username)

WebUI.setText(findTestObject('Login page/input_password'), GlobalVariable.password)

WebUI.click(findTestObject('Login page/btn_login'))

landingPage = WebUI.verifyElementPresent(findTestObject('Main page/identify_element'), GlobalVariable.wait_timeout)

