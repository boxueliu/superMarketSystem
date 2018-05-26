package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.utils.Constants;
import cn.smbms.utils.PageUtil;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Resource
	private ProviderService providerService;
	
	@RequestMapping("/providerlist")
	public String getproviderList(@RequestParam(value="queryProCode",required=false)String queryProCode,
								@RequestParam(value="queryProName",required=false)String queryProName,
								@RequestParam(value="pageIndex",required=false)String pageIndex,
								Model model){
		List<Provider>providerList=null;
		int currentPageNo=1;//当前页
		int pageSize=Constants.pageSize;//设置页面容量
		if (StringUtils.isNullOrEmpty(queryProCode)) {
			queryProCode="";
		}
		if (StringUtils.isNullOrEmpty(queryProName)) {
			queryProName="";
		}
		if(pageIndex!=null){
			currentPageNo=Integer.valueOf(pageIndex);
		}
		int totalCount=providerService.getproviderCount(queryProCode, queryProName);//总数量
		//总页数
		PageUtil pageUtil=new PageUtil();
		pageUtil.setCurrentPageNo(currentPageNo);
		pageUtil.setPageSize(pageSize);
		pageUtil.setTotalCount(totalCount);
		int totalPageCount=pageUtil.getTotalPageCount();
		//控制首页和尾页
		if (currentPageNo<1) {
			currentPageNo=1;
		}else if (currentPageNo>totalPageCount) {
			currentPageNo=totalPageCount;
		}
		providerList=providerService.getproviderList(queryProCode, queryProName, currentPageNo, pageSize);
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("queryProName", queryProName);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "providerlist";
	}
	/**
	 * 跳转到添加页面
	 * @param provider
	 * @return
	 */
	@RequestMapping(value="/proadd",method=RequestMethod.GET)
	public String add(@ModelAttribute("provider")Provider provider ){
		System.out.println("+++++++++++++++++++++++++++++++");
		return "provideradd";
	}
	
	/**
	 * 添加
	 * @param provider
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addSave",method=RequestMethod.POST)
	public String addSave( Provider provider,HttpSession session){
		System.out.println("——————————————————————————————————");
//		provider.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		provider.setCreationDate(new Date());
        provider.setCreatedBy(123);
		if (providerService.add(provider)) {
			return "redirect:/provider/providerlist";//重定向
		}
		return "provideradd";
	}
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/providermodify/{id}",method=RequestMethod.GET)
	public String getUserById(@PathVariable String id,Model model){
		Provider provider=providerService.getProviderById(id);
		model.addAttribute(provider);
		return "providermodify";
	}
	/**
	 * 修改
	 * @param provider
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/modifyproviderSave",method=RequestMethod.POST)
	public String modifyUserSave(Provider provider,HttpSession session){
		provider.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		provider.setModifyDate(new Date());
		if(providerService.modify(provider)){
			return "redirect:/provider/providerlist";
		}
		return "providermodify";
	}
	/**
	 * 查看
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/proview/{id}",method=RequestMethod.GET)
	public String view(@PathVariable String id,Model model){
		Provider provider = providerService.getProviderById(id);
		model.addAttribute(provider);
		return "providerview";
	}
	/**
	 * 删除
	 * Created by mac on 18/4/19.
	 * @author boxueliu
	 * @return
	 */
	@RequestMapping(value="/delpro",method=RequestMethod.GET)
	@ResponseBody
	public Object delpro(@RequestParam String proid){
		HashMap<String, String>resultMap=new HashMap<String, String>();
		if(!StringUtils.isNullOrEmpty(proid)){
			 int delpro = providerService.delpro(proid);
			 if(delpro==0){//删除成功
				 resultMap.put("delResult", "true");
			 }else if(delpro==-1){//删除失败
				 resultMap.put("delResult", "false");
			 }else if(delpro>0){//该供应商下有订单，不能删除，返回订单数
				 resultMap.put("delResult", String.valueOf(delpro));
			 }
			}else{
				resultMap.put("delResult", "notexit");
			}
		return JSONArray.toJSONString(resultMap);
	} 
	
}
