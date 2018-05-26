package cn.smbms.service.shop;

import cn.smbms.pojo.Bill;

/**
 * Created by mac on 18/4/19.
 */

public interface ShopService {
    /**
     * 查询接口根据Rfid
     * @return
     */
    public Bill queryByRFIDId(String rfidId);

//    /**
//     * 新增数量的接口
//     * @return
//     */
//    public boolean addBillNumber();
//
//    /***
//     * 减少数量的接口
//     * @return
//     */
//    public boolean  decreaseBillNumber();

    /**
     * 提交订单的接口
     * @return
     */
    public String submitOrder();


}
