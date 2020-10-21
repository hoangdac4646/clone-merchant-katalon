package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import common.DataFile as DataFile

import internal.GlobalVariable

public class DataFile {
	def data = [];
	String filename = 'Data Files/';

	DataFile(){
		this.filename = this.filename + GlobalVariable.data_file_name
	}

	@Keyword
	def execute() {
		this.importFile()
		this.nomarlizeData()
		def initData = this.getStepData(0, this.data)
		GlobalVariable.merchant_ids = initData[0].split(',')
		GlobalVariable.place_ids = initData[1].split(',')
		GlobalVariable.current_mer_id = GlobalVariable.merchant_ids[0]
		GlobalVariable.current_place_id = GlobalVariable.place_ids[0]
		GlobalVariable.data = this.data
	}

	def void importFile() {
		TestData rawData = findTestData(this.filename)
		this.data = rawData.getAllData()
	}

	def nomarlizeData() {
		def processedData = this.data

		for(int i = 0; i < processedData.size(); i++) {
			processedData[i] = this.nomarlizeRow(processedData[i])
			if (processedData[i].size() == 0){
				processedData.remove(i)
				i--;
			}
		}

		this.data = processedData
		return processedData
	}

	def nomarlizeRow(def row) {
		List<Object> result = row.reverse();

		if (result.size() > 0){
			while (result[0] == '' || result[0] == null) {
				result.remove(0);
				if (result.size() == 0){
					break;
				}
			}
		}
		return result.reverse();
	}

	@Keyword
	def getStepData(int step_index, def input_data) {
		def data = input_data
		def result = []
		String nextKeyword = ''

		String keyword = GlobalVariable.step_keywords[step_index]
		if (step_index < GlobalVariable.step_keywords.size() - 1){
			nextKeyword = GlobalVariable.step_keywords[step_index + 1]
		}
		boolean lockTarget = false;

		for(int i = 0; i < data.size(); i++) {
			def row = data[i];
			if (row.size() > 0) {
				if (row[0].contains(nextKeyword)) {
					lockTarget = false;
				}

				if (lockTarget){
					if (step_index <= 4){
						result.add(row[1])
					}
					else {
						result.add(row)
					}
				}

				if (row[0].contains(keyword)) {
					lockTarget = true;
				}
			}
		}

		if (step_index > 4){
			result.remove(0)
		}
		return result;
	}
}
