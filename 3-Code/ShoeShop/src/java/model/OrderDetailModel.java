/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
public class OrderDetailModel {
    public List<OrderDetail> getOrderDetal(String codeOrder){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderCode(codeOrder);
        String query = Methods.strGetOrderDetail(orderDetail);
        List<OrderDetail> lstOrderDetails = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)){
            if (rs == null) {
                return new ArrayList<>();
            }
            while (rs.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrd(rs.getInt(Constants.ORDER_DETAIL_COLUMN_ORD));
                orderDetail.setOrderCode(rs.getString(Constants.ORDER_DETAIL_COLUMN_ORDER_CODE));
                orderDetail.setProductCode(rs.getString(Constants.ORDER_DETAIL_COLUMN_PRODUCT_CODE));
                orderDetail.setQuantity(rs.getInt(Constants.ORDER_DETAIL_COLUMN_QUANTITY));
                lstOrderDetails.add(orderDetail);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lstOrderDetails;
    }
}
