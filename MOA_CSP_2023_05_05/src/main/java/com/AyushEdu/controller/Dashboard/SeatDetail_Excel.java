package com.AyushEdu.controller.Dashboard;

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

public class SeatDetail_Excel  extends AbstractXlsView{
	
	String Type = "";
	List<String> TH;
	String Heading = "";

	 List<Map<String, Object>> ms;

	public SeatDetail_Excel(String Type, List<String> TH, List<Map<String, Object>> ms, String Heading
			) {
		this.Type = Type;
		this.TH = TH;
		this.ms = ms;
		this.Heading = Heading;
//		this.role = role;

	}



	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest arg2,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-disposition", "attachment; filename=\"" + "20/04/20201" + ".xls\"");

		@SuppressWarnings("unchecked")
//		ArrayList<List<String>> list = (ArrayList<List<String>>) model.get("userList");
		
//		System.err.println("listttttttttttt" + list);

		Font cFont = workbook.createFont();
		Font dFont = workbook.createFont();

		cFont.setColor(IndexedColors.WHITE.index);
		dFont.setColor(IndexedColors.BLACK.index);

		CellStyle cs = workbook.createCellStyle();

		CellStyle csH = workbook.createCellStyle();
		csH.setFont(dFont);
		csH.setFont(dFont);

		Sheet sheet = workbook.createSheet("User List");
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

		List<Map<String, Object>> aList = (List<Map<String, Object>>) ms;
		System.err.println("aListtttttttttttttt" + aList);

		int rowNum = 1;
//		for (int i = 0; i < list.size(); i++) {
//			List<String> l = (List<String>) list.get(i);
//			Row row = sheet.createRow(rowNum++);
//			for (int j = 0; j < l.size(); j++) {
//				Cell cell = row.createCell(j);
//				cell.setCellValue(l.get(j));
//				cell.setCellStyle(cs);
//			}
//		}
		for (int d = 0; d < TH.size(); d++) {
			sheet.autoSizeColumn(d, true);
		}
		List<String> keys = new ArrayList<String>();
		keys.add("ser");
		keys.add("name");
		keys.add("available");
		keys.add("allocated");
		keys.add("total");
		
		System.err.println("--size-" + aList);
		for (int i = 0; i < aList.size(); i++) {
			
			Map<String, Object> l = aList.get(i);
//			int total  = (int) l.get("total");
//			int available  = (int)l.get("available");
//			l.put("allocated", total-available); 
//			System.err.println("allocated - - - "+l.get("allocated"));
			Row row1 = sheet.createRow(rowNum++);
			for (int j = 0; j < 5; j++) {
				Cell cell = row1.createCell(j);
				if (l.get(keys.get(j)) == null) {
					cell.setCellValue( "0");
				}
				else
					cell.setCellValue( String.valueOf(l.get(keys.get(j))));
				cell.setCellStyle(cs);
			}
		}
	}

}
