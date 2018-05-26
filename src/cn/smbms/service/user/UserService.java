package cn.smbms.service.user;

import java.util.List;

import cn.smbms.pojo.User;

public interface UserService {
/**
 * 用户登录
 * @param userCode
 * @return
 */
	public User login(String userCode,String userPassword);
/**
 * 根据条件查询 用户列表  并分页
 * @param queryUserName
 * @param queryUserRole
 * @param currentPageNo
 * @param pageSize
 * @return
 */
	public List<User>getUserList(String queryUserName,int queryUserRole,int currentPageNo,int pageSize);
	/**
	 * 查询用户记录数
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getUserCount(String queryUserName,int queryUserRole);
	/**
	 * 添加用户  需要事务处理
	 * @param user
	 * @return
	 */
	public boolean add(User user);
	/**
	 * 通过id查询用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(String id);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean modify(User user);
	/**
	 * 根据id 删除用户
	 * @param id
	 * @return
	 */
	public boolean del(String id);
	/**
	 * 根据userCode查询出User
	 * @param userCode
	 * @return
	 */
	public User selectUserCodeExist(String userCode);
	/**
	 * 修改用户密码
	 * @param integer
	 * @param pwd
	 * @return
	 */
	public boolean updatepwd(Integer integer,String pwd);
}
