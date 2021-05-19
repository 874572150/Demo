package cn.os.test.model;

import lombok.Data;

import java.util.List;

@Data
public class SheetData {

    /**
     * 工作本中的工作表的名称
     */
    private String sheetName;

    /**
     * 头部标题
     */
    private Object[] headers;

    /**
     * 表数据列表
     */
    private List<Object[]> datas;

    /**
     * 起始行位置，从0开始计数
     */
    private int startRow;

    /**
     * 起始列位置，从0开始计数
     */
    private int startColumn;

    public SheetData(String sheetName, Object[] headers, List<Object[]> datas, int startRow, int startColumn) {
        this.sheetName = sheetName;
        this.headers = headers;
        this.datas = datas;
        this.startRow = startRow;
        this.startColumn = startColumn;
    }

    public SheetData(String sheetName, List<Object[]> datas, int startRow, int startColumn) {
        this.sheetName = sheetName;
        this.headers = null;
        this.datas = datas;
        this.startRow = startRow;
        this.startColumn = startColumn;
    }

}
