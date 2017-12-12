/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

/**
 *
 * @author Panda
 */
public class Constants {
    public static String DB_URL = "jdbc:sqlserver://PANDAPO\\SQLEXPRESS:1433;DatabaseName=SHOE_SHOP";
    public static String CONNECTION_STRING = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String DB_USER = "sa";
    public static String DB_PASS = "123";
    
    public static String RESULT_EMPTY_OBJECT = "{}";
    public static String RESULT_EMPTY_ARRAY = "[]";
    
    public static String CONTENT_TYPE_HEADER_BYTE_CODE = "text/html;charset=UTF-8";
    public static String CONTENT_TYPE_HEADER_JSON_RETURN_TYPE= "application/json";
    
    public static String LOGIN_URL = "/login.html";
    public static String REGISTER_URL = "/register.html";
    
    public static String USER_PARAM = "user";
    public static String PASS_PARAM = "pass";
    
    public static String ACCOUNT_TABLE = "tbl_account";
    public static String ACCOUNT_COLUMN_ORD = "_ord";
    public static String ACCOUNT_COLUMN_CODE = "_code";
    public static String ACCOUNT_COLUMN_ROLE_CODE = "_role_code";
    public static String ACCOUNT_COLUMN_USER = "_user";
    public static String ACCOUNT_COLUMN_PASS = "_pass";
    public static String ACCOUNT_COLUMN_NAME = "_name";
    public static String ACCOUNT_COLUMN_ADDRESS = "_address";
    public static String ACCOUNT_COLUMN_PHONE = "_phone";
    public static String ACCOUNT_COLUMN_EMAIL = "_email";
    public static String ACCOUNT_COLUMN_STATUS = "_status";
}
