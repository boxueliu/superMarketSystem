package cn.smbms.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.User;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public User login(String userCode,String userPassword) {
		Connection con=null;
		User user=null;
		try {
			con=BaseDao.getCon();
			user=userDao.login(userCode,userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return user;
	}

	@Override
	public List<User> getUserList(String queryUserName, int queryUserRole,
			int currentPageNo, int pageSize) {
		List<User>userList=null;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			userList=userDao.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return userList;
	}

	@Override
	public int getUserCount(String queryUserName, int queryUserRole) {
		int count=0;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			count=userDao.getUserCount(queryUserName, queryUserRole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return count;
	}

	@Override
	public boolean add(User user) {
		boolean flag=false;
		Connection con=null;
		
		try {
			con=BaseDao.getCon();
			con.setAutoCommit(false);//开启JDBC事务
			if(userDao.add(user)>0){
				flag=true;
				con.commit();
				System.out.println("添加成功");
			}else{
				System.out.println("添加失败");
			}
			
		} catch (Exception e) {
			try {
				System.out.println("rollback==================");
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
	public User getUserById(String id) {
		Connection con=null;
		User user=null;
		try {
			con=BaseDao.getCon();
			user=userDao.getUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return user;
	}

	@Override
	public boolean modify(User user) {
		boolean flag=false;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			if (userDao.modify(user)>0) {
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
	public boolean del(String id) {
		boolean flag=false;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			if (userDao.del(id)>0) {
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
	public User selectUserCodeExist(String userCode) {
		Connection con = null;
		User user = null;
		try {
			con = BaseDao.getCon();
			user = userDao.getLoginUser(userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);;
		}
		return user;
	}

	@Override
	public boolean updatepwd(Integer id, String pwd) {
		boolean flag=false;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			if (userDao.updatepwd(id, pwd)>0) {
				System.out.println(userDao.updatepwd(id, pwd));
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
