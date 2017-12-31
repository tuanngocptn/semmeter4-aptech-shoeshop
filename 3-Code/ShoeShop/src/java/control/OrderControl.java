/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Order;
import java.util.List;
import model.OrderModel;

/**
 *
 * @author Panda
 */
public class OrderControl {

    OrderModel orderModerl;
    
    public OrderControl() {
        orderModerl = new OrderModel();
    }
    
    public boolean insertOrder(Order order){
        return orderModerl.insertOrder(order);
    }
    
    public List<Order> getOrder(Order order){
        return orderModerl.getOrder(order);
    }
    
    public boolean update(Order order){
        return orderModerl.updateOrder(order);
    }
}
