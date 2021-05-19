package cn.os.test.controller;

import cn.os.test.model.User;
import cn.os.test.common.utils.ExcelUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/test")
    public Object test(@RequestParam("type") String type) {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> list = new ArrayList<>();
        jsonObject.put("name","zs");
        jsonObject.put("age",123);
        list.add(jsonObject);
        jsonObject.put("name","ls");
        jsonObject.put("age",22);
        list.add(jsonObject);
        switch (type) {
            case "string" : return "string" ;
            case "int" : return 123 ;
            case "array" : return list;
            case "json" : return jsonObject;
        }
        return null;
    }
    @PostMapping("/exportExcel")
    public Object pubggupload(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName=file.getOriginalFilename();
        if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            List<User> users = ExcelUtils.parseExcel(file.getInputStream(), User.class);
            return users;
        }
        return null;
    }

}
