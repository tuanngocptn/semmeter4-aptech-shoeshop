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
public class OrderModel {

    public boolean insertOrder(Order order) {
        String query = Methods.strOrderMaxOrd();
        int max = 0;
        try {
            ResultSet rs = DatabaseConnect.getResultSet(query);
            if (rs == null) {
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
        if (!order.getLstOrderDetail().isEmpty()) {
            for (OrderDetail orderDetail : order.getLstOrderDetail()) {
                orderDetail.setOrderCode(order.getCode());
                query += Methods.strInsertOrderDetail(orderDetail);
            }
        }
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }

    public List<Order> getOrder(Order order) {
        OrderDetailModel orderDetailModel = new OrderDetailModel();
        String query = Methods.strGetOrder(order);
        List<Order> lstOrder = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)) {
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Order orderResult = new Order();
                orderResult.setOrd(rs.getInt(Constants.ORDER_COLUMN_ORD));
                orderResult.setCode(rs.getString(Constants.ORDER_COLUMN_CODE));
                orderResult.setAccountCode(rs.getString(Constants.ORDER_COLUMN_ACCOUNT_CODE));
                orderResult.setDate(rs.getLong(Constants.ORDER_COLUMN_DATE));
                orderResult.setName(rs.getString(Constants.ORDER_COLUMN_NAME));
                orderResult.setEmail(rs.getString(Constants.ORDER_COLUMN_EMAIL));
                orderResult.setPhone(rs.getString(Constants.ORDER_COLUMN_PHONE));
                orderResult.setShipAddress(rs.getString(Constants.ORDER_COLUMN_SHIP_ADDRESS));
                orderResult.setShipDate(rs.getLong(Constants.ORDER_COLUMN_SHIP_DATE));
                orderResult.setStatus(rs.getString(Constants.ORDER_COLUMN_STATUS));
                orderResult.setLstOrderDetail(orderDetailModel.getOrderDetal(orderResult.getCode()));
                lstOrder.add(orderResult);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lstOrder;
    }
    
    public boolean updateOrder(Order order){
        String query = Methods.strUpdateOrder(order);
        return DatabaseConnect.excute(query);
    }
}
