package com.os.myframe.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * csv
 */
public class CsvUtil {
    /**
     * @param response
     * @param map   对应的列标题
     * @param exportData    需要导出的数据
     * @param fileds    列标题对应的实体类的属性
     * @throws IOException
     */
//    @SuppressWarnings({ "resource", "rawtypes", "unchecked" })
    public static void exportFile(HttpServletResponse response,
                                  HashMap map, List exportData, String fileds[])
            throws IOException {
        try {
            // 写入临时文件
            File tempFile = File.createTempFile("tempFile", ".csv");
            BufferedWriter csvFileOutputStream = null;
            // UTF-8使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "GBK"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null ? new String(((String) propertyEntry.getValue()).getBytes("GBK"), "GBK") : "");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.write("\r\n");
            // 写入文件内容,
            // ============ //：Arraylist<实体类>填充实体类的基本信息==================
            for (int j = 0; exportData != null && !exportData.isEmpty()
                    && j < exportData.size(); j++) {
                // RealTimeSO2 t = (BankWageMonth) exportData.get(j);
                Class clazz = exportData.get(j).getClass();
                String[] contents = new String[fileds.length];
                for (int i = 0; fileds != null && i < fileds.length; i++) {
                    String filedName = toUpperCaseFirstOne(fileds[i]);
                    Object obj = null;
                    try {
                        Method method = clazz.getMethod(filedName);
                        method.setAccessible(true);
                        obj = method.invoke(exportData.get(j));
                    } catch (Exception e) {

                    }
                    String str = String.valueOf(obj);
                    if (str == null || str.equals("null"))
                        str = "";
                    contents[i] = str;
                }

                for (int n = 0; n < contents.length; n++) {
                    // 将生成的单元格添加到工作表中
                    csvFileOutputStream.write(contents[n]);
                    csvFileOutputStream.write(",");
                }
                csvFileOutputStream.write("\r\n");
            }

            csvFileOutputStream.flush();

            /**
             * 写入csv结束，写出流
             */
            java.io.OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            java.io.File fileLoad = new java.io.File(tempFile.getCanonicalPath());
            response.reset();
            response.setContentType("application/csv");
            String trueCSVName="导出数据.csv";
            response.setHeader("Content-Disposition", "attachment;  filename="+ new String(trueCSVName.getBytes("GBK"), "ISO8859-1"));
            long fileLength = fileLoad.length();
            String length1 = String.valueOf(fileLength);
            response.setHeader("Content_Length", length1);
            java.io.FileInputStream in = new java.io.FileInputStream(fileLoad);
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n); // 每次写入out1024字节
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将第一个字母转换为大写字母并和get拼合成方法
     *
     * @param origin
     * @return
     */
    private static String toUpperCaseFirstOne(String origin) {
        StringBuffer sb = new StringBuffer(origin);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "get");
        return sb.toString();
    }
}
