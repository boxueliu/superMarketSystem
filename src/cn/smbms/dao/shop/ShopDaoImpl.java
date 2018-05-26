package cn.smbms.dao.shop;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Bill;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by mac on 18/4/19.
 */
@Repository("shopDaoImpl")
public class ShopDaoImpl implements ShopDao {

    @Override
    public Bill queryByRfidId(String RfidId) throws Exception {
        Bill bill = new Bill();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql=" select * from smbms_bill where RFIDId=?";
        Object[] param={RfidId};
        rs= BaseDao.query(sql, param);
        while(rs.next()) {
            bill.setId(rs.getInt("id"));
            bill.setBillCode(rs.getString("billCode"));
            bill.setProductName(rs.getString("productName"));
            bill.setProductDesc(rs.getString("productDesc"));
            bill.setProductUnit(rs.getString("productUnit"));
            bill.setProductCount(rs.getBigDecimal("productCount"));
            bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
            bill.setIsPayment(rs.getInt("isPayment"));
            bill.setProviderId(rs.getInt("providerId"));
//            bill.setProviderName(rs.getString("provductUnit"));
            bill.setCreationDate(rs.getTimestamp("creationDate"));
            bill.setCreatedBy(rs.getInt("createdBy"));
            bill.setRFIDId(rs.getString("RFIDID"));
        }
        return bill;
    }
}
