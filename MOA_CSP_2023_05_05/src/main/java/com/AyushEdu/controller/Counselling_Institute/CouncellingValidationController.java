package com.AyushEdu.controller.Counselling_Institute;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CouncellingValidationController {

	public String AllowOnlyDigit(String val) {

		if (val.matches("^[0-9]+$")) {
			return "true";
		} else {
			return "Should Contains Only Digits";
		}

	}

	public String AllowOnlyDigitwithdot(String val) {

		if (val.matches("^[0-9.]+$")) {
			return "true";
		} else {
			return "Should Contains Only Decimal Value";
		}

	}
	
	public String MinimumLengthCheck(String val, int length) {

		if (val.length() < length) {
			return "Should Contains Atleast " + length + " Characters.";
		} else {
			return "true";
		}

	}

	public String MaximumLengthCheck(String val, int length,String datatype) {

		if (val.length() > length) {
			if(datatype.equalsIgnoreCase("number")) {
				return "Should Contains Maximum " + length + " Digits.";
			}else {
				return "Should Contains Maximum " + length + " Characters.";	
			}
			
		} else {
			return "true";
		}

	}

	public String OnlyAlphabeAndSpaceRegExp(String val) {

		if (val.matches("^[a-zA-Z ]*$")) {
			return "true";
		} else {
			return "Should Contains Only Alphabets and Space";
		}

	}
	
	public String OnlyAlphaNumericWithoutSpaceRegExp(String val) {

		if (val.matches("^[a-zA-Z0-9]*$")) {
			return "true";
		} else {
			return "Should Contains Only AlphaNumeric Value without Space";
		}

	}

	public String OnlyAlphabetWithoutSpaceRegExp(String val) {

		if (val.matches("^[a-zA-Z]*$")) {
			return "true";
		} else {
			return "Should Contains Only Letters without Space";
		}

	}
	
	public String checkFileFormats(MultipartFile fileDoc, String filename, String documenttype) throws IOException {
		String message = "Success";
		String contenttype = fileDoc.getContentType();
		int filesize = fileDoc.getBytes().length;
		boolean checkempty = false, checkextension = false, checkMaxfilesize = true;

		ArrayList<String> contentlist = new ArrayList<>();
		ArrayList<String> extensionList = new ArrayList<>();
		if (documenttype.equalsIgnoreCase("image")) {
			String extension = "";
			int a = filename.lastIndexOf('.');
			if (a >= 0) {
				extension = filename.substring(a + 1);
			}
			extensionList.add("JPG");
			extensionList.add("PNG");
			extensionList.add("JPEG");

			contentlist.add("image/jpeg");
			contentlist.add("image/png");
			if (!extensionList.contains(extension.toUpperCase())) {
				message = "Only JPG/PNG/JPEG file is allow to upload";
			} else if (!contentlist.contains(contenttype)) {
				message = "Only JPG/PNG/JPEG file is allow to upload";
			} else if (filesize == 0) {
				message = "Please do not attach empty file";
			} else if (filesize > 2097152) {
				message = "Please Upload Maximum 2 MB file";
			}

		}
		if (documenttype.equalsIgnoreCase("pdf")) {
			extensionList.add("PDF");

			contentlist.add("application/pdf");
			String extension = "";
			int a = filename.lastIndexOf('.');
			if (a >= 0) {
				extension = filename.substring(a + 1);
			}

			if (!extensionList.contains(extension.toUpperCase())) {
				message = "Only PDF file is accepted";
			} else if (!contentlist.contains(contenttype)) {
				message = "Only PDF file is accepted";
			} else if (filesize == 0) {
				message = "Please do not attach empty document";
			} else if (filesize > 2097152) {
				message = "Please Upload document upto Maximum 2 MB size.";
			}

		}
		if (documenttype.equalsIgnoreCase("xls")) {
			extensionList.add("XLS");

			contentlist.add("application/xls");

			contentlist.add("application/octet-stream");
			contentlist.add("application/excel");
			contentlist.add("application/vnd.ms-excel");
			contentlist.add("application/x-excel");
			contentlist.add("application/x-msexcel");
			String extension = "";
			int a = filename.lastIndexOf('.');
			if (a >= 0) {
				extension = filename.substring(a + 1);
			}

			if (!extensionList.contains(extension.toUpperCase())) {
				message = "Only XLS file is accepted";
			} else if (!contentlist.contains(contenttype)) {
				message = "Only XLS file is accepted";
			} else if (filesize == 0) {
				message = "Please do not attach empty document";
			} else if (filesize > 2097152) {
				message = "Please Upload document upto Maximum 2 MB size.";
			}

		}

		return message;
	}
}

