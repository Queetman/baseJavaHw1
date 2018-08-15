import java.io.File;

public class MainFile {

    public static void main(String[] args) {

        File dir = new File("./src/ru/");
        getFileNames(dir);
    }

    private static void getFileNames(File directory) {

        String offset = "   ";

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(offset + "Dir: " + file.getName());
                    getFileNames(file);
                }
            }
        }
    }
}

