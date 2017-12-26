/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Brand;
import java.util.List;
import model.BrandModel;

/**
 *
 * @author Panda
 */
public class BrandControl {
    
    private BrandModel brandModel;

    public BrandControl() {
        brandModel = new BrandModel();
    }
    
    public boolean add(Brand brand){
        return brandModel.add(brand);
    }
    
    public boolean update(Brand brand){
        return brandModel.update(brand);
    }
    
    public List<Brand> get(Brand brand){
        return brandModel.get(brand);
    }
}
