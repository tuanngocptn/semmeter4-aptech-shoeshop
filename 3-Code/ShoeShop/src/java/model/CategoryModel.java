/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class CategoryModel {

    public boolean add(Category category) {
        String query = Methods.strCateMaxOrd();
        int max = 0;
        try {
            ResultSet rs = DatabaseConnect.getResultSet(query);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                max = rs.getInt(Constants.CATEGORY_COLUMN_ORD);
                rs.close();
            }
        } catch (SQLException ex) {
        }
        category.setCode(Constants.FORMAT_CATEGORY_CODE + ++max);
        category.setStatus(Constants.STATUS_ACTIVE);
        query = Methods.strInsertCategory(category);
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }

    public List<Category> get(Category category) {
        String query = Methods.strSelectCategory(category);
        List<Category> lstCategories = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)){
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Category cate = new Category();
                cate.setOrd(rs.getInt(Constants.CATEGORY_COLUMN_ORD));
                cate.setCode(rs.getString(Constants.CATEGORY_COLUMN_CODE));
                cate.setName(rs.getString(Constants.CATEGORY_COLUMN_NAME));
                cate.setStatus(rs.getString(Constants.CATEGORY_COLUMN_STATUS));
                cate.setLogo(rs.getString(Constants.CATEGORY_COLUMN_LOGO));
                lstCategories.add(cate);
            }
            rs.close();
            return lstCategories;
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public boolean update(Category category){
        String query = Methods.strUpdateCategory(category);
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }
}
