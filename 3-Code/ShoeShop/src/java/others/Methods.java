/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import com.google.gson.Gson;
import entities.Account;
import entities.Order;
import entities.OrderDetail;
import entities.Product;
import entities.Rate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String query = "SELECT * FROM " + Constants.ACCOUNT_TABLE + " WHERE "
                + Constants.ACCOUNT_COLUMN_USER + " = '" + user + "' AND "
                + Constants.ACCOUNT_COLUMN_PASS + " = '" + pass + "'";
        return query;
    }

    //get string from ajax
    public static String getAjaxRequest(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        String line;
        while ((line = br.readLine()) != null) {
            json += line;
        }
        return json;
    }

    //Convert json and Object
    public static String toJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    //Convert json and Object
    public static <T> T fromJson(String json, Class<T> classT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classT);
    }

    public static String strInsertAccount(Account account) {
        String query = "INSERT INTO " + Constants.ACCOUNT_TABLE + "("
                + Constants.ACCOUNT_COLUMN_CODE + ", " + Constants.ACCOUNT_COLUMN_ROLE_CODE + ", "
                + Constants.ACCOUNT_COLUMN_USER + ", " + Constants.ACCOUNT_COLUMN_PASS + ", "
                + Constants.ACCOUNT_COLUMN_ADDRESS + ", " + Constants.ACCOUNT_COLUMN_PHONE + ", "
                + Constants.ACCOUNT_COLUMN_EMAIL + ", " + Constants.ACCOUNT_COLUMN_STATUS + ")"
                + "VALUES ('"
                + account.getCode() + "','" + account.getRoleCode() + "','"
                + account.getUser() + "','" + account.getPass() + "','"
                + account.getAddress() + "','" + account.getPhone() + "', '"
                + account.getEmail() + "', '" + account.getStatus() + "')";
        return query;
    }

    public static String strAccountMaxOrd() {
        String query = "SELECT MAX(" + Constants.ACCOUNT_COLUMN_ORD + ") AS "
                + Constants.ACCOUNT_COLUMN_ORD + "  FROM " + Constants.ACCOUNT_TABLE + ";";
        return query;
    }

    public static String strSelectProduct(Product product) {
        String query = "SELECT * FROM " + Constants.PRODUCT_TABLE
                + " WHERE " + Constants.PRODUCT_COLUMN_STATUS + " = '" + Constants.STATUS_ACTIVE + "' ";
        if (product.getCode() != null) {
            query += "AND " + Constants.PRODUCT_COLUMN_CODE + " LIKE '%" + product.getCode() + "%' ";
        }
        if (product.getName() != null) {
            query += "AND " + Constants.PRODUCT_COLUMN_NAME + " LIKE '%" + product.getName() + "%' ";
        }
        if (product.getBrandCode() != null) {
            query += "AND " + Constants.PRODUCT_COLUMN_BRAND_CODE + " LIKE '%" + product.getBrandCode() + "%' ";
        }
        if (product.getCategoryCode() != null) {
            query += "AND " + Constants.PRODUCT_COLUMN_CATEGORY_CODE + " LIKE '%" + product.getCategoryCode() + "%' ";
        }
        if (product.getDescription() != null) {
            query += "AND " + Constants.PRODUCT_COLUMN_DESCRIPTION + " LIKE '%" + product.getDescription() + "%' ";
        }
        if (product.getPrice() != 0) {
            query += "AND " + Constants.PRODUCT_COLUMN_PRICE + " LIKE '%" + product.getPrice() + "%' ";
        }
        if (product.isIsHot()) {
            query += "AND " + Constants.PRODUCT_COLUMN_IS_HOT + " LIKE '%1%' ";
        }
        return query;
    }

    public static String strSelectImageProduct(String productCode) {
        String query = "SELECT TOP 4 * FROM " + Constants.IMAGE_TABLE
                + " WHERE " + Constants.IMAGE_STATUS + " = '" + Constants.STATUS_ACTIVE
                + "' AND " + Constants.IMAGE_PRODUCT_CODE + " = '" + productCode + "'";
        return query;
    }

    public static String strSelectRate(Rate rate) {
        String query = "SELECT ROUND(AVG(" + Constants.RATE_COLUMN_RATE + "),0)  AS " + Constants.RATE_COLUMN_RATE
                + " FROM " + Constants.RATE_TABLE + " WHERE 0 = 0 ";
        if (rate.getAccountCode() != null) {
            query += "AND " + Constants.RATE_COLUMN_ACCOUNT_CODE + " LIKE '%" + rate.getAccountCode() + "%' ";
        }
        if (rate.getProductCode() != null) {
            query += "AND " + Constants.RATE_COLUMN_PRODUCT_CODE + " LIKE '%" + rate.getProductCode() + "%' ";
        }
        return query;
    }

    public static String strDoRate(Rate rate) {
        String query = "DELETE FROM " + Constants.RATE_TABLE
                + " WHERE " + Constants.RATE_COLUMN_PRODUCT_CODE + " = '" + rate.getProductCode()
                + "' AND " + Constants.RATE_COLUMN_ACCOUNT_CODE + " = '" + rate.getAccountCode() + "';"
                + "INSERT INTO " + Constants.RATE_TABLE + "(" + Constants.RATE_COLUMN_PRODUCT_CODE + ", "
                + Constants.RATE_COLUMN_ACCOUNT_CODE + "," + Constants.RATE_COLUMN_RATE + ") VALUES ('"
                + rate.getProductCode() + "','" + rate.getAccountCode() + "'," + rate.getRate() + ");";
        return query;
    }

    public static String strInsertOrderDetail(OrderDetail orderDetail) {
        String query = "INSERT INTO " + Constants.ORDER_DETAIL_TABLE + "(" + Constants.ORDER_DETAIL_COLUMN_ORDER_CODE + ","
                + Constants.ORDER_DETAIL_COLUMN_PRODUCT_CODE + "," + Constants.ORDER_DETAIL_COLUMN_QUANTITY + ") VALUES ('"
                + orderDetail.getOrderCode() + "','" + orderDetail.getProductCode() + "'," + orderDetail.getQuantity() + ");";
        return query;
    }

    public static String strInsertOrder(Order order) {
        String query = "INSERT INTO " + Constants.ORDER_TABLE + " ("
                + Constants.ORDER_COLUMN_CODE + ", " + Constants.ORDER_COLUMN_ACCOUNT_CODE + ", "
                + Constants.ORDER_COLUMN_DATE + ", " + Constants.ORDER_COLUMN_NAME + ", "
                + Constants.ORDER_COLUMN_EMAIL + ", " + Constants.ORDER_COLUMN_PHONE + ", "
                + Constants.ORDER_COLUMN_SHIP_ADDRESS + ", " + Constants.ORDER_COLUMN_SHIP_DATE + ", "
                + Constants.ORDER_COLUMN_STATUS + ") VALUES ('"
                + order.getCode() + "','" + order.getAccountCode() + "',"
                + order.getDate() + ",'" + order.getName() + "','"
                + order.getEmail() + "', '" + order.getPhone() + "','"
                + order.getShipAddress() + "'," + order.getShipDate() + ",'"
                + order.getStatus() + "');";
        return query;
    }
    
    public static String strOrderMaxOrd() {
        String query = "SELECT MAX(" + Constants.ORDER_COLUMN_ORD + ") AS "
                + Constants.ORDER_COLUMN_ORD + "  FROM " + Constants.ORDER_TABLE + ";";
        return query;
    }
}
