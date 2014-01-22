package com.pms.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.app.dao.OutsRecordDetailDao;
import com.pms.app.entity.OutsRecordDetail;
import com.pms.base.dao.BaseDao;
import com.pms.base.service.BaseService;

@Service
public class DailyOutsRecordService extends BaseService<OutsRecordDetail, String> {

	@Autowired OutsRecordDetailDao outsRecordDetailDao;
	
	@Override
	protected BaseDao<OutsRecordDetail, String> getEntityDao() {
		return outsRecordDetailDao;
	}
	
	public File generalRecordFile(String delegatorId, String supervisionCustomerId, Date date, String name) throws Exception {
		
		List<OutsRecordDetail> list = findListByQuery(delegatorId, supervisionCustomerId, date);
		
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("日常出货统计表");
		String[] title = { "序号", "日期", "款式大类", "成色", "重量规格", "数量", "总重量", "生产厂家", "出货时间", "出货后质物总量" };
		for (int i = 0; i < 10; i++) {
			s.setColumnWidth(i, 10 * 256);// 设置列的宽度
		}
		Row r = null;
		Cell c = null;
		CellStyle cMenuCellStyle = getMenuCellStyle(wb);
		CellStyle cTitleCellStyle = getTitleCellStyle(wb);
		CellStyle cOtherCellStyle = getOtherCellStyle(wb);

		s.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		r = s.createRow(0);
		c = r.createCell(0);
		c.setCellValue("日常出货统计表");
		c.setCellStyle(cTitleCellStyle);
		for (int i = 1; i <= 9; i++) {
			r.createCell(i).setCellStyle(cOtherCellStyle);
		}
		
		r = s.createRow(1);
		for (int m = 0; m < 10; m++) {
			c = r.createCell(m);
			c.setCellValue(title[m].toString());
			c.setCellStyle(cMenuCellStyle);
		}

		int row = 2;
		int order = 1;
		for (OutsRecordDetail outsRecordDetail : list) {
			r = s.createRow(row);
			c = r.createCell(0);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(order);
			
			c = r.createCell(1);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getDateStr());
			
			c = r.createCell(2);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getStyle().getName());
			
			c = r.createCell(3);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getPledgePurity().getName());
			
			c = r.createCell(4);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getSpecWeight());
			
			c = r.createCell(5);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getAmount());
			
			c = r.createCell(6);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getSumWeight());
			
			c = r.createCell(7);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getCompany());
			
			c = r.createCell(8);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getTimeStr());
			
			c = r.createCell(9);
			c.setCellStyle(cOtherCellStyle);
			c.setCellValue(outsRecordDetail.getRemainWeight());
			
			row++;
			order++;
		}
		r = s.createRow(row);
		row++;
		
		r = s.createRow(row);
		c = r.createCell(7);
		c.setCellValue("统计人:" + name);
		row++;
		
		r = s.createRow(row);
		c = r.createCell(7);
		c.setCellValue("日期:" + new DateTime().toString("yyyy-MM-dd"));
		
		String tempDir =  System.getProperty("java.io.tmpdir");
		File file = new File(tempDir + "\\" + UUID.randomUUID() + ".xls");
		FileOutputStream fileOut = new FileOutputStream(file);
	    wb.write(fileOut);
	    fileOut.close();
	    
	    return file;
	}
	
	
	private static CellStyle getTitleCellStyle(Workbook workbook) {
		CellStyle cTitleCellStyle = workbook.createCellStyle();
		Font fTitleFont = workbook.createFont();
		fTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fTitleFont.setFontHeightInPoints((short) 18);
		fTitleFont.setFontName("宋体");
		cTitleCellStyle.setFont(fTitleFont);
		cTitleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cTitleCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cTitleCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cTitleCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cTitleCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return cTitleCellStyle;
	}
	
	private static CellStyle getOtherCellStyle(Workbook workbook) {
		CellStyle cOtherCellStyle = workbook.createCellStyle();
		cOtherCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cOtherCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cOtherCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cOtherCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cOtherCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return cOtherCellStyle;
	}
	
	private static CellStyle getMenuCellStyle(Workbook workbook) {
		CellStyle cMenuCellStyle = workbook.createCellStyle();
		Font infoFont = workbook.createFont();
		infoFont.setFontName("宋体");
		cMenuCellStyle.setFont(infoFont);
		cMenuCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cMenuCellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cMenuCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cMenuCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cMenuCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cMenuCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cMenuCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cMenuCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cMenuCellStyle.setWrapText(true);
		return cMenuCellStyle;
	}

}