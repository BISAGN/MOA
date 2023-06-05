package com.AyushEdu.controller.Exp_Excel;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.servlet.view.document.AbstractXlsView;


// IMPLEMENTED BY DEVANG PATEl 01-11-2022

public  class E_Form_Gen_Excel extends AbstractXlsView  {
	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	String InstMst = "";
	String StateMst = "";
	String QuotaMst = "";
	String CAMst = "";
	String CatMst = "";

	public E_Form_Gen_Excel(String Type, List<String> TH, String Heading, String username,
			String InstMst,String StateMst,String QuotaMst,String CAMst,String CatMst) {
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
		this.InstMst = InstMst;
		this.StateMst = StateMst;
		this.QuotaMst = QuotaMst;
		this.CAMst = CAMst;
		this.CatMst = CatMst;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest arg2,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-disposition", "attachment; filename=\"" + "20/04/20201" + ".xls\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> list = (ArrayList<List<String>>) model.get("userList");

		Font cFont = workbook.createFont();
		Font dFont = workbook.createFont();

		cFont.setColor(IndexedColors.WHITE.index);
		dFont.setColor(IndexedColors.BLACK.index);

		CellStyle cs = workbook.createCellStyle();

		CellStyle csH = workbook.createCellStyle();
		csH.setFont(dFont);
		csH.setFont(dFont);

		Sheet sheet = workbook.createSheet("User List");

		Row heading = sheet.createRow(0);
		Cell cell_header = heading.createCell(0);
		cell_header.setCellValue(Heading);
		
		
		String []  listInst= InstMst.split(",");
		String []  listState= StateMst.split(",");
		String []  listQuota= QuotaMst.split(",");
		String []  listCA= CAMst.split(",");
		String []  listCat= CatMst.split(",");
		
		Sheet hidden = workbook.createSheet("abc");
		Sheet hidden1 = workbook.createSheet("abc1");
		Sheet hidden2 = workbook.createSheet("abc2");
		Sheet hidden3 = workbook.createSheet("abc3");
		Sheet hidden4 = workbook.createSheet("abc4");
		
		for (int i = 0, length= listInst.length; i < length; i++) 
		{   
			String name = listInst[i];
			Row row = hidden.createRow(i); 
			Cell cell = row.createCell(0);  
			cell.setCellValue(name);  
		} 
		for (int i = 0, length= listState.length; i < length; i++) 
		{   
			String name = listState[i];
			Row row = hidden1.createRow(i); 
			Cell cell = row.createCell(0);  
			cell.setCellValue(name);  
		} 
		for (int i = 0, length= listQuota.length; i < length; i++) 
		{   
			String name = listQuota[i];
			Row row = hidden2.createRow(i); 
			Cell cell = row.createCell(0);  
			cell.setCellValue(name);  
		} 
		for (int i = 0, length= listCA.length; i < length; i++) 
		{   
			String name = listCA[i];
			Row row = hidden3.createRow(i); 
			Cell cell = row.createCell(0);  
			cell.setCellValue(name);  
		}
		for (int i = 0, length= listCat.length; i < length; i++) 
		{   
			String name = listCat[i];
			Row row = hidden4.createRow(i); 
			Cell cell = row.createCell(0);  
			cell.setCellValue(name);  
		} 
		
		
		
		Row header = sheet.createRow(0);
		for (int h = 0; h < TH.size(); h++) {
			
			Cell cell = header.createCell(h);

			if (h==0) {
				
				Name namedCell = workbook.createName(); 
				namedCell.setNameName("hidden"+h); 
				namedCell.setRefersToFormula("abc!$A$1:$A$300"); 
				
				DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden"+h); 
				CellRangeAddressList addressList = new CellRangeAddressList(1, 1000000000, h, h);  
				HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
				validation.setSuppressDropDownArrow(false);
				workbook.setSheetHidden(1, true);  
				sheet.addValidationData(validation);
				
				cell.setCellValue(TH.get(h).split(":")[0]);
				cell.setCellStyle(csH);
			}else if (h==2) {
				
				Name namedCell = workbook.createName(); 
				namedCell.setNameName("hidden1"+h); 
				namedCell.setRefersToFormula("abc1!$A$1:$A$300"); 
				
				DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden1"+h); 
				CellRangeAddressList addressList = new CellRangeAddressList(1, 1000000000, h, h);  
				HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
				validation.setSuppressDropDownArrow(false);
				workbook.setSheetHidden(2, true);  
				sheet.addValidationData(validation);
				
				cell.setCellValue(TH.get(h).split(":")[0]);
				cell.setCellStyle(csH);
			}else if (h==14) {
				
				Name namedCell = workbook.createName(); 
				namedCell.setNameName("hidden2"+h); 
				namedCell.setRefersToFormula("abc2!$A$1:$A$300"); 
				
				DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden2"+h); 
				CellRangeAddressList addressList = new CellRangeAddressList(1, 1000000000, h, h);  
				HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
				validation.setSuppressDropDownArrow(false);
				workbook.setSheetHidden(3, true);  
				sheet.addValidationData(validation);
				
				cell.setCellValue(TH.get(h).split(":")[0]);
				cell.setCellStyle(csH);
			}else if (h==15) {
					
					Name namedCell = workbook.createName(); 
					namedCell.setNameName("hidden3"+h); 
					namedCell.setRefersToFormula("abc3!$A$1:$A$300"); 
					
					DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden3"+h); 
					CellRangeAddressList addressList = new CellRangeAddressList(1, 1000000000, h, h);  
					HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
					validation.setSuppressDropDownArrow(false);
					workbook.setSheetHidden(4, true);  
					sheet.addValidationData(validation);
					
					cell.setCellValue(TH.get(h).split(":")[0]);
					cell.setCellStyle(csH);
				
			}else if (h==16) {
				
				Name namedCell = workbook.createName(); 
				namedCell.setNameName("hidden4"+h); 
				namedCell.setRefersToFormula("abc4!$A$1:$A$300"); 
				
				DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden4"+h); 
				CellRangeAddressList addressList = new CellRangeAddressList(1, 1000000000, h, h);  
				HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
				validation.setSuppressDropDownArrow(false);
				workbook.setSheetHidden(5, true);  
				sheet.addValidationData(validation);
				
				cell.setCellValue(TH.get(h).split(":")[0]);
				cell.setCellStyle(csH);
			
		}else if (TH.get(h).contains(":")) {
				CellRangeAddressList addressList1 = new CellRangeAddressList(1, 100000, h, h);
				DVConstraint dvConstraint1 = DVConstraint
						.createExplicitListConstraint(TH.get(h).split(":")[2].split(","));
				DataValidation dataValidation1 = new HSSFDataValidation(addressList1, dvConstraint1);
				dataValidation1.setSuppressDropDownArrow(false);
				sheet.addValidationData(dataValidation1);
				cell.setCellValue(TH.get(h).split(":")[0]);
				cell.setCellStyle(csH);
			} else {
				cell.setCellValue(TH.get(h));
				cell.setCellStyle(csH);
			}
		}

		int rowNum = 1;
		for (int i = 0; i < list.size(); i++) {
			List<String> l = list.get(i);
			Row row = sheet.createRow(rowNum++);
			for (int j = 0; j < l.size(); j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(l.get(j));
				cell.setCellStyle(cs);
			}
		}
		for (int d = 0; d < TH.size(); d++) {
			sheet.autoSizeColumn(d, true);
		}

	}
}
