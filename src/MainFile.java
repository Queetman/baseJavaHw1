import ru.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainFile {

    static ArrayList<String> fileNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        File dir = new File("./src/ru/");
        getFileNames(dir);
        System.out.println(fileNames);
    }

    private static void getFileNames(File directory) {

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory())
                    getFileNames(file);
            }
            for (File file : files) {

                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
    }
}
