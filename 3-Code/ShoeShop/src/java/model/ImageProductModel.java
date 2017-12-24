/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import control.ProductControl;
import entities.ImageProduct;
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
public class ImageProductModel {
    public List<ImageProduct> getImageProduct(String productCode){
        String query = Methods.strSelectImageProduct(productCode);
        List<ImageProduct> lstImageProducts = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)){
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setOrd(rs.getInt(Constants.IMAGE_ORD));
                imageProduct.setCode(rs.getString(Constants.IMAGE_CODE));
                imageProduct.setProductCode(rs.getString(Constants.IMAGE_PRODUCT_CODE));
                imageProduct.setStatus(rs.getString(Constants.IMAGE_STATUS));
                imageProduct.setImage(rs.getString(Constants.IMAGE_IMAGE));
                lstImageProducts.add(imageProduct);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lstImageProducts;
    }
}
