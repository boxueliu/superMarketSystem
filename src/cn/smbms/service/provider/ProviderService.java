package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderService {
	/**
	 *  根据条件查询供应商列表
	 * @param proCode
	 * @param proName
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	public List<Provider>getproviderList(String proCode,String proName,int currentPageNo,int pageSize);
	/**
	 * 根据条件查询供应商记录数
	 * @param proCode
	 * @param proName
	 * @return
	 */
	public int getproviderCount(String proCode,String proName);
	/**
	 * 增加供应商
	 * @param provider
	 * @return
	 */
	public boolean add(Provider provider);
	/**
	 * 通过proId获取Provider
	 * @param id
	 * @return
	 */
	public Provider getProviderById(String id);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean modify(Provider provider);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delpro(String id);
	/**
	 * 查询所有 供应商
	 * @return
	 */
	public List<Provider> getproviderList2();
}
