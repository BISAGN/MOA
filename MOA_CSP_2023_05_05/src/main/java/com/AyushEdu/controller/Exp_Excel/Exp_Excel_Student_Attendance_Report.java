package com.AyushEdu.controller.Exp_Excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class Exp_Excel_Student_Attendance_Report extends AbstractXlsView {

	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	
	List<ArrayList<String>> listofdata;
	String atndnce_date="";
	String course_name="";
	

	public Exp_Excel_Student_Attendance_Report(String Type, List<String> TH,List<ArrayList<String>> listofdata,String attendance_date,String course, String Heading, String username) {
		this.Type = Type;
		this.TH = TH;
		this.listofdata = listofdata;
		this.Heading = Heading;
		this.username = username;
		this.atndnce_date=attendance_date;
		this.course_name=course;
//		System.err.println("listofdataaaaaaexc"+listofdata);
//		System.err.println("attendance_dateeeeeeeeeeeeeeeeeeeee"+attendance_date);
//		System.err.println("courseeeeeeeeeeeeeeeeeeeeeeeeee"+course);
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest arg2,
			HttpServletResponse response) throws Exception {
//		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
//		String file_name = datetimestamp.currentDateWithTimeStampString();
		response.setHeader("Content-disposition", "attachment; filename=\"" + "20/04/20201" + ".xls\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> list = (ArrayList<List<String>>) model.get("userList");
		System.err.println("listttttttttttt"+list);

		Font cFont = workbook.createFont();
		Font dFont = workbook.createFont();

		cFont.setColor(IndexedColors.WHITE.index);
		dFont.setColor(IndexedColors.BLACK.index);

		CellStyle cs = workbook.createCellStyle();

		CellStyle csH = workbook.createCellStyle();
		csH.setFont(dFont);
		csH.setFont(dFont);

		Sheet sheet = workbook.createSheet(course_name);
		final CellCopyPolicy options = new CellCopyPolicy();
		options.setCopyCellValue(false);

		Row heading = sheet.createRow(0);
		Cell cell_header = heading.createCell(0);
		cell_header.setCellValue(Heading);

		Row header = sheet.createRow(0);
		for (int h = 0; h < TH.size(); h++) {
			Cell cell = header.createCell(h);
			//
			if (TH.get(h).contains(":")) {
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
		
		ArrayList<ArrayList<String>> aList = (ArrayList<ArrayList<String>>) listofdata;
		System.err.println("aListtttttttttttttt"+aList);

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
		
		for (int i = 0; i < aList.size(); i++) {
			List<String> l = aList.get(i);
			Row row1 = sheet.createRow(rowNum++);
			for (int j = 0; j < 2; j++) {
				
				Cell cell = row1.createCell(j);
				cell.setCellValue(l.get(j));
				cell.setCellStyle(cs);
				
				if(j == 1) {
					System.err.println("attendance_date------------------------------"+atndnce_date);
					Cell adcell = row1.createCell(j+1);
					adcell.setCellValue(atndnce_date);
					adcell.setCellStyle(cs);
				}
				
			}
		}

	}
}