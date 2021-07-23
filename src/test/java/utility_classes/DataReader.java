package utility_classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private ArrayList<String> getEmails() throws IOException{
      String rawText =  new String(Files.readAllBytes(Paths.get(propsPath.toString() + "/emails.txt")));
      String[] array = rawText.split("\r\n");
      return convertToArrayList(array);
    }
    public String getEmail() throws IOException, URISyntaxException {
        String email;
        // test data
        ArrayList<String> unusedEmails = getEmails();
        // email list already used in tests
        ArrayList<String> usedEmails = convertToArrayList(
                readProjectProps("usedEmails","CinemaCityProps.properties").split(",")
        );
        int index = 0;
        //loop over unusedEmails if duplicate email was found in usedEmails
        do{
            email = unusedEmails.get(index);
            index++;
        }while(usedEmails.contains(email));
        updateProps(email.trim(),usedEmails);
        return email;
    }
    public String readProjectProps(String property, String fileName) throws IOException {
        Properties props = new Properties();
        FileInputStream inputStream = new FileInputStream(propsPath.toString() +"/"+ fileName);
        props.load(inputStream);
        inputStream.close();
        return props.getProperty(property);
    }
    // method for converting regular String array to ArrayList of strings
    private ArrayList<String> convertToArrayList(String[] arr){
        return new ArrayList<String>(Arrays.asList(arr));
    }
    // method which updates CinemaCityProps document
    private void updateProps(String email, ArrayList<String> usedEmails) throws IOException {
        Properties props = new Properties();
        String countryInstance = readProjectProps("country","CinemaCityProps.properties");
        FileOutputStream outputStream = new FileOutputStream(propsPath.toString() +"/CinemaCityProps.properties");
        usedEmails.add(email);
        String userEmailsStr = usedEmails.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ","");
        props.setProperty("country", countryInstance);
        props.setProperty("usedEmails", userEmailsStr);
        props.store(outputStream,null);
        outputStream.close();
    }
}
