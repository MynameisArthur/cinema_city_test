import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmailLoader {
    private static URL ResourceUtils;
    private Scanner in;
    public ArrayList emailList;
    private String path;
    EmailLoader(String path) {
        this.path = path;
    }
//    public String getAllEmails() throws IOException {
//
//    }
    public String getText() throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
