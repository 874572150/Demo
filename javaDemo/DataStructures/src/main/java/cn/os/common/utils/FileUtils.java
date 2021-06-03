package cn.os.common.utils;

import java.io.File;
import java.io.IOException;

/**
 * @author ou shuo
 * @date 2021/6/2 10:07
 * @description 文件工具类
 */
public class FileUtils {
    private static final String projectPath;

    private static final String resourcePath;

    static {
        File file = new File("");
        projectPath = file.getAbsolutePath();
        resourcePath = projectPath + "/src/main/resources/";
    }

    /**
     * 获取resources下的文件
     *
     * @param filePath 文件相对路径
     * @return File
     */
    public static File getResourcesFile(String filePath) {
        return getResourcesFile(filePath, false);
    }

    /**
     * 获取resources下的文件
     *
     * @param filePath 文件相对路径
     * @return File
     */
    public static File getResourcesFile(String filePath, boolean isCreate) {
        if (null == filePath || "".equals(filePath)) {
            throw new RuntimeException("文件名不能为空");
        }
        File file = new File(resourcePath + filePath);
        if (!file.exists() && isCreate) {
            try {
                boolean newFile = file.createNewFile();
                if (!newFile) {
                    throw new RuntimeException("文件创建失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new File(resourcePath + filePath);
    }


}
