package cn.smbms.dao.shop;

import cn.smbms.pojo.Bill;

/**
 * Created by mac on 18/4/19.
 */
public interface ShopDao {
    /**
     * 根据RFIDid去查询商品
     * @return
     * @throws Exception
     */
    public Bill queryByRfidId(String RfidId) throws  Exception;

}
