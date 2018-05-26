package cn.smbms.service.bill;

import java.util.List;

import cn.smbms.pojo.Bill;

public interface BillService {
/**
 * 删除
 * @param id
 * @return
 */
	public boolean delBill(String id);
	
	/**
	 * 添加模糊查询 订单列表 并分页
	 * @param providerName
	 * @param providerId
	 * @param isPayment
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Bill>getBillList(String providerName,int providerId,int isPayment
			,int currentPageNo, int pageSize);
	/**
	 * 条件模糊查询 订单总数
	 * @param providerName
	 * @param providerId
	 * @param isPayment
	 * @return
	 */
	public int getBillCount(String providerName,int providerId,int isPayment);
	
	/**
	 * 通过id  查看用户信息
	 * @param id
	 * @return
	 */
	public Bill getBill(String id);
	/**
	 * 添加   事务
	 * @param bill
	 * @return
	 */
	public boolean add(Bill bill);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean del(String id);
	/**
	 * 修改订单信息
	 * @param bill
	 * @return
	 */
	public boolean update(Bill bill);
	
	
	

	/**
	 * 查询是否存在  唯一
	 * @param billCode
	 * @return
	 *//*
	public Bill selectBillCodeExist(String billCode);*/
}
