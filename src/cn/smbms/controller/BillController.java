package cn.smbms.controller;


import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
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
@RequestMapping("/bills")
public class BillController {

	@Resource
	private BillService billService;
	
	@Resource
	private ProviderService providerService;
	/**
	 * 模糊查询  并分页
	 * @param queryProductName
	 * @param queryProviderId
	 * @param queryIsPayment
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping("/billList")
	public String getBillList(@RequestParam(value="queryProductName",required=false)String queryProductName,
								@RequestParam(value="queryProviderId",required=false)String queryProviderId,
								@RequestParam(value="queryIsPayment",required=false)String queryIsPayment,
								@RequestParam(value="pageIndex",required=false)String pageIndex,
								Model model){
		List<Bill> billList=null;
		int _queryProviderId=0;
		int _queryIsPayment=0;
		int currentPageNo=1;//当前页
		int pageSize=Constants.pageSize;//设置页面容量
		if (queryProductName==null) {
			queryProductName="";
		}
		if(queryProviderId!=null &&!queryProviderId.equals("")){
			_queryProviderId=Integer.parseInt(queryProviderId);
		}
		if(queryIsPayment!=null &&!queryIsPayment.equals("")){
			_queryIsPayment=Integer.parseInt(queryIsPayment);
		}
		if(pageIndex!=null){
			currentPageNo=Integer.valueOf(pageIndex);
		}
		//总数量
		int totalCount=billService.getBillCount(queryProductName, _queryProviderId, _queryIsPayment);
		//总页数
		PageUtil pageUtil=new PageUtil();
		pageUtil.setCurrentPageNo(currentPageNo);
		pageUtil.setPageSize(pageSize);
		pageUtil.setTotalCount(totalCount);
		int totalPageCount=pageUtil.getTotalPageCount();
		//控制首尾页
		if (currentPageNo<1) {
			currentPageNo=1;
		}else if (currentPageNo>totalPageCount) {
			currentPageNo=totalPageCount;
		}
		billList=billService.getBillList(queryProductName, _queryProviderId, _queryIsPayment, currentPageNo, pageSize);
		model.addAttribute("billList", billList);
		List<Provider>providerList=null;
		providerList=providerService.getproviderList2();
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", queryProviderId);
		model.addAttribute("queryIsPayment", queryIsPayment);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "billlist";
	}
	/**
	 * 查看信息
	 * @param id
	 * @param model
	 * Created by mac on 18/4/19.
	 * @author boxueliu
	 * @return
	 */
	@RequestMapping(value="/billview/{id}",method=RequestMethod.GET)
	public String getBill(@PathVariable String id,Model model){
		Bill bill = billService.getBill(id);
		model.addAttribute(bill);
		return "billview";
	}

	/**
	 *跳转到添加页面
	 * @param bill
	 * @return
     */
	@RequestMapping(value="/toadd",method=RequestMethod.GET)
	public String toadd(@ModelAttribute("bill") Bill bill){
		return "billadd";
	}
	/**
	 * 添加
	 * @return
	 */
	@RequestMapping(value="/addbill",method=RequestMethod.POST)
	public String addbill(Bill bill,HttpSession session){
		bill.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		bill.setCreationDate(new Date());
		if(billService.add(bill)){
			return "redirect:/bills/billList";
		}
		return "billadd";
	}
	/**
	 * 供应商信息
	 * @return
	 */
	@RequestMapping(value="/getProviderlist",method=RequestMethod.GET)
	@ResponseBody
	public List<Provider> getProviderlist(){
		List<Provider> getproviderList2 = providerService.getproviderList2();
		return getproviderList2;
	}
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toupdate/{id}",method=RequestMethod.GET)
	public String toupdate(@PathVariable String id,Model model){
		Bill bill = billService.getBill(id);
		model.addAttribute(bill);
		return "billmodify";
	}
	/**
	 * 修改
	 * @param bill
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String modifyUserSave(Bill bill,HttpSession session){
		bill.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		bill.setModifyDate(new Date());
		if(billService.update(bill)){
			return "billlist";
		}
		return "billmodify";
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	@ResponseBody
	public String del(@RequestParam String id){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(!StringUtils.isNullOrEmpty(id)){
			if (billService.del(id)) {//删除成功
				resultMap.put("delResult", "true");
			}else{//删除失败
				resultMap.put("delResult", "false");
			}
		}else{
			resultMap.put("delResult", "notexit");
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 
	 * 判断订单是否存在
	 * @RequestMapping(value="/ucexist")
	public String billCodeIsExist(@RequestParam String billCode){
		HashMap<String, String>resultMap=new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(billCode)){
			resultMap.put("userCode", "exist");
		}else{
			Bill bill = billService.selectBillCodeExist(billCode);
			if(bill!=null){
				resultMap.put("userCode", "exist");
			}else{
				resultMap.put("userCode", "noexist");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}*/
}
