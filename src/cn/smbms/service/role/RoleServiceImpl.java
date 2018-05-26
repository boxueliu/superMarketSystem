package cn.smbms.service.role;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.role.RoleDao;
import cn.smbms.pojo.Role;
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public List<Role> getRoleList() {
		List<Role>roleList=null;
		Connection con=null;
		try {
			con=BaseDao.getCon();
			roleList=roleDao.getRoleList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, null, con);
		}
		return roleList;
	}
}
