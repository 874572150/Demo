package cn.os.myframe.controller;

import cn.os.myframe.model.SheetData;
import cn.os.myframe.common.utils.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yyr
 * @date 2020/10/26
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelDemoController {

    @GetMapping("/excel_demo")
    public void downloadList(HttpServletResponse response) {
        String sheetName = "demo";

        List<Object[]> datas = new ArrayList<>();
        List dbQueryData = new ArrayList();//数据库查询得到的数据
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("demo.xlsx", "UTF-8"));
            OutputStream outputStream = response.getOutputStream();
            Object[] headers = {"编号", "年月", "目录"};
            for (int i = 0; i < dbQueryData.size(); i++) {
                Object[] data = new Object[headers.length];
                datas.add(data);
            }
            SheetData sheetData = new SheetData(sheetName, headers, datas, 0, 0);
            ExcelUtil.createExcel(outputStream, sheetData);
        } catch (Exception e) {
//            throw new AppException("文件导出错误", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
