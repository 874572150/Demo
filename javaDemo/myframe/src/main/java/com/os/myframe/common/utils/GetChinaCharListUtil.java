package com.os.myframe.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.*;

/**
 * @author oushuo
 * @date 2020/10/9
 * @description 从文件中读取中文字符
 */
public class GetChinaCharListUtil {
    public static void main(String[] args) {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("//", null);
        filterMap.put("<style", "</style>");
        filterMap.put("<!--", "-->");
        Set<String> chinaWord = getFileChinaWord("F:\\Demo\\TestDire\\FileTest", filterMap);
        for (String s : chinaWord) {
            System.out.println(s);
        }
    }

    /**
     * 获取指定文件（文件下）下的所有中文字符
     *
     * @param filePath 文件路径
     * @param suffix   文件后缀名
     * @return
     * @Param filter Map<行首开始字符，行尾结束字符>
     */
    public static Set<String> getFileChinaWord(String filePath, String suffix, Map<String, String> filter) {
        //utf-8编码：中文字符占3个字节
        //gbk编码：中文字符占2个字节
        Set<String> returnChinaWord = new HashSet<>();
        // 是否需要过滤
        boolean isFilter = filter == null ? false : true;
        // 获取文件夹下的所有指定后缀文件
        List<File> files = new ArrayList<>();
        BufferedReader br = null;
        pushFile(files, new File(filePath), suffix);
        for (File file : files) {
            try {
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                String beginStr = "";
                boolean beginFlag = false;
                String filterKey = "";
                boolean filterFlag = false;

                String currStr = null;
                while ((currStr = br.readLine()) != null) {
                    currStr = currStr.trim();

                    int filterBeginIndex = 0;
                    int filterEndIndex = 0;
                    if (isFilter && !filterFlag) {
                        for (String key : filter.keySet()) {
                            if (currStr.indexOf(key) != -1) {
                                filterBeginIndex = currStr.indexOf(key);
                                filterFlag = true;
                                filterKey = key;
                                break;
                            }
                        }
                    }
                    if (isFilter && filterFlag && filterKey.length() > 0) {
                        if (filter.get(filterKey) == null) {
                            filterFlag = false;
                            filterKey = "";
                            filterBeginIndex = 0;
                            continue;
                        }
                        if (currStr.indexOf(filter.get(filterKey)) != -1) {
                            filterEndIndex = currStr.indexOf(filter.get(filterKey));
                            filterFlag = false;
                            if (filterEndIndex + filter.get(filterKey).length() == currStr.length()) {
                                filterKey = "";
                                continue;
                            }
                            filterKey = "";
                        }
                    }
                    if (!filterFlag) {
                        char[] chars = currStr.toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            if (i < filterBeginIndex || i > filterEndIndex) {
                                String tempStr = chars[i] + "";
                                int currCharLength = tempStr.getBytes("utf-8").length;
                                if (currCharLength == 3) {
                                    beginFlag = true;
                                } else {
                                    beginFlag = false;
                                }
                                if (beginFlag) {
                                    beginStr += tempStr;
                                } else if (beginStr.length() > 0) {
//                                    System.out.println(beginStr);
                                    returnChinaWord.add(beginStr);
                                    beginStr = "";
                                }
                            }else{
                                beginFlag = false;
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("file not find");
            } catch (IOException e) {
                System.out.println("file read failure");
            }
            System.out.println(file.getName()+returnChinaWord.size());

            System.out.println("--------------------------------------------------------");
        }
        return returnChinaWord;
    }

    /**
     * 获取指定文件（文件下）下的所有中文字符
     *
     * @param filePath 文件路径
     * @return
     */
    public static  Set<String> getFileChinaWord(String filePath) {
        return getFileChinaWord(filePath, null, null);
    }

    /**
     * 获取指定文件（文件下）下的所有中文字符
     *
     * @param filePath 文件路径
     * @param filter   Map<行首开始字符，行尾结束字符>
     * @return
     */
    public static  Set<String> getFileChinaWord(String filePath, Map<String, String> filter) {
        return getFileChinaWord(filePath, null, filter);
    }

    /**
     * 将该文件夹下的指定文件存储到returnFile中
     *
     * @param returnFile
     * @param file
     * @param suffix
     */
    private static void pushFile(List<File> returnFile, File file, String suffix) {
        if (file.isDirectory()) {
            for (File sFile : file.listFiles()) {
                pushFile(returnFile, sFile, suffix);
            }
        } else {
            if (StringUtils.isBlank(suffix)) {
                returnFile.add(file);
            } else if (file.getName().endsWith(suffix)) {
                returnFile.add(file);
            }
        }
    }

}
