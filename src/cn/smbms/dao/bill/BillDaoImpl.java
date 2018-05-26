package cn.smbms.dao.bill;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Bill;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository("billDaoImpl")
public class BillDaoImpl implements BillDao{

	@Override
	public int getBillCountByProviderId(String providerId)throws Exception{
		ResultSet rs=null;
		PreparedStatement ps=null;
		int count=0;
		String sql=" select count(1) as billCount from smbms_bill where providerId = ? ";
		Object[]param={providerId};
		rs=BaseDao.query(sql, param);
		if(rs.next()){
			count=rs.getInt("billCount");
		}
		BaseDao.closeAll(rs, ps, null);
		return count;
	}

	@Override
	public int delBill(String id)throws Exception {
		int count=0;
		PreparedStatement ps=null;
		String sql=" delete form smbms_bill where id=? ";
		Object[]param={id};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public List<Bill> getBillList(String productName, int providerId,
			int isPayment, int currentPageNo, int pageSize)throws Exception {
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Bill>billList=new ArrayList<Bill>();
		List<Object>param=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select b.*,p.proName as providerName from smbms_bill b, smbms_provider p where b.providerId = p.id ");
		if (!StringUtils.isNullOrEmpty(productName)) {
			sql.append(" and productName like ? ");
			param.add( "%" + productName + "%" );
		}
		if (providerId>0) {
			sql.append(" and providerId = ? ");
			param.add(providerId);
		}
		if(isPayment> 0){
			sql.append(" and isPayment = ?");
			param.add(isPayment);
		}
		sql.append(" order by creationDate DESC limit ?,? ");
		currentPageNo=(currentPageNo-1)*pageSize;
		param.add(currentPageNo);
		param.add(pageSize);
		rs=BaseDao.query(sql.toString(), param.toArray());
		while(rs.next()){
			Bill bill = new Bill();
			bill.setId(rs.getInt("id"));
			bill.setBillCode(rs.getString("billCode"));
			bill.setProductName(rs.getString("productName"));
			bill.setProductDesc(rs.getString("productDesc"));
			bill.setProductUnit(rs.getString("productUnit"));
			bill.setProductCount(rs.getBigDecimal("productCount"));
			bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
			bill.setIsPayment(rs.getInt("isPayment"));
			bill.setProviderId(rs.getInt("providerId"));
			bill.setProviderName(rs.getString("providerName"));
			bill.setCreationDate(rs.getTimestamp("creationDate"));
			bill.setCreatedBy(rs.getInt("createdBy"));
			billList.add(bill);
		}
		BaseDao.closeAll(rs, ps, null);
		return billList;
	}
	@Override
	public int getBillCount(String productName, int providerId, int isPayment)throws Exception {
		int count=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Object>param=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(1) as billCount from smbms_bill b, smbms_provider p where b.providerId = p.id ");
		if (!StringUtils.isNullOrEmpty(productName)) {
			sql.append(" and productName like ? ");
			param.add( "%" + productName + "%" );
		}
		if (providerId>0) {
			sql.append(" and providerId = ? ");
			param.add(providerId);
		}
		if(isPayment> 0){
			sql.append(" and isPayment = ?");
			param.add(isPayment);
		}
		rs=BaseDao.query(sql.toString(), param.toArray());
		if(rs.next()){
			count=rs.getInt("billCount");
		}
		BaseDao.closeAll(rs, ps, null);
		return count;
	}

	@Override
	public Bill getBill(String id)throws Exception {
		PreparedStatement ps=null;
		ResultSet rs=null;
		Bill bill=null;
		String sql=" select b.*,p.proName as providerName from smbms_bill b,smbms_provider p where b.providerId = p.id and b.id=?  ";
		Object[]param={id};
		rs=BaseDao.query(sql, param);
		if(rs.next()){
			bill = new Bill();
			bill.setId(rs.getInt("id"));
			bill.setBillCode(rs.getString("billCode"));
			bill.setProductName(rs.getString("productName"));
			bill.setProductDesc(rs.getString("productDesc"));
			bill.setProductUnit(rs.getString("productUnit"));
			bill.setProductCount(rs.getBigDecimal("productCount"));
			bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
			bill.setIsPayment(rs.getInt("isPayment"));
			bill.setProviderId(rs.getInt("providerId"));
			bill.setProviderName(rs.getString("providerName"));
			bill.setModifyBy(rs.getInt("modifyBy"));
			bill.setModifyDate(rs.getTimestamp("modifyDate"));
		}
		BaseDao.closeAll(rs, ps, null);
		return bill;
	}

	@Override
	public int add(Bill bill)throws Exception{
		int count=0;
		PreparedStatement ps=null;
		String sql = "insert into smbms_bill (billCode,productName,productDesc," +
				"productUnit,productCount,totalPrice,isPayment,providerId,createdBy,creationDate) " +
				"values(?,?,?,?,?,?,?,?,?,?)";
		Object[] param = {bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),
				bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
				bill.getProviderId(),bill.getCreatedBy(),bill.getCreationDate()};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public int update(Bill bill) throws Exception {
		int count=0;
		PreparedStatement ps=null;
		String sql = "update smbms_bill set productName=?," +
				"productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
				"isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ? ";
		Object[] param = {bill.getProductName(),bill.getProductDesc(),
						bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
						bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public int del(String id) throws Exception {
		int count=0;
		PreparedStatement ps=null;
		String sql=" delete from smbms_bill where id=? ";
		Object []param={id};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}
}
