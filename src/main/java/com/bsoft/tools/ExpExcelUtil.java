package com.bsoft.tools;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 导出Excel 公用类
 * 
 * @author wms1231
 *
 */
public class ExpExcelUtil {
	private static final Logger log = LoggerFactory.getLogger(ExpExcelUtil.class);
	private static HSSFWorkbook WORK_BOOK = null;// 工作表 设置为全局加样式时用到
	private static short HEAD_FONT_SIZE = 16;// 表格头字体大小
	private static short TITLE_FONT_SIZE = 14;// 表格标题字体大小
	private static short CONTENT_FONT_SIZE = 12;// 表格内容字体大小
	private static short HEAD_ROW_HEIGHT = 410;// 表格头行高度
	private static short TITLE_ROW_HEIGHT = 350;// 表格标题行高度
	private static short CONTENT_ROW_HEIGHT = 320;// 表格内容行高度
	private static String HEAD_FONT_NAME = "黑体";// 表格头字体样式
	private static String TITLE_FONT_NAME = "宋体";// 表格标题字体样式
	private static String CONTENT_FONT_NAME = "宋体";// 表格内容字体样式
	private static String DEFAULT_SHEET_NAME = "Sheet"; // 默认Sheet名称

	/**
	 * 创建一个excel文件
	 * 
	 * @return
	 */
	public static HSSFWorkbook createExcel() {
		
		return new HSSFWorkbook();
	}

	
	/**
	 * 创建一个精简的HSSFWorkbook对象
	 * 
	 * @param headTitle
	 * @param sheetName
	 * @param titleList
	 * @param rowList
	 * @return
	 */
	public static HSSFWorkbook getSimpleWorkbook(String headTitle, String sheetName, List<String> titleList,
			List<List<String>> rowList) {
		if (WORK_BOOK != null) {
			WORK_BOOK = null;
		}
		WORK_BOOK = createExcel();
		
		HSSFCellStyle headStyle = createHeadStyle(WORK_BOOK);
		sheetName = "".equals(sheetName) ? DEFAULT_SHEET_NAME : sheetName;
		HSSFSheet hssfSheet = WORK_BOOK.createSheet(sheetName);
		int row = 0;

		if (StringUtils.isNotBlank(headTitle)) {
			int length = titleList.size();
			length = length > 0 ? length - 1 : length;
			HSSFRow headRow = hssfSheet.createRow(row);
			row++;
			HSSFCell headerCell = headRow.createCell(0);
			headerCell.setCellValue(headTitle);
			headerCell.setCellStyle(headStyle);
			hssfSheet.autoSizeColumn(0);
			headRow.setHeight(HEAD_ROW_HEIGHT);
			hssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, length));
		}

		HSSFRow titleRow = hssfSheet.createRow(row);
		row++;
		for (int i = 0; i < titleList.size(); i++) {
			HSSFCell cell = titleRow.createCell(i);
			cell.setCellValue(titleList.get(i));
		}
		for (int i = 0; i < rowList.size(); i++) {
			HSSFRow hssfRow = hssfSheet.createRow(row + i);
			hssfRow.setHeight(CONTENT_ROW_HEIGHT);
			List<String> cellList = rowList.get(i);
			for (int j = 0; j < cellList.size(); j++) {
				HSSFCell cell = hssfRow.createCell(j);
				cell.setCellValue(cellList.get(j));
			}
			// 测试使用
			System.out.println("已经生成列数：" + i);
		}
		return WORK_BOOK;
	}

	/**
	 * 创建一个格式好看的的 excel HSSFWorkbook文件 速度比较慢
	 * 
	 * @param headTitle
	 * @param sheetName
	 * @param titleList
	 * @param rowList
	 * @return
	 */
	public static HSSFWorkbook getSmartWorkbook(String headTitle, String sheetName, List<String> titleList,
			List<List<String>> rowList) {
		if (WORK_BOOK != null) {
			WORK_BOOK = null;
		}
		WORK_BOOK = createExcel();
		HSSFCellStyle headStyle = createHeadStyle(WORK_BOOK);
		HSSFCellStyle titleStyle = createTitleStyle(WORK_BOOK);
		HSSFCellStyle contentStyle = createContentStyle(WORK_BOOK);
		sheetName = "".equals(sheetName) ? DEFAULT_SHEET_NAME : sheetName;
		HSSFSheet hssfSheet = WORK_BOOK.createSheet(sheetName);
		int row = 0;// 创建的表格行号

		/** 创建表头合并单元格开始 */
		if (StringUtils.isNotBlank(headTitle)) {// 判断是否需要标题
			int length = titleList.size();
			length = length > 0 ? length - 1 : length;
			HSSFRow headRow = hssfSheet.createRow(row);// 表格头
			row++;
			HSSFCell headerCell = headRow.createCell(0);
			headerCell.setCellValue(headTitle);
			headerCell.setCellStyle(headStyle);
			hssfSheet.autoSizeColumn(0);
			// 参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, length);
			headRow.setHeight(HEAD_ROW_HEIGHT);
			hssfSheet.addMergedRegion(cellRangeAddress);
		}
		/** 创建表头合并单元格结束 */

		HSSFRow titleRow = hssfSheet.createRow(row);// 表格标题
		titleRow.setHeight(TITLE_ROW_HEIGHT);
		row++;
		// 循环生成标题行
		for (int i = 0; i < titleList.size(); i++) {
			HSSFCell cell = titleRow.createCell(i);
			cell.setCellValue(titleList.get(i));
			cell.setCellStyle(titleStyle);
			hssfSheet.autoSizeColumn(i);//特别耗时
		}
		// 循环生成具体内容行的具体列

		for (int i = 0; i < rowList.size(); i++) {
			HSSFRow hssfRow = hssfSheet.createRow(row + i);// 表格内容
			hssfRow.setHeight(CONTENT_ROW_HEIGHT);
			List<String> cellList = rowList.get(i);
			// 循环生成具体内容行的具体列
			for (int j = 0; j < cellList.size(); j++) {
				HSSFCell cell = hssfRow.createCell(j);
				cell.setCellValue(cellList.get(j));
				cell.setCellStyle(contentStyle);
				hssfSheet.autoSizeColumn(i);
			}
		}
		return WORK_BOOK;
	}

	/**
	 * 设置表头样式
	 * 
	 * @param workBook
	 * @return
	 */
	private static HSSFCellStyle createHeadStyle(HSSFWorkbook workBook) {
		HSSFCellStyle headStyle = workBook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		// 垂直居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headStyle.setBorderBottom(BorderStyle.NONE);
		HSSFFont headFont = workBook.createFont();
		headFont.setFontName(HEAD_FONT_NAME);
		headFont.setFontHeightInPoints(HEAD_FONT_SIZE);
		headFont.setBold(false);
		headStyle.setFont(headFont);
		return headStyle;
	}

	/**
	 * 设置标题样式
	 * 
	 * @param workBook
	 * @return
	 */
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook workBook) {
		HSSFCellStyle titleStyle = workBook.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		titleStyle.setBorderBottom(BorderStyle.NONE);
		HSSFFont titleFont = workBook.createFont();
		titleFont.setFontName(TITLE_FONT_NAME);
		titleFont.setFontHeightInPoints(TITLE_FONT_SIZE);
		titleFont.setBold(false);
		titleStyle.setFont(titleFont);
		return titleStyle;
	}

	/**
	 * 设置内容样式
	 * 
	 * @param workBook
	 * @return
	 */
	private static HSSFCellStyle createContentStyle(HSSFWorkbook workBook) {
		HSSFCellStyle contentStyle = workBook.createCellStyle();
		contentStyle.setAlignment(HorizontalAlignment.CENTER);
		contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentStyle.setBorderBottom(BorderStyle.NONE);
		contentStyle.setWrapText(true);
		HSSFFont contentFont = workBook.createFont();
		contentFont.setFontName(CONTENT_FONT_NAME);
		contentFont.setFontHeightInPoints(CONTENT_FONT_SIZE);
		contentStyle.setFont(contentFont);
		return contentStyle;
	}

	/**
	 * 导出Excel
	 * 
	 * @param response
	 * @param fileName
	 * @param workbook
	 */
	public static void exportExcel(HttpServletResponse response, String fileName, HSSFWorkbook workbook) {
		ServletOutputStream out = null;
		try {
			response.reset();// 清空
			out = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);// 文件名称
			workbook.write(out);
		} catch (IOException e) {
			log.error("数据出错，可能无法正常导出表格文件", e);
			log.error("可能是用户已经取消了导出表格文件操作!\n详细如下:", e);
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
					log.info("导出EXECL ServletOutputStream已经正常关闭！");
				} catch (IOException e) {
					log.error("导出EXECL ServletOutputStream已经关闭异常！\n详细如下:");
					e.printStackTrace();
				}
			}
		}
	}
}