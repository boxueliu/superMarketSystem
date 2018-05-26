package cn.smbms.dao.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderDao {
	/**
	 * 根据条件查询供应商列表
	 * @param proCode
	 * @param proName
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Provider>getproviderList(String proCode,String proName,int currentPageNo,int pageSize)throws Exception;
	/**
	 * 根据条件查询供应商记录数
	 * @param proCode
	 * @param proName
	 * @return
	 */
	public int getproviderCount(String proCode,String proName)throws Exception;
	/**
	 * 添加用户   需要事务处理
	 * @param provider
	 * @return
	 */
	public int add(Provider provider)throws Exception;
	/**
	 * 通过id 查询用户
	 * @param id
	 * @return
	 */
	public Provider getProviderById(String id)throws Exception;
	/**
	 * 修改用户信息
	 * @param provider
	 * @return
	 */
	public int modify(Provider provider)throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delpro(String id);
	/**
	 * 查询所有的供应商
	 * @return
	 * @throws Exception
	 */
	public List<Provider> getproviderList2()throws Exception;
}
