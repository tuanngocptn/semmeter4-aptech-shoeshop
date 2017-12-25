/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Category;
import java.util.List;
import model.CategoryModel;

/**
 *
 * @author Panda
 */
public class CategoryControl {
    
    private CategoryModel categoryModel;

    public CategoryControl() {
        categoryModel = new CategoryModel();
    }
    
    public boolean add(Category category){
        return categoryModel.add(category);
    }
    
    public boolean update(Category category){
        return categoryModel.update(category);
    }
    
    public List<Category> get(Category category){
        return categoryModel.get(category);
    }
}
