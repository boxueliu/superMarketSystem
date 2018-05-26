package cn.smbms.dao.role;

import java.util.List;

import cn.smbms.pojo.Role;

public interface RoleDao {

	/**
	 * 查询用户角色信息
	 * @return
	 */
	public List<Role>getRoleList() throws Exception;
}
