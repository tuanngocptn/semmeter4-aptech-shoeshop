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
    public static final String DB_URL = "jdbc:sqlserver://PANDAPO\\SQLEXPRESS:1433;DatabaseName=SHOE_SHOP";
    public static final String CONNECTION_STRING = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DB_USER = "sa";
    public static final String DB_PASS = "123";
    
    public static final String RESULT_EMPTY_OBJECT = "{}";
    public static final String RESULT_EMPTY_ARRAY = "[]";
    
    public static final String CONTENT_TYPE_HEADER_BYTE_CODE = "text/html;charset=UTF-8";
    public static final String CONTENT_TYPE_HEADER_JSON_RETURN_TYPE= "application/json";
    
    public static final String LOGIN_URL = "/login.html";
    public static final String REGISTER_URL = "/register.html";
    public static final String PRODUCT_URL = "/index.html";
    
    public static final String USER_PARAM = "user";
    public static final String PASS_PARAM = "pass";
    public static final String PRAM_STRING = "params";
    
    public static final String ROLE_ADMIN = "adm";
    public static final String ROLE_CUSTOMER = "ctm";
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_DELETE= "delete";
    public static final String FORMAT_ORDER_CODE = "ord";
    public static final String FORMAT_CATEGORY_CODE = "cate";
    public static final String FORMAT_BRAND_CODE = "bra";
    public static final String FORMAT_PRODUCT_IMAGE = "ptr";
    public static final String FORMAT_PRODUCT = "prd";
    
    public static final String ACCOUNT_TABLE = "tbl_account";
    public static final String ACCOUNT_COLUMN_ORD = "_ord";
    public static final String ACCOUNT_COLUMN_CODE = "_code";
    public static final String ACCOUNT_COLUMN_ROLE_CODE = "_role_code";
    public static final String ACCOUNT_COLUMN_USER = "_user";
    public static final String ACCOUNT_COLUMN_PASS = "_pass";
    public static final String ACCOUNT_COLUMN_NAME = "_name";
    public static final String ACCOUNT_COLUMN_ADDRESS = "_address";
    public static final String ACCOUNT_COLUMN_PHONE = "_phone";
    public static final String ACCOUNT_COLUMN_EMAIL = "_email";
    public static final String ACCOUNT_COLUMN_STATUS = "_status";
    public static final String ACCOUNT_COLUMN_PASS_LEVEL_2 = "_passLv2";
    
    public static final String PRODUCT_TABLE = "tbl_product";
    public static final String PRODUCT_COLUMN_ORD = "_ord";
    public static final String PRODUCT_COLUMN_CODE = "_code";
    public static final String PRODUCT_COLUMN_NAME = "_name";
    public static final String PRODUCT_COLUMN_BRAND_CODE = "_brand_code";
    public static final String PRODUCT_COLUMN_CATEGORY_CODE = "_category_code";
    public static final String PRODUCT_COLUMN_DESCRIPTION = "_description";
    public static final String PRODUCT_COLUMN_PRICE = "_price";
    public static final String PRODUCT_COLUMN_IS_HOT = "_is_hot";
    public static final String PRODUCT_COLUMN_QUANTITY = "_quantity";
    public static final String PRODUCT_COLUMN_STATUS = "_status";
    
    public static final String IMAGE_TABLE = "tbl_product_image";
    public static final String IMAGE_ORD = "_ord";
    public static final String IMAGE_CODE = "_code";
    public static final String IMAGE_PRODUCT_CODE = "_product_code";
    public static final String IMAGE_STATUS = "_status";
    public static final String IMAGE_IMAGE = "_image";
    
    public static final String RATE_TABLE = "tbl_rate";
    public static final String RATE_COLUMN_ORD = "_ord";
    public static final String RATE_COLUMN_PRODUCT_CODE = "_product_code";
    public static final String RATE_COLUMN_ACCOUNT_CODE = "_account_code";
    public static final String RATE_COLUMN_RATE = "_rate";
    
    public static final String ORDER_TABLE = "tbl_order";
    public static final String ORDER_COLUMN_ORD = "_ord";
    public static final String ORDER_COLUMN_CODE = "_code";
    public static final String ORDER_COLUMN_ACCOUNT_CODE = "_account_code";
    public static final String ORDER_COLUMN_DATE = "_date";
    public static final String ORDER_COLUMN_NAME = "_name";
    public static final String ORDER_COLUMN_EMAIL = "_email";
    public static final String ORDER_COLUMN_PHONE = "_phone";
    public static final String ORDER_COLUMN_SHIP_ADDRESS = "_ship_address";
    public static final String ORDER_COLUMN_SHIP_DATE = "_ship_date";
    public static final String ORDER_COLUMN_STATUS = "_status";
    
    public static final String ORDER_DETAIL_TABLE = "tbl_order_detail";
    public static final String ORDER_DETAIL_COLUMN_ORD = "_ord";
    public static final String ORDER_DETAIL_COLUMN_ORDER_CODE = "_order_code";
    public static final String ORDER_DETAIL_COLUMN_PRODUCT_CODE = "_product_code";
    public static final String ORDER_DETAIL_COLUMN_QUANTITY = "_quantity";
    
    public static final String CATEGORY_TABLE = "tbl_category";
    public static final String CATEGORY_COLUMN_ORD = "_ord";
    public static final String CATEGORY_COLUMN_CODE = "_code";
    public static final String CATEGORY_COLUMN_NAME = "_name";
    public static final String CATEGORY_COLUMN_STATUS = "_status";
    public static final String CATEGORY_COLUMN_LOGO = "_logo";
    
    public static final String BRAND_TABLE = "tbl_brand";
    public static final String BRAND_COLUMN_ORD = "_ord";
    public static final String BRAND_COLUMN_CODE = "_code";
    public static final String BRAND_COLUMN_NAME = "_name";
    public static final String BRAND_COLUMN_STATUS = "_status";
    public static final String BRAND_COLUMN_LOGO = "_logo";
}
