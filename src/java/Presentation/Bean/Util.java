/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

public class Util {

    private static final String ALERT_SUCCESS = "success";
    private static final String ALERT_DANGER = "danger";

    private static String buildAlert(String type, String title, String message) {
        return "<div class=\"alert alert-" + type + " alert-dismissible\" role=\"alert\">"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"
                + "  <strong>" + title + "</strong> " + message + "</div>";
    }

    public static String buildSuccess(String title, String message) {
        return buildAlert(ALERT_SUCCESS, title, message);
    }
}
