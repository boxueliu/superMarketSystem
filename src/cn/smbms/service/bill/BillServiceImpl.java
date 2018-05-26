package cn.smbms.service.bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;
@Service("billServiceImpl")
public class BillServiceImpl implements BillService{

	@Resource
	private BillDao billDao;
	
	@Override
	public boolean delBill(String id) {
		Connection con=null;
		boolean flag=false;
		try {
			con=BaseDao.getCon();
			if (billDao.delBill(id)>0) {
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

	@Override
	public List<Bill> getBillList(String providerName, int providerId,
			int isPayment, int currentPageNo, int pageSize) {
		Connection con=null;
		List<Bill>billList=null;
		try {
			con=BaseDao.getCon();
			billList=billDao.getBillList(providerName, providerId, isPayment, currentPageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return billList;
	}

	@Override
	public int getBillCount(String providerName, int providerId, int isPayment){
		int count=0;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			count=billDao.getBillCount(providerName, providerId, isPayment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return count;
	}

	@Override
	public Bill getBill(String id) {
		Connection con=null;
		Bill bill=null;
		try {
			con=BaseDao.getCon();
			bill=billDao.getBill(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return bill;
	}

	@Override
	public boolean add(Bill bill) {
		Connection con=null;
		boolean flag=false;
		try {
			con=BaseDao.getCon();
			con.setAutoCommit(false);
			if (billDao.add(bill)>0) {
				flag=true;
				con.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return flag;
	}

	@Override
	public boolean del(String id) {
		// TODO Auto-generated method stub
				Connection con = null;
				boolean flag = false;
				try {
					con = BaseDao.getCon();
					if(billDao.del(id)>0){
						flag = true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					BaseDao.closeAll(null, null, con);
				}
				return flag;
	}
	@Override
	public boolean update(Bill bill) {
		Connection con=null;
		boolean flag=false;
		try {
			con=BaseDao.getCon();
			if(billDao.update(bill)>0){
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

}
