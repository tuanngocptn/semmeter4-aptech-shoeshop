/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Panda
 */
public class Methods {

    public static void setHeaderNomal(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(Constants.CONTENT_TYPE_HEADER_BYTE_CODE);
    }
    
    public static void setHeaderAjax(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(Constants.CONTENT_TYPE_HEADER_JSON_RETURN_TYPE);
    }

    public static String strCheckLogin(String user, String pass) {
        String query = "SELECT * FROM "+ Constants.ACCOUNT_TABLE +" WHERE "
                + Constants.ACCOUNT_COLUMN_USER +" = '" + user + "' AND "
                + Constants.ACCOUNT_COLUMN_PASS +" = '" + pass + "'";
        return query;
    }
    
    
    
    //Convert json and Object
    public static String toJson(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
    //Convert json and Object
    public static <T> T fromJson(String json, Class<T> classT){
        Gson gson = new Gson();
        return gson.fromJson(json, classT);
    }
}
