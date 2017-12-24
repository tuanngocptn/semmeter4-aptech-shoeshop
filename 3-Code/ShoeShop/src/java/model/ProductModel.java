/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.ProductControl;
import entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class ProductModel {

    public List<Product> getAllProduct(Product product) {
        ImageProductModel imageProductModel = new ImageProductModel();
        String query = Methods.strSelectProduct(product);
        List<Product> lstProducts = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)){
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                product = new Product();
                product.setOrd(rs.getInt(Constants.PRODUCT_COLUMN_ORD));
                product.setCode(rs.getString(Constants.PRODUCT_COLUMN_CODE));
                product.setName(rs.getString(Constants.PRODUCT_COLUMN_NAME));
                product.setBrandCode(rs.getString(Constants.PRODUCT_COLUMN_BRAND_CODE));
                product.setCategoryCode(rs.getString(Constants.PRODUCT_COLUMN_CATEGORY_CODE));
                product.setDescription(rs.getString(Constants.PRODUCT_COLUMN_DESCRIPTION));
                product.setPrice(rs.getDouble(Constants.PRODUCT_COLUMN_PRICE));
                product.setIsHot(rs.getBoolean(Constants.PRODUCT_COLUMN_IS_HOT));
                product.setQuantity(rs.getInt(Constants.PRODUCT_COLUMN_QUANTITY));
                product.setStatus(rs.getString(Constants.PRODUCT_COLUMN_STATUS));
                product.setLstImages(imageProductModel.getImageProduct(product.getCode()));
                lstProducts.add(product);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lstProducts;
    }
}
