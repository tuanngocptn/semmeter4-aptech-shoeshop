/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Brand;
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
public class BrandModel {

    public boolean add(Brand brand) {
        String query = Methods.strBrandMaxOrd();
        int max = 0;
        try {
            ResultSet rs = DatabaseConnect.getResultSet(query);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                max = rs.getInt(Constants.BRAND_COLUMN_ORD);
                rs.close();
            }
        } catch (SQLException ex) {
        }
        brand.setCode(Constants.FORMAT_BRAND_CODE + ++max);
        brand.setStatus(Constants.STATUS_ACTIVE);
        query = Methods.strInsertBrand(brand);
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }

    public List<Brand> get(Brand brand) {
        String query = Methods.strSelectBrand(brand);
        List<Brand> lstBrands = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)){
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Brand cate = new Brand();
                cate.setOrd(rs.getInt(Constants.BRAND_COLUMN_ORD));
                cate.setCode(rs.getString(Constants.BRAND_COLUMN_CODE));
                cate.setName(rs.getString(Constants.BRAND_COLUMN_NAME));
                cate.setStatus(rs.getString(Constants.BRAND_COLUMN_STATUS));
                cate.setLogo(rs.getString(Constants.BRAND_COLUMN_LOGO));
                lstBrands.add(cate);
            }
            rs.close();
            return lstBrands;
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public boolean update(Brand brand){
        String query = Methods.strUpdateBrand(brand);
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }
}
