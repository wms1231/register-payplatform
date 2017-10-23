package com.bsoft.tools;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 导出Excel 公用类
 * 
 * @author wms1231
 *
 */
public class ExpExcelUtil {
	protected static final Logger log = LoggerFactory.getLogger(ExpExcelUtil.class);
	private static HSSFWorkbook WORK_BOOK = null;// 工作表 设置为全局加样式时用到
	private static short HEAD_FONT_POINT = 16;// 表格头字体大小
	private static short TITLE_FONT_POINT = 14;// 表格标题字体大小
	private static short CONTENT_FONT_POINT = 12;// 表格内容字体大小
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
	 * 创建一个excel HSSFWorkbook文件
	 * 
	 * @param headStr
	 *            表格头
	 * @param sheetStr
	 *            标题
	 * @param titleList
	 *            标题集合
	 * @param rowList
	 *            内容集合
	 * 
	 * @return
	 */
	public static HSSFWorkbook getWorkbook(String headTitle, String sheetName, List<String> titleList,
			List<List<String>> rowList) {
		if (WORK_BOOK != null) {
			WORK_BOOK = null;
		}
		WORK_BOOK = createExcel();
		HSSFCellStyle headStyle = setHeadStyle(WORK_BOOK);
		HSSFCellStyle titleStyle = setTitleStyle(WORK_BOOK);
		HSSFCellStyle contentStyle = setContentStyle(WORK_BOOK);
		sheetName = "".equals(sheetName) ? DEFAULT_SHEET_NAME : sheetName;
		HSSFSheet hssfSheet = WORK_BOOK.createSheet(sheetName);
		int row = 0;// 创建的表格行号
		/** 创建表头合并单元格开始 */
		if (StringUtils.hasText(headTitle)) {// 判断是否需要标题
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
			hssfSheet.autoSizeColumn(i);
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
			// 测试使用
			System.out.println("已经生成列数：" + i);
		}
		return WORK_BOOK;
	}

	/**
	 * 设置标题样式
	 * 
	 * @param workBook
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static HSSFCellStyle setHeadStyle(HSSFWorkbook workBook) {
		/** 表头样式开始 */
		HSSFCellStyle headStyle = workBook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		HSSFFont headFont = workBook.createFont();
		headFont.setFontName(HEAD_FONT_NAME);
		headFont.setFontHeightInPoints(HEAD_FONT_POINT);// 设置字体大小
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		headStyle.setFont(headFont);// 选择需要用到的字体格式
		/** 表头样式结束 */
		return headStyle;
	}

	/**
	 * 设置标题样式
	 * 
	 * @param workBook
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static HSSFCellStyle setTitleStyle(HSSFWorkbook workBook) {
		/** 标题样式开始 */
		HSSFCellStyle titleStyle = workBook.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		HSSFFont titleFont = WORK_BOOK.createFont();
		titleFont.setFontName(TITLE_FONT_NAME);
		titleFont.setFontHeightInPoints(TITLE_FONT_POINT);// 设置字体大小
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		titleStyle.setFont(titleFont);// 选择需要用到的字体格式
		/** 标题样式结束 */
		return titleStyle;
	}

	/**
	 * 设置标题样式
	 * 
	 * @param workBook
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static HSSFCellStyle setContentStyle(HSSFWorkbook workBook) {
		/** 内容样式开始 */
		HSSFCellStyle contentStyle = workBook.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setWrapText(true);
		HSSFFont contentFont = WORK_BOOK.createFont();
		contentFont.setFontName(CONTENT_FONT_NAME);
		contentFont.setFontHeightInPoints(CONTENT_FONT_POINT);// 设置字体大小
		contentStyle.setFont(contentFont);// 选择需要用到的字体格式
		/** 内容样式结束 */
		return contentStyle;
	}

	/**
	 * 导出Execl
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