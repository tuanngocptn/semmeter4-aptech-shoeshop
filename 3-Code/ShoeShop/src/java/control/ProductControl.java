/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Product;
import java.util.List;
import model.ProductModel;
import others.Methods;

/**
 *
 * @author Panda
 */
public class ProductControl {
    
    private ProductModel productModel;

    public ProductControl() {
        productModel = new ProductModel();
    }
    
    public String getAllProduct(Product product){
        List<Product> lstProducts = (productModel.getAllProduct(product));
        return Methods.toJson(lstProducts);
    }
}
