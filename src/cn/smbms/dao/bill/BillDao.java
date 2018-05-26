package cn.smbms.dao.bill;

import java.util.List;

import cn.smbms.pojo.Bill;

public interface BillDao {
	/**
	 *  根据供应商ID查询订单数量
	 * @param providerId
	 * @return
	 */
	public int getBillCountByProviderId(String providerId)throws Exception;
	/**
	 * 删除订单
	 * @param id
	 * @return
	 */
	public int delBill(String id)throws Exception;
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
	public List<Bill>getBillList(String productName,int providerId,int isPayment
			,int currentPageNo, int pageSize)throws Exception ;
	/**
	 * 条件模糊查询 订单总数
	 * @param providerName
	 * @param providerId
	 * @param isPayment
	 * @return
	 */
	public int getBillCount(String providerName,int providerId,int isPayment)throws Exception;
	/**
	 * 通过id  查看用户信息
	 * @param id
	 * @return
	 */
	public Bill getBill(String id)throws Exception;
	/**
	 * 添加
	 * @param bill
	 * @return
	 */
	public int add(Bill bill)throws Exception;
	/**
	 * 修改
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public int update(Bill bill)throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int del(String id)throws Exception;
}
