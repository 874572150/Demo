package cn.os.test.controller;//package com.os.myframe.controller;
//
//import CsvUtil;
//import Menu;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//
///**
// * 导出
// */
//@RestController
//@RequestMapping("/export")
//public class ExportController {
//    @Autowired
//    private HttpServletResponse response;
//
//    @GetMapping("/csv")
//    public void exportCsv() {
//        List<Menu> reportData = new ArrayList<>();
//        Menu menu = new Menu();
//        menu.setId(1l);
//        menu.setName("test1");
//        reportData.add(menu);
//        menu.setId(2l);
//        menu.setName("test2");
//        reportData.add(menu);
//
//        HashMap map = new LinkedHashMap();
//        map.put("1", "第一列");
//        map.put("2", "第二列");
//        map.put("3", "第三列");
//        map.put("4", "第四列");
//        String fileds[] = new String[] { "id", "name" , "xhLx","recDate","so2" };
//        try {
//            CsvUtil.exportFile(response, map, reportData, fileds);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
