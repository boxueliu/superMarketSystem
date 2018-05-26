package cn.smbms.service.shop;


import cn.smbms.dao.BaseDao;
import cn.smbms.dao.shop.ShopDao;
import cn.smbms.pojo.Bill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;

/**
 * Created by mac on 18/4/19.
 */
@Service("shopServiceImpl")
public class ShopServeImpl implements ShopService {
    @Resource
    private ShopDao shopDao;

    @Override
    public Bill queryByRFIDId(String rfidId) {
        Bill bill=new Bill();
        Connection con=null;
        try {
            con= BaseDao.getCon();
           bill=shopDao.queryByRfidId(rfidId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeAll(null, null, con);
        }
        return bill;
    }

    @Override
    public String submitOrder() {
        return null;
    }
}
