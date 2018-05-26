package cn.smbms.dao.provider;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Provider;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository("providerDaoImpl")
public class ProviderDaoImpl implements ProviderDao {

	@Override
	public List<Provider> getproviderList(String proCode, String proName,int currentPageNo,int pageSize)throws Exception{
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Provider>providerList=new ArrayList<Provider>();
		List<Object>param=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from smbms_provider where 1=1 ");
		if (!StringUtils.isNullOrEmpty(proCode)) {
			sql.append(" and proCode like ?");
			param.add("%"+proCode+"%");
		}
		if (!StringUtils.isNullOrEmpty(proName)) {
			sql.append(" and proName like ?");
			param.add("%"+proName+"%");
		}
		sql.append(" order by creationDate DESC limit ?,? ");
		currentPageNo=(currentPageNo-1)*pageSize;
		param.add(currentPageNo);
		param.add(pageSize);
		rs=BaseDao.query(sql.toString(), param.toArray());
		while(rs.next()){
			Provider provider = new Provider();
			provider.setId(rs.getInt("id"));
			provider.setProCode(rs.getString("proCode"));
			provider.setProName(rs.getString("proName"));
			provider.setProDesc(rs.getString("proDesc"));
			provider.setProContact(rs.getString("proContact"));
			provider.setProPhone(rs.getString("proPhone"));
			provider.setProAddress(rs.getString("proAddress"));
			provider.setProFax(rs.getString("proFax"));
			provider.setCreationDate(rs.getTimestamp("creationDate"));
			providerList.add(provider);
		}
		BaseDao.closeAll(rs, ps, null);
		return providerList;
	}

	@Override
	public int getproviderCount(String proCode, String proName)
			throws Exception {
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		List<Object>param=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(1) as count from smbms_provider where 1=1 ");
		if (!StringUtils.isNullOrEmpty(proCode)) {
			sql.append(" and proCode like ?");
			param.add("%"+proCode+"%");
		}
		if (!StringUtils.isNullOrEmpty(proName)) {
			sql.append(" and proName like ?");
			param.add("%"+proName+"%");
		}
		rs=BaseDao.query(sql.toString(), param.toArray());
		if(rs.next()){
			count=rs.getInt("count");
		}
		BaseDao.closeAll(rs, ps, null);
		return count;
	}

	public static void main(String[] args) {
		ProviderDaoImpl daoImpl=new ProviderDaoImpl();
		try {
			daoImpl.getproviderList(null, null, 1, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int add(Provider provider) throws Exception {
		int count=0;
		PreparedStatement ps=null;
		String sql = "insert into smbms_provider (proCode,proName,proDesc," +
				"proContact,proPhone,proAddress,proFax,createdBy,creationDate) " +
				"values(?,?,?,?,?,?,?,?,?)";
		Object[] param = {provider.getProCode(),provider.getProName(),provider.getProDesc(),
				provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
				provider.getProFax(),provider.getCreatedBy(),provider.getCreationDate()};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public Provider getProviderById(String id) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps=null;
		Provider provider=null;
		String sql=" select * from smbms_provider where id=?";
		Object[]param={id};
		rs=BaseDao.query(sql, param);
		while(rs.next()){
			provider = new Provider();
			provider.setId(rs.getInt("id"));
			provider.setProCode(rs.getString("proCode"));
			provider.setProName(rs.getString("proName"));
			provider.setProDesc(rs.getString("proDesc"));
			provider.setProContact(rs.getString("proContact"));
			provider.setProPhone(rs.getString("proPhone"));
			provider.setProAddress(rs.getString("proAddress"));
			provider.setProFax(rs.getString("proFax"));
			provider.setCreatedBy(rs.getInt("createdBy"));
			provider.setCreationDate(rs.getTimestamp("creationDate"));
			provider.setModifyBy(rs.getInt("modifyBy"));
			provider.setModifyDate(rs.getTimestamp("modifyDate"));
		}
		return provider;
	}

	@Override
	public int modify(Provider provider) throws Exception {
		int count=0;
		PreparedStatement ps=null;
		String sql = "update smbms_provider set proName=?,proDesc=?,proContact=?," +
				"proPhone=?,proAddress=?,proFax=?,modifyBy=?,modifyDate=? where id = ? ";
		Object[] param = {provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
				provider.getProFax(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public int delpro(String id) {
		PreparedStatement ps=null;
		int count=0;
		String sql=" delete from smbms_provider where id=? ";
		Object[]param={id};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public List<Provider> getproviderList2() throws Exception {
		List<Provider>providerList=new ArrayList<Provider>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String sql=" select * from smbms_provider ";
		Object[]param={};
		rs=BaseDao.query(sql, param);
		while(rs.next()){
			Provider provider = new Provider();
			provider.setId(rs.getInt("id"));
			provider.setProCode(rs.getString("proCode"));
			provider.setProName(rs.getString("proName"));
			provider.setProDesc(rs.getString("proDesc"));
			provider.setProContact(rs.getString("proContact"));
			provider.setProPhone(rs.getString("proPhone"));
			provider.setProAddress(rs.getString("proAddress"));
			provider.setProFax(rs.getString("proFax"));
			provider.setCreationDate(rs.getTimestamp("creationDate"));
			providerList.add(provider);
		}
		BaseDao.closeAll(rs, ps, null);
		return providerList;
	}
}
