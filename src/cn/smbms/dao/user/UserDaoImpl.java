package cn.smbms.dao.user;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.User;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Override
	public User login(String userCode,String userPassword) throws Exception{
		ResultSet rs=null;
		PreparedStatement ps=null;
		User user=null;
		String sql=" select * from smbms_user where userCode=? and userPassword=? ";
		Object[]param={userCode,userPassword};
		rs=BaseDao.query(sql, param);
		while(rs.next()){
			user=new User();
			user.setId(rs.getInt("id"));
			user.setUserCode(rs.getString("userCode"));
			user.setUserName(rs.getString("userName"));
			user.setUserPassword(rs.getString("userPassword"));
			user.setGender(rs.getInt("gender"));
			user.setBirthday(rs.getDate("birthday"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
//			user.setUserRole(rs.getInt("userRole"));
			user.setCreatedBy(rs.getInt("createdBy"));
			user.setCreationDate(rs.getTimestamp("creationDate"));
			user.setModifyBy(rs.getInt("modifyBy"));
			user.setModifyDate(rs.getTimestamp("modifyDate"));
		}
		BaseDao.closeAll(rs, ps, null);
		return user;
	}

	@Override
	public List<User> getUserList(String userName, int userRole,
			int currentPageNo, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<User>userList=new ArrayList<User>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		List<Object>param=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole=r.id ");
		if (!StringUtils.isNullOrEmpty(userName)) {
			sql.append(" and userName like ? ");
			param.add( "%"+userName+"%");
		}
		if (userRole>0) {
			sql.append(" and userRole = ? ");
			param.add(userRole);
		}
		sql.append(" order by creationDate DESC limit ?,? ");
		currentPageNo=(currentPageNo-1)*pageSize;
		param.add(currentPageNo);
		param.add(pageSize);
		rs=BaseDao.query(sql.toString(), param.toArray());
		while(rs.next()){
			User user=new User();
			user.setId(rs.getInt("id"));
			user.setUserCode(rs.getString("userCode"));
			user.setUserName(rs.getString("userName"));
			user.setGender(rs.getInt("gender"));
			user.setBirthday(rs.getDate("birthday"));
			user.setPhone(rs.getString("phone"));
			user.setUserRole(rs.getInt("userRole"));
			user.setUserRoleName(rs.getString("userRoleName"));
			userList.add(user);
		}
		BaseDao.closeAll(rs, ps, null);
		return userList;
	}

	@Override
	public int getUserCount(String userName, int userRole) throws Exception {
		int count=0;
		ResultSet rs=null;
		PreparedStatement ps=null;
		List<Object>param=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id ");
		if (!StringUtils.isNullOrEmpty(userName)) {
			sql.append(" and userName like ? ");
			param.add( "%"+userName+"%");
		}
		if (userRole>0) {
			sql.append(" and userRole = ? ");
			param.add(userRole);
		}
		rs=BaseDao.query(sql.toString(), param.toArray());
		if(rs.next()){
			count=rs.getInt("count");
		}
		BaseDao.closeAll(rs, ps, null);
		return count;
	}

	@Override
	public int add(User user) {
		PreparedStatement ps=null;
		int count=0;
		String sql = "insert into smbms_user (userCode,userName,userPassword," +
				"userRole,gender,birthday,phone,address,creationDate,createdBy) " +
				"values(?,?,?,?,?,?,?,?,?,?)";
		Object[] param = {user.getUserCode(),user.getUserName(),user.getUserPassword(),
						user.getUserRole(),user.getGender(),user.getBirthday(),
						user.getPhone(),user.getAddress(),user.getCreationDate(),user.getCreatedBy()};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public User getUserById(String id) throws Exception{
		ResultSet rs=null;
		PreparedStatement ps=null;
		User user=null;
		String sql=" select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.id=? and u.userRole=r.id ";
		Object[]param={id};
		rs=BaseDao.query(sql, param);
		while(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUserCode(rs.getString("userCode"));
			user.setUserName(rs.getString("userName"));
			user.setUserPassword(rs.getString("userPassword"));
			user.setGender(rs.getInt("gender"));
			user.setBirthday(rs.getDate("birthday"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			user.setUserRole(rs.getInt("userRole"));
			user.setCreatedBy(rs.getInt("createdBy"));
			user.setCreationDate(rs.getTimestamp("creationDate"));
			user.setModifyBy(rs.getInt("modifyBy"));
			user.setModifyDate(rs.getTimestamp("modifyDate"));
			user.setUserRoleName(rs.getString("userRoleName"));
		}
		BaseDao.closeAll(rs, ps, null);
		return user;
	}

	@Override
	public int modify(User user) throws Exception{
		PreparedStatement ps=null;
		int count=0;
		String sql = "update smbms_user set userName=?,"+
				"gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? where id = ? ";
		Object[] param = {user.getUserName(),user.getGender(),user.getBirthday(),
				user.getPhone(),user.getAddress(),user.getUserRole(),user.getModifyBy(),
				user.getModifyDate(),user.getId()};
			count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public int del(String id) throws Exception {
		int count=0;
		PreparedStatement ps=null;
		String sql=" delete from smbms_user where id=? ";
		Object[] param={id};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		return count;
	}

	@Override
	public User getLoginUser(String userCode) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
			String sql = "select * from smbms_user where userCode=?";
			Object[] param= {userCode};
			rs = BaseDao.query(sql, param);
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserName(rs.getString("userName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setGender(rs.getInt("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUserRole(rs.getInt("userRole"));
				user.setCreatedBy(rs.getInt("createdBy"));
				user.setCreationDate(rs.getTimestamp("creationDate"));
				user.setModifyBy(rs.getInt("modifyBy"));
				user.setModifyDate(rs.getTimestamp("modifyDate"));
			}
			BaseDao.closeAll(rs, ps, null);
			return user;
	}

	@Override
	public int updatepwd(Integer id, String pwd) throws Exception {
		PreparedStatement ps=null;
		int count=0;
		String sql=" update smbms_user set userPassword= ? where id = ? ";
		Object[]param={pwd,id};
		count=BaseDao.CUD(sql, param);
		BaseDao.closeAll(null, ps, null);
		System.out.println(count);
		return count;
	}
}
