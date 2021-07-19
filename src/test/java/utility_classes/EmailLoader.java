package utility_classes;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailLoader {
    private static URL ResourceUtils;
    private Scanner in;
    private String path;
    public EmailLoader(String path) {
        this.path = path;
    }
    private String[] getAllEmails() throws IOException {
      String rawText =  new String(Files.readAllBytes(Paths.get(path)));
      return rawText.split("\r\n");
    }
    public String getRandomEmail() throws IOException {
        String[] emailList = getAllEmails();
        return emailList[(int)Math.floor(Math.random() * emailList.length)+1];
    }
}
