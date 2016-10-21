/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
//@ViewScoped
//@SessionScoped
@ApplicationScoped
public class Util implements Serializable {

    private static final String ALERT_SUCCESS = "success";
    private static final String ALERT_DANGER = "danger";

    private static String buildAlert(String type, String title, String message) {
        final String TAG  = "<div class=\"alert alert-%s alert-dismissible\" role=\"alert\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button><strong>%s</strong>  %s</div>";
        return String.format(TAG, type, title, message);
    }

    public static String buildSuccess(String title, String message) {
        return buildAlert(ALERT_SUCCESS, title, message);
    }
    
    public static String buildAlert( boolean state, String title, String message ){
        if(state)
            return buildSuccess(title, message);
        return buildDanger(title, message);
    }
    
    public static String buildDanger(String title, String message) {
        return buildAlert(ALERT_DANGER, title, message);
    }
    
    public static boolean onlyNumbers(String word){
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(word).matches();
    }
    
    public static boolean onlyFloatNumbers(String word){
        Pattern pattern = Pattern.compile("[0-9.]+");
        return pattern.matcher(word).matches();
    }
    
    public static boolean onlyLetters(String word){
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        return pattern.matcher(word).matches();
    }
    
    public static boolean onlyLettersNumbers(String word){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        return pattern.matcher(word).matches();
    }
}
