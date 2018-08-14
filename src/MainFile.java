import java.io.File;

public class MainFile {

    public static void main(String[] args) {

        File dir = new File("./src/ru/");
        getFileNames(dir);
    }

    private static void getFileNames(File directory) {

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("Dir: " + file.getName());
                    getFileNames(file);
                }
                else System.out.println("     File:" + file.getName());
            }
        }
    }
}

