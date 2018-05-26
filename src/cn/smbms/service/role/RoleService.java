package cn.smbms.service.role;

import java.util.List;

import cn.smbms.pojo.Role;



public interface RoleService {
/**
 * 查询用户角色
 * @return
 */
	public List<Role> getRoleList();
	
}
