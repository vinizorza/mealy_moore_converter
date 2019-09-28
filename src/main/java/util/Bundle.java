package util;

import java.util.ResourceBundle;

public class Bundle {
    public static String get(String key){
        return ResourceBundle.getBundle("messages").getString(key);
    }
}
