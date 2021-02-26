package cn.os.myframe.common.utils;

import com.alibaba.fastjson.JSONArray;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author oushuo
 * @date 2020/10/11
 * @description 解析Excel
 */
public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 解析excel2list<Map>
     * @param inputStream 文件输入流
     * @param headerContact <excelHead,beanAttr> excel表头与bean对象映射关系
     * @return
     */
    public static List<Map<String,Object>> parseExcel(InputStream inputStream, Map<String,String> headerContact) {
        List<Map<String,Object>> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            // 工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            // 总行数
            int rowLength = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
            // 工作表的列
            Row row = sheet.getRow(0);
            // 总列数
            int colLength = row.getLastCellNum();
            // 得到指定的单元格
            Cell cell = row.getCell(0);
            List<String> beanAttrs = new ArrayList<>();
            for (int i = 0; i < colLength; i++) {
                String beanAttr = row.getCell(i).getStringCellValue();
                if (headerContact != null && headerContact.size() != 0) {
                    beanAttr = headerContact.get(beanAttr);
                }
                // 加载表头数据
                beanAttrs.add(beanAttr);
            }
            for (int i = 1; i < rowLength; i++) {
                Map<String,Object> map = new HashMap<>();
                for (int j = 0; j < colLength ; j++) {
                    cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        map.put(beanAttrs.get(j),getCellValue(cell));
                    }
                }
                list.add(map);
            }
        } catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return list;
    }

    /**
     * 解析excel2list<Map>
     * @param inputStream 文件输入流
     * @return
     */
    public static List<Map<String,Object>> parseExcel(InputStream inputStream) {
        return parseExcel(inputStream, new HashMap<>());
    }

    /**
     * 解析excel2List<bean>
     * @param inputStream 文件输入流
     * @param headerContact <excelHead,beanHead> excel表头与bean对象映射关系
     * @param cls 类字节码
     * @return
     */
    public static <T> List<T> parseExcel(InputStream inputStream,Map<String,String> headerContact, Class<T> cls) {
        List<Map<String, Object>> maps = parseExcel(inputStream,headerContact);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(maps);
        try {
            List<T> list = jsonArray.toJavaList(cls);
            return list;
        } catch(Exception e){

        }
        return null;
    }

    /**
     * 解析excel2List<bean>
     * @param inputStream 文件输入流
     * @param cls 类字节码
     * @return
     */
    public static <T> List<T> parseExcel(InputStream inputStream, Class<T> cls) {
        return parseExcel(inputStream,null,cls);
    }

    /**
     * 获取单元格的值
     * @param cell
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;

        DecimalFormat df = new DecimalFormat("0");//格式化number String字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化

        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df.format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case BLANK:
                value = "";
                break;
            default:
                value = cell.toString();
                break;
        }
        return value;
    }
}

