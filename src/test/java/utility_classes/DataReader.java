package utility_classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/*
* Utility class for test email and project properties loading
* */


public class DataReader {
    private static Object propsPath;
    public DataReader(Path propsPath){
        this.propsPath = propsPath;
    }
    private ArrayList<String> getEmails(String propertyName) throws IOException{
      String rawText =  readProjectProps(propertyName);
      String[] array = rawText.split(",");
      return new ArrayList<String>(Arrays.asList(array));
    }
    public String getEmail() throws IOException, URISyntaxException {
        String email;
        ArrayList<String> unusedEmails = getEmails("emails");
        ArrayList<String> usedEmails = getEmails("usedEmails");

        int index = 0;
        //loop over unusedEmails if duplicate email was found in usedEmails
        do{
            email = unusedEmails.get(index);
            index++;
        }while(usedEmails.contains(email));

        return email;
    }
    public String readProjectProps(String property) throws IOException {
        Properties props = new Properties();
        FileInputStream inputStream = new FileInputStream(propsPath.toString());
        props.load(inputStream);
        return props.getProperty(property);
    }
}
