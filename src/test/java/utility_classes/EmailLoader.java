package utility_classes;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static java.nio.file.Files.*;

/*
* Utility class for email loading from testing data file
* */


public class EmailLoader {
    private String path;
    public EmailLoader(String path) {
        this.path = path;
    }
    private ArrayList<String> getAllEmails() throws IOException {
      String rawText =  new String(readAllBytes(Paths.get(path)));
      String[] array = rawText.split("\r\n");
      return new ArrayList<String>(Arrays.asList(array));
    }
    public String getEmail() throws IOException {
        String email;
        ArrayList<String> emailList = getAllEmails();
        email = emailList.get((int) Math.floor(Math.random() * emailList.size()));
        return email;
    }
}
