package cn.smbms.service.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.bill.BillDao;
import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
@Service("providerServiceImpl")
public class ProviderServiceImpl implements ProviderService{

	@Resource
	private ProviderDao providerDao;
	
	@Resource
	private BillDao billDao;
	
	@Override
	public List<Provider> getproviderList(String proCode, String proName,int currentPageNo,int pageSize) {
		List<Provider>providerList=null;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			providerList=providerDao.getproviderList(proCode, proName, currentPageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return providerList;
	}

	@Override
	public int getproviderCount(String proCode, String proName){
		int count=0;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			count=providerDao.getproviderCount(proCode, proName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return count;
	}

	@Override
	public boolean add(Provider provider) {
		boolean flag=false;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			con.setAutoCommit(false);//开启事务
			if(providerDao.add(provider)>0){
				flag=true;
				con.commit();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return flag;
	}

	@Override
	public Provider getProviderById(String id) {
		Provider provider=null;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			provider=providerDao.getProviderById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return provider;
	}

	@Override
	public boolean modify(Provider provider) {
		boolean flag=false;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			if(providerDao.modify(provider)>0){
				System.out.println("!!!!!!!!!!!!");
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return flag;
	}
	/**
	 * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作
	 * 若订单表中无该供应商的订单数据，则可以删除
	 * 若有该供应商的订单数据，则不可以删除
	 * 返回值billCount
	 * 1> billCount == 0  删除---1 成功 （0） 2 不成功 （-1）
	 * 2> billCount > 0    不能删除 查询成功（0）查询不成功（-1）
	 *                 需要事务
	 * ---判断
	 * 如果billCount = -1 失败
	 * 若billCount >= 0 成功
	 */
	@Override
	public int delpro(String id) {
		int billCount=-1;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			con.setAutoCommit(false);//开启事务
			billCount=billDao.getBillCountByProviderId(id);
			if(billCount==0){
				providerDao.delpro(id);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			billCount = -1;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return billCount;
	}

	@Override
	public List<Provider> getproviderList2() {
		Connection con=null;
		List<Provider>providerList=null;
		try {
			con=BaseDao.getCon();
			providerList=providerDao.getproviderList2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return providerList;
	}

}
