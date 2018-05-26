package cn.smbms.dao.user;

import java.sql.Connection;
import java.util.List;

import cn.smbms.pojo.User;

public interface UserDao {

	/**
	 * 用户登录
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User login(String userCode,String userPassword) throws Exception;
	/**
	 * 根据条件查询 用户列表 进行分页
	 * @param userName
	 * @param userRole
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<User>getUserList(String userName,int userRole,
			int currentPageNo, int pageSize)throws Exception;
	/**
	 * 查询用户记录数
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public int getUserCount(String userName,int userRole)throws Exception;
	/**
	 * 添加用户   需要事务处理
	 * @param user
	 * @return
	 */
	public int add(User user);
	/**
	 * 通过userCode获取User
	 * @param connection
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getLoginUser(String userCode)throws Exception;
	/**
	 * 通过id 查询用户
	 * @param id
	 * @return
	 */
	public User getUserById(String id)throws Exception;
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int modify(User user)throws Exception;
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int del(String id)throws Exception;
	/**
	 * 修改用户密码 
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public int updatepwd(Integer id,String pwd)throws Exception;
}
