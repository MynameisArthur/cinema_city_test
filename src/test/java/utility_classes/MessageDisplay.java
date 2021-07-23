package utility_classes;

/*
* Utility class with static method for displaying success messages used in CinemaCityAsserts class
* */

public class MessageDisplay {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void successMessage(String msg){
        System.out.println(ANSI_GREEN + " " + msg + " - assertion passed." +ANSI_RESET);
    }

}
