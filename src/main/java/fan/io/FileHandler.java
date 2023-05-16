package fan.io;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件操作类
 *
 * @author Fan
 * @since 2023/5/15 14:23
 */
@Slf4j
public class FileHandler {

    private static List<File> getFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return Collections.emptyList();
        }

        List<File> fileList = new ArrayList<>();
        return getFiles(file, fileList);
    }

    private static List<File> getFiles(File file, List<File> fileList) {
        File[] files = file.listFiles();
        assert files != null;

        for (File fl : files) {
            if (fl.isDirectory()) {
                getFiles(fl, fileList);
            } else {
                fileList.add(fl);
            }
        }

        return fileList;
    }

    private static List<Path> walkFiles(String path) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            return walk.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) throws IOException {
        List<File> files = getFiles("C:\\Users\\fan\\Pictures\\测试");
        List<String> images = new ArrayList<>();
        String str = "![](http://img.fan223.cn/2023/05/20230511145952.png)";

        int preIndex = 0;
        int sufIndex;
        while ((preIndex = str.indexOf("!", preIndex) + 1) > 0) {
            sufIndex = str.indexOf(")", preIndex);
            String[] paths = str.substring(preIndex, sufIndex).split("/");
            images.add(paths[paths.length - 1]);
        }

        for (File file : files) {
            if (!images.contains(file.getName())) {
                Files.delete(file.toPath());
            }
        }
    }
}
