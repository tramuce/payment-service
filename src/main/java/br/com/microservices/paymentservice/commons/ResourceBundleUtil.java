package br.com.microservices.paymentservice.commons;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("Messages", new Locale("pt"));
    }

    public static String getString(String key) {
        return getResourceBundle().getString(key);
    }
}
