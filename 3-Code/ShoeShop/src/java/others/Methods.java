/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import com.google.gson.Gson;
import entities.Account;
import entities.Brand;
import entities.Category;
import entities.ImageProduct;
import entities.Order;
import entities.OrderDetail;
import entities.Product;
import entities.Rate;
import entities.Report;
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

    //Account function
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

    public static String strGetAccount(Account account) {
        String query = "SELECT * FROM " + Constants.ACCOUNT_TABLE;
        if (account.getStatus() != null) {
            query += " WHERE (0 <> 0 ";
        } else {
            query += " WHERE 0 <> 0 ";
        }
        if (account.getCode() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_CODE + " LIKE '%" + account.getCode() + "%' ";
        }
        if (account.getRoleCode() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_ROLE_CODE + " LIKE '%" + account.getRoleCode() + "%' ";
        }
        if (account.getUser() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_USER + " LIKE '%" + account.getUser() + "%' ";
        }
        if (account.getName() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_NAME + " LIKE '%" + account.getName() + "%' ";
        }
        if (account.getAddress() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_ADDRESS + " LIKE '%" + account.getAddress() + "%' ";
        }
        if (account.getPhone() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_PHONE + " LIKE '%" + account.getPhone() + "%' ";
        }
        if (account.getEmail() != null) {
            query += " OR " + Constants.ACCOUNT_COLUMN_EMAIL + " LIKE '%" + account.getEmail() + "%' ";
        }
        if (account.getStatus() != null) {
            query += " ) AND " + Constants.ACCOUNT_COLUMN_STATUS + " LIKE '%" + account.getStatus() + "%' ";
        }
        return query;
    }

    public static String strUpdateAccount(Account account) {
        String query = "UPDATE " + Constants.ACCOUNT_TABLE + " SET "
                + Constants.ACCOUNT_COLUMN_ROLE_CODE + " = '" + account.getRoleCode() + "', "
                + Constants.ACCOUNT_COLUMN_USER + " ='" + account.getUser() + "', "
                + Constants.ACCOUNT_COLUMN_PASS + " = '" + account.getPass() + "', "
                + Constants.ACCOUNT_COLUMN_NAME + " = '" + account.getName() + "', "
                + Constants.ACCOUNT_COLUMN_ADDRESS + " = '" + account.getAddress() + "', "
                + Constants.ACCOUNT_COLUMN_PHONE + " = '" + account.getPhone() + "', "
                + Constants.ACCOUNT_COLUMN_EMAIL + " = '" + account.getEmail() + "', "
                + Constants.ACCOUNT_COLUMN_STATUS + " = '" + account.getStatus()
                + "' WHERE "
                + Constants.ACCOUNT_COLUMN_CODE + " = '" + account.getCode() + "';";
        return query;
    }

    //Product function
    public static String strSelectProduct(Product product) {
        String query;
        if (product.getTypeSearch() == null) {
            product.setTypeSearch("OR");
        }
        if (product.getTop() == 0) {
            query = "SELECT * FROM " + Constants.PRODUCT_TABLE;
        } else {
            query = "SELECT TOP " + product.getTop() + " * FROM " + Constants.PRODUCT_TABLE;
        }
        if (product.getStatus() != null) {
            query += " WHERE (0 = 0 ";
        } else {
            query += " WHERE 0 <> 0 ";
        }
        if (product.getCode() != null) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_CODE + " LIKE '%" + product.getCode() + "%' ";
        }
        if (product.getName() != null) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_NAME + " LIKE '%" + product.getName() + "%' ";
        }
        if (product.getBrandCode() != null) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_BRAND_CODE + " LIKE '%" + product.getBrandCode() + "%' ";
        }
        if (product.getCategoryCode() != null) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_CATEGORY_CODE + " LIKE '%" + product.getCategoryCode() + "%' ";
        }
        if (product.getDescription() != null) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_DESCRIPTION + " LIKE '%" + product.getDescription() + "%' ";
        }
        if (product.getPrice() != 0) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_PRICE + " LIKE '%" + product.getPrice() + "%' ";
        }
        if (product.isIsHot()) {
            query += product.getTypeSearch() + " " + Constants.PRODUCT_COLUMN_IS_HOT + " LIKE '%1%' ";
        }
        if (product.getStatus() != null) {
            query += ") AND " + Constants.PRODUCT_COLUMN_STATUS + " = '" + product.getStatus() + "' ";
        }

        return query;
    }

    public static String strSelectImageProduct(String productCode) {
        String query = "SELECT TOP 4 * FROM " + Constants.IMAGE_TABLE
                + " WHERE " + Constants.IMAGE_STATUS + " = '" + Constants.STATUS_ACTIVE
                + "' AND " + Constants.IMAGE_PRODUCT_CODE + " = '" + productCode + "'";
        return query;
    }

    public static String strProductMaxOrd() {
        String query = "SELECT MAX(" + Constants.PRODUCT_COLUMN_ORD + ") AS "
                + Constants.PRODUCT_COLUMN_ORD + "  FROM " + Constants.PRODUCT_TABLE + ";";
        return query;
    }

    public static String strAddProduct(Product product) {
        String query = "INSERT INTO " + Constants.PRODUCT_TABLE + " ("
                + Constants.PRODUCT_COLUMN_CODE + ", " + Constants.PRODUCT_COLUMN_NAME + ", "
                + Constants.PRODUCT_COLUMN_BRAND_CODE + ", " + Constants.PRODUCT_COLUMN_CATEGORY_CODE + ","
                + Constants.PRODUCT_COLUMN_DESCRIPTION + "," + Constants.PRODUCT_COLUMN_PRICE + ","
                + Constants.PRODUCT_COLUMN_IS_HOT + "," + Constants.PRODUCT_COLUMN_QUANTITY
                + ") VALUES ('"
                + product.getCode() + "','" + product.getName() + "', '"
                + product.getBrandCode() + "', '" + product.getCategoryCode() + "','"
                + product.getDescription() + "'," + product.getPrice() + ","
                + (product.isIsHot() ? 1 : 0) + "," + product.getQuantity() + ");";
        for (ImageProduct image : product.getLstImages()) {
            query += "INSERT INTO " + Constants.IMAGE_TABLE + " ("
                    + Constants.IMAGE_CODE + ", " + Constants.IMAGE_PRODUCT_CODE + ", "
                    + Constants.IMAGE_IMAGE + ") VALUES ((CONCAT('"
                    + Constants.FORMAT_PRODUCT_IMAGE + "', (SELECT MAX (" + Constants.IMAGE_TABLE + "."
                    + Constants.IMAGE_ORD + ") FROM " + Constants.IMAGE_TABLE + ") + 1)),'"
                    + product.getCode() + "','" + image.getImage() + "');";
        }
        return query;
    }

    public static String strUpdateProduct(Product product) {
        String query = "UPDATE " + Constants.PRODUCT_TABLE + " SET "
                + Constants.PRODUCT_COLUMN_NAME + " = '" + product.getName() + "', "
                + Constants.PRODUCT_COLUMN_BRAND_CODE + " = '" + product.getBrandCode() + "', "
                + Constants.PRODUCT_COLUMN_CATEGORY_CODE + " = '" + product.getCategoryCode() + "', "
                + Constants.PRODUCT_COLUMN_DESCRIPTION + " ='" + product.getDescription() + "' , "
                + Constants.PRODUCT_COLUMN_PRICE + " = " + product.getPrice() + ", "
                + Constants.PRODUCT_COLUMN_IS_HOT + " = " + (product.isIsHot() ? 1 : 0) + ", "
                + Constants.PRODUCT_COLUMN_QUANTITY + " = " + product.getQuantity() + " ,"
                + Constants.PRODUCT_COLUMN_STATUS + " = '" + product.getStatus()
                + "' WHERE "
                + Constants.PRODUCT_COLUMN_CODE + " = '" + product.getCode() + "';";
        query += "DELETE FROM " + Constants.IMAGE_TABLE + " WHERE " + Constants.IMAGE_PRODUCT_CODE + " = '" + product.getCode() + "';";
        for (ImageProduct image : product.getLstImages()) {
            query += "INSERT INTO " + Constants.IMAGE_TABLE + " ("
                    + Constants.IMAGE_CODE + ", " + Constants.IMAGE_PRODUCT_CODE + ", "
                    + Constants.IMAGE_IMAGE + ") VALUES ((CONCAT('"
                    + Constants.FORMAT_PRODUCT_IMAGE + "', (SELECT MAX (" + Constants.IMAGE_TABLE + "."
                    + Constants.IMAGE_ORD + ") FROM " + Constants.IMAGE_TABLE + ") + 1)),'"
                    + product.getCode() + "','" + image.getImage() + "');";
        }
        return query;
    }

    //Rate function
    public static String strSelectRate(Rate rate) {
        String query = "SELECT ROUND(AVG(" + Constants.RATE_COLUMN_RATE + "),0)  AS " + Constants.RATE_COLUMN_RATE
                + " FROM " + Constants.RATE_TABLE + " WHERE 0 = 0 ";
        if (rate.getAccountCode() != null) {
            query += "OR " + Constants.RATE_COLUMN_ACCOUNT_CODE + " LIKE '%" + rate.getAccountCode() + "%' ";
        }
        if (rate.getProductCode() != null) {
            query += "OR " + Constants.RATE_COLUMN_PRODUCT_CODE + " LIKE '%" + rate.getProductCode() + "%' ";
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

    //Order function
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

    public static String strGetOrder(Order order) {
        String query = "SELECT * FROM " + Constants.ORDER_TABLE + " WHERE "
                + Constants.ORDER_COLUMN_STATUS + " <> 'deactive' AND ( 0<>0 ";
        if (order.getCode() != null) {
            query += " OR " + Constants.ORDER_COLUMN_CODE + " LIKE '%" + order.getCode() + "%' ";
        }
        if (order.getAccountCode() != null) {
            query += " OR " + Constants.ORDER_COLUMN_ACCOUNT_CODE + " LIKE '%" + order.getAccountCode() + "%' ";
        }
        if (order.getDate() != null) {
            query += " OR " + Constants.ORDER_COLUMN_DATE + " LIKE '%" + order.getDate() + "%' ";
        }
        if (order.getName() != null) {
            query += " OR " + Constants.ORDER_COLUMN_NAME + " LIKE '%" + order.getName() + "%' ";
        }
        if (order.getEmail() != null) {
            query += " OR " + Constants.ORDER_COLUMN_EMAIL + " LIKE '%" + order.getEmail() + "%' ";
        }
        if (order.getPhone() != null) {
            query += " OR " + Constants.ORDER_COLUMN_PHONE + " LIKE '%" + order.getPhone() + "%' ";
        }
        if (order.getShipAddress() != null) {
            query += " OR " + Constants.ORDER_COLUMN_SHIP_ADDRESS + " LIKE '%" + order.getShipAddress() + "%' ";
        }
        if (order.getShipDate() != null) {
            query += " OR " + Constants.ORDER_COLUMN_SHIP_DATE + " LIKE '%" + order.getShipDate() + "%' ";
        }
        if (order.getStatus() != null) {
            query += " OR " + Constants.ORDER_COLUMN_STATUS + " LIKE '%" + order.getStatus() + "%' ";
        }
        query += ");";
        return query;
    }

    public static String strGetOrderDetail(OrderDetail orderDetail) {
        String query = "SELECT * FROM " + Constants.ORDER_DETAIL_TABLE + " WHERE "
                + Constants.ORDER_DETAIL_COLUMN_ORDER_CODE + " LIKE '%" + orderDetail.getOrderCode() + "%'";
        return query;
    }

    public static String strUpdateOrder(Order order) {
        String query = "UPDATE " + Constants.ORDER_TABLE + " SET "
                + Constants.ORDER_COLUMN_ACCOUNT_CODE + " = '" + order.getAccountCode() + "', "
                + Constants.ORDER_COLUMN_DATE + " ='" + order.getDate() + "', "
                + Constants.ORDER_COLUMN_NAME + " ='" + order.getName() + "', "
                + Constants.ORDER_COLUMN_EMAIL + " ='" + order.getEmail() + "', "
                + Constants.ORDER_COLUMN_PHONE + " ='" + order.getPhone() + "', "
                + Constants.ORDER_COLUMN_SHIP_ADDRESS + " ='" + order.getShipAddress() + "', "
                + Constants.ORDER_COLUMN_SHIP_DATE + " ='" + order.getShipDate() + "', "
                + Constants.ORDER_COLUMN_STATUS + " = '" + order.getStatus()
                + "' WHERE "
                + Constants.ORDER_COLUMN_CODE + " = '" + order.getCode() + "';";
        query += " DELETE FROM " + Constants.ORDER_TABLE + " WHERE " + Constants.ORDER_COLUMN_CODE + " = '%" + order.getCode() + "%'";
        for (OrderDetail orderDetail : order.getLstOrderDetail()) {
            query += strInsertOrderDetail(orderDetail);
        }
        return query;
    }

    //Category function
    public static String strSelectCategory(Category category) {
        String query = "SELECT "
                + Constants.CATEGORY_COLUMN_ORD + ", " + Constants.CATEGORY_COLUMN_CODE + ", "
                + Constants.CATEGORY_COLUMN_NAME + ", " + Constants.CATEGORY_COLUMN_STATUS + ", "
                + Constants.CATEGORY_COLUMN_LOGO + " FROM " + Constants.CATEGORY_TABLE + " WHERE 0 <> 0 ";
        if (category.getStatus() != null) {
            query += " OR " + Constants.CATEGORY_COLUMN_STATUS + " LIKE '%" + category.getStatus() + "%' ";
        }
        if (category.getCode() != null) {
            query += " OR " + Constants.CATEGORY_COLUMN_CODE + " LIKE '%" + category.getCode() + "%' ";
        }
        if (category.getName() != null) {
            query += " OR " + Constants.CATEGORY_COLUMN_NAME + " LIKE '%" + category.getName() + "%';";
        }
        return query;
    }

    public static String strCateMaxOrd() {
        String query = "SELECT MAX(" + Constants.CATEGORY_COLUMN_ORD + ") AS "
                + Constants.CATEGORY_COLUMN_ORD + "  FROM " + Constants.CATEGORY_TABLE + ";";
        return query;
    }

    public static String strInsertCategory(Category category) {
        String query = "INSERT INTO " + Constants.CATEGORY_TABLE + " ("
                + Constants.CATEGORY_COLUMN_CODE + ", " + Constants.CATEGORY_COLUMN_NAME + ", "
                + Constants.CATEGORY_COLUMN_LOGO + ") "
                + "VALUES ('" + category.getCode() + "','" + category.getName() + "','"
                + category.getLogo() + "')";
        return query;
    }

    public static String strUpdateCategory(Category category) {
        String query = "UPDATE " + Constants.CATEGORY_TABLE + " SET "
                + Constants.CATEGORY_COLUMN_NAME + " = '" + category.getName() + "', "
                + Constants.CATEGORY_COLUMN_STATUS + " ='" + category.getStatus() + "', "
                + Constants.CATEGORY_COLUMN_LOGO + " = '" + category.getLogo()
                + "' WHERE "
                + Constants.CATEGORY_COLUMN_CODE + " = '" + category.getCode() + "';";
        return query;
    }

    //Brand function
    public static String strBrandMaxOrd() {
        String query = "SELECT MAX(" + Constants.BRAND_COLUMN_ORD + ") AS "
                + Constants.BRAND_COLUMN_ORD + "  FROM " + Constants.BRAND_TABLE + ";";
        return query;
    }

    public static String strSelectBrand(Brand brand) {
        String query = "SELECT "
                + Constants.BRAND_COLUMN_ORD + ", " + Constants.BRAND_COLUMN_CODE + ", "
                + Constants.BRAND_COLUMN_NAME + ", " + Constants.BRAND_COLUMN_STATUS + ", "
                + Constants.BRAND_COLUMN_LOGO + " FROM " + Constants.BRAND_TABLE + " WHERE 0 <> 0 ";
        if (brand.getStatus() != null) {
            query += " OR " + Constants.BRAND_COLUMN_STATUS + " LIKE '%" + brand.getStatus() + "%' ";
        }
        if (brand.getCode() != null) {
            query += " OR " + Constants.BRAND_COLUMN_CODE + " LIKE '%" + brand.getCode() + "%' ";
        }
        if (brand.getName() != null) {
            query += " OR " + Constants.BRAND_COLUMN_NAME + " LIKE '%" + brand.getName() + "%';";
        }
        return query;
    }

    public static String strInsertBrand(Brand brand) {
        String query = "INSERT INTO " + Constants.BRAND_TABLE + " ("
                + Constants.BRAND_COLUMN_CODE + ", " + Constants.BRAND_COLUMN_NAME + ", "
                + Constants.BRAND_COLUMN_LOGO + ") "
                + "VALUES ('" + brand.getCode() + "','" + brand.getName() + "','"
                + brand.getLogo() + "')";
        return query;
    }

    public static String strUpdateBrand(Brand brand) {
        String query = "UPDATE " + Constants.BRAND_TABLE + " SET "
                + Constants.BRAND_COLUMN_NAME + " = '" + brand.getName() + "', "
                + Constants.BRAND_COLUMN_STATUS + " ='" + brand.getStatus() + "', "
                + Constants.BRAND_COLUMN_LOGO + " = '" + brand.getLogo()
                + "' WHERE "
                + Constants.BRAND_COLUMN_CODE + " = '" + brand.getCode() + "';";
        return query;
    }
    
    public static String strSearchReport(Report report){
        String query = "SELECT * FROM VIEW_REPORT WHERE 0 = 0 ";
        if(report.getStartDate() != null){
            query += " AND _date >= " + report.getStartDate();
        }
        if(report.getEndDate()!= null){
            query += " AND _date <= " + report.getEndDate();
        }
        return query;
    }

}
