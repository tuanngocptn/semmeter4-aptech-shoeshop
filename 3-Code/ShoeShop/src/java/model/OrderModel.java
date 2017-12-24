/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entities.Order;
import entities.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class OrderModel {
    public boolean insertOrder(Order order){
        String query = Methods.strOrderMaxOrd();
        int max = 0;
        try {
            ResultSet rs = DatabaseConnect.getResultSet(query);
            if(rs == null){
                return false;
            }
            if (rs.next()) {
                max = rs.getInt(Constants.ACCOUNT_COLUMN_ORD);
                rs.close();
            }
        } catch (SQLException ex) {
            return false;
        }
        order.setCode(Constants.FORMAT_ORDER_CODE + ++max);
        query = Methods.strInsertOrder(order);
        if(!order.getLstOrderDetail().isEmpty()){
            for (OrderDetail orderDetail : order.getLstOrderDetail()) {
                orderDetail.setOrderCode(order.getCode());
                query+= Methods.strInsertOrderDetail(orderDetail);
            }
        }
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }
}
