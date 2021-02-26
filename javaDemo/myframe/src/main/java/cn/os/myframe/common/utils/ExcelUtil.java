package cn.os.myframe.common.utils;

import cn.os.myframe.model.SheetData;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ExcelUtil {

    /**
     * 生成excel文件流
     *
     * @param outputStream  输出流
     * @param sheetDataList 需要填充的数据
     * @return excel文件流
     */
    public static void createExcel(OutputStream outputStream, SheetData... sheetDataList) {
        Workbook workbook = createWorkbook(false);
        createExcel(outputStream, workbook, sheetDataList);
    }

    /**
     * 创建csv文件
     *
     * @param head         表头数据
     * @param dataList     内容数据
     * @param outputStream 输出流
     */
    public static void createCSV(Object[] head, List<Object[]> dataList, OutputStream outputStream, String unicode) {
        BufferedWriter csvWriter = null;
        try {
            csvWriter = new BufferedWriter(new OutputStreamWriter(outputStream, unicode), 1024);
            if (head != null)
                writeRow(head, csvWriter);
            for (Object[] row : dataList) {
                writeRow(row, csvWriter);
            }
            csvWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成excel文件流
     *
     * @param outputStream  输出流
     * @param workbook      工作本
     * @param sheetDataList 需要填充的数据
     */
    public static void createExcel(OutputStream outputStream, Workbook workbook, SheetData... sheetDataList) {
        try {
            if (workbook == null || sheetDataList.length <= 0)
                return;
            for (SheetData sheetData : sheetDataList) {
                Sheet sheet = createSheet(workbook, sheetData.getSheetName());
                if (sheet == null || sheetData.getDatas() == null)
                    continue;
                writeSheet(sheet, sheetData.getHeaders(), sheetData.getDatas(), sheetData.getStartRow(), sheetData.getStartColumn());
            }
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 向一个工作表内写入数据
     *
     * @param sheet       工作表
     * @param headers     表头
     * @param rows        表数据
     * @param startRow    起始行位置 从0开始
     * @param startColumn 起始列位置 从0开始
     * @return 是否写入成功
     */
    public static boolean writeSheet(Sheet sheet, Object[] headers, List<Object[]> rows, int startRow, int startColumn) {
        if (sheet == null || rows == null)
            return false;
        int rowNumberHave = sheet.getPhysicalNumberOfRows() - 1;

        if (headers != null) {
            Row rowHeader = rowNumberHave >= startRow ? sheet.getRow(startRow) : sheet.createRow(startRow);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = rowNumberHave >= startRow ? rowHeader.getCell(startColumn + i) : rowHeader.createCell(startColumn + i);
                setCellValue(cell, headers[i]);
            }
            startRow++;
        }

        for (Object[] row : rows) {
            Row dataRow = rowNumberHave >= startRow ? sheet.getRow(startRow) : sheet.createRow(startRow);
            for (int i = 0; i < row.length; i++) {
                Cell cell = rowNumberHave >= startRow ? dataRow.getCell(startColumn + i) : dataRow.createCell(startColumn + i);
                setCellValue(cell, row[i]);
            }
            startRow++;
        }

        return true;
    }

    /**
     * 向一个单元格内写入数据，等于号开头字符串自动已公式方式写入
     *
     * @param cell  单元格
     * @param value 值
     */
    private static void setCellValue(Cell cell, Object value) {
        if (cell == null)
            return;
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else if (value instanceof Date) {
            CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
            short df = cell.getSheet().getWorkbook().createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss");
            style.setDataFormat(df);
            cell.setCellValue((Date) value);
            cell.setCellStyle(style);
        } else if (value instanceof RichTextString) {
            cell.setCellValue((RichTextString) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar) value);
        } else {
            if (value.toString().startsWith("=")) {
                try {
                    cell.setCellFormula(value.toString().substring(1));
                } catch (Exception e) {
                    cell.setCellValue(value.toString());
                }
            } else {
                cell.setCellValue(value.toString());
            }
        }
    }

    /**
     * 创建一个工作表
     *
     * @param workbook  excel工作文件
     * @param sheetName 工作表名称
     * @return 返回新建的工作表或与名称相同的现有工作表
     */
    public static Sheet createSheet(Workbook workbook, String sheetName) {
        try {
            if (StringUtils.isBlank(sheetName))
                return workbook.createSheet();
            else if (workbook.getSheet(sheetName) != null)
                return workbook.getSheet(sheetName);
            else
                return workbook.createSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建新的excel工作本
     *
     * @param oldExcel 是否创建office2003版本 true：是2003版本；false：2007版本；
     * @return 新的工作本
     */
    public static Workbook createWorkbook(boolean oldExcel) {
        if (oldExcel)
            return new HSSFWorkbook();
        return new XSSFWorkbook();
    }

    /**
     * csv写入一行数据
     *
     * @param row       行数据
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(Object[] row, BufferedWriter csvWriter) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (Object data : row) {
            sb.append(data == null ? "" : data).append(",");
        }
        String rowStr = sb.toString();
        rowStr = rowStr.substring(0, rowStr.length() - 1);
        csvWriter.write(rowStr);
        csvWriter.newLine();
    }
}
