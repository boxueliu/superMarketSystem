package cn.smbms.dao.role;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Role;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository("roleDaoImpl")
public class RoleDaoImpl implements RoleDao {

	@Override
	public List<Role> getRoleList() throws Exception {
		List<Role>roleList=new ArrayList<Role>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=" select * from smbms_role ";
		Object[] param={};
		rs=BaseDao.query(sql, param);
		while(rs.next()){
			Role role=new Role();
			role.setId(rs.getInt("id"));
//			role.setRoleCode(rs.getString("roleCode"));
			role.setRoleName(rs.getString("roleName"));
			roleList.add(role);
		}
		BaseDao.closeAll(rs, ps, null);
		return roleList;
	}

}
