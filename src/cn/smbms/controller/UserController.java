package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.utils.Constants;
import cn.smbms.utils.PageUtil;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang.math.RandomUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	/**
	 * 用户登录
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String userCode,String userPassword,HttpServletRequest request,HttpSession session){
		User user = userService.login(userCode, userPassword);
//		if(user!=null){
//			session.setAttribute(Constants.USER_SESSION, user);
//			if (session.getAttribute(Constants.USER_SESSION)==null) {
//				return "redirect:/user/login";
//			}login
			return "frame";
//		}else{
//			request.setAttribute("error", "用户名或密码错误！");
//			return "login";
////			return "frame";
//		}
	}
	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/loginout")
	public String loginout(HttpSession session){
		session.removeAttribute(Constants.USER_SESSION);
		return "login";
	}
	
	
	/**
	 * 展示用户信息  并分页
	 * @param queryUserName
	 * @param queryUserRole
	 * @param pageIndex 当前页
	 * @param model
	 * @return
	 */
	@RequestMapping("/userlist")
	public String getUserList(@RequestParam(value="queryname",required=false)String queryUserName,
							@RequestParam(value="queryUserRole",required=false)String queryUserRole,
							@RequestParam(value="pageIndex",required=false)String pageIndex,Model model){
		List<User> userList=null;
		int _queryUserRole=0;//转换成int类型
		int currentPageNo=1;//当前页码
		int pageSize=Constants.pageSize;//设置页面容量
		if(queryUserName==null){
			queryUserName="";
		}
		if (queryUserRole!=null &&!queryUserRole.equals("")) {
			_queryUserRole=Integer.parseInt(queryUserRole);
		}
		if (pageIndex!=null) {
			currentPageNo=Integer.valueOf(pageIndex);
		}
		int totalCount=userService.getUserCount(queryUserName, _queryUserRole);//总数量
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
		userList=userService.getUserList(queryUserName, _queryUserRole, currentPageNo, pageSize);
		model.addAttribute("userList", userList);
		List<Role> roleList=null;
		roleList=roleService.getRoleList();
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("roleList", roleList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "userlist";
	}
	/**
	 * 跳转到添加页面
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/useradd",method=RequestMethod.GET)
	public String add(@ModelAttribute("user")User user ){
		return "useradd";
	}
	/*
	*//**
	 * 添加用户  单文件上传
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addSave",method=RequestMethod.POST)
	public String addSave( User user,HttpSession session,HttpServletRequest request){
//		String idPicPath=null;
//		//1.判断文件是否为空
//		if (!attach.isEmpty()) {
//			//2.定义上传目标路径
//			String path=request.getSession().getServletContext().getRealPath("statics/"+ File.separator+"uploaderfiles");
//			//3.获取原文件名
//			String oldFileName=attach.getOriginalFilename();
//			//4.获取源文件后缀名
//			String prefix= FilenameUtils.getExtension(oldFileName);
//			//5.文件大小
//			int filesize=500000;
//			if (attach.getSize()>filesize) {
//				request.setAttribute("uploadFileError", "上传大小不能超过500k");
//				return "useradd";
//			}else if(prefix.equalsIgnoreCase("jpg")
//						||prefix.equalsIgnoreCase("jpeg")
//						||prefix.equalsIgnoreCase("png")
//						||prefix.equalsIgnoreCase("pneg")){//6.文件格式
//				//生成新的文件名   当前系统时间+随机数+"_Presonal.jpg"
//				String fileName=System.currentTimeMillis()+
//						RandomUtils.nextInt(1000000)+"_Presonal.jpg";
//
//				File targetFile=new File(path, fileName);//路径，新的文件名
//				if(!targetFile.exists()){//判断是否存在
//					targetFile.mkdirs();//不存在 创建
//				}
//				try {//保存
//					attach.transferTo(targetFile);
//				}catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					request.setAttribute("uploadFileError", "上传失败！");
//					return "useradd";
//				}
//				idPicPath=path+File.separator+fileName;//记录上传文件的存储路径  往数据库更新
//			}else {
//				request.setAttribute("uploadFileError", "* 上传图片格式不正确！");
//				return "useradd";
//			}
//		}
		user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		user.setCreationDate(new Date());
//		user.setIdPicPath(idPicPath);
		if (userService.add(user)==true) {
			return "redirect:/user/userlist";
		}
		return "useradd";
	}

	/**
	 * 添加用户  多文件上传
	 * @param
	 * @return
	 */
//	@RequestMapping(value="/addSave",method=RequestMethod.POST)
//	public String addSave( User user,HttpSession session,HttpServletRequest request,
//			               @RequestParam(value="attachs" ,required=false) MultipartFile[] attachs){
//		String idPicPath=null;//证件照
//		String workPicPath=null;//工作照
//		String errorInfo=null;//错误信息
//		boolean flag=true;
//		String path=request.getSession().getServletContext().getRealPath("statics/"+File.separator+"uploaderfiles");//存储路径
//		for(int i=0;i<attachs.length;i++){
//			MultipartFile attach=attachs[i];
//			//1.判断文件是否为空
//			if (!attach.isEmpty()) {
//				if(i==0){
//					errorInfo="uploadFileError";
//				}else if(i==1){
//					errorInfo="uploadWpError";
//				}
//				//3.获取原文件名
//				String oldFileName=attach.getOriginalFilename();
//				//4.获取源文件后缀名
//				String prefix=FilenameUtils.getExtension(oldFileName);
//				//5.文件大小
//				int filesize=500000;
//				if (attach.getSize()>filesize) {
//					request.setAttribute(errorInfo, "上传大小不能超过500k");
//					flag=false;
//				}else if(prefix.equalsIgnoreCase("jpg")
//							||prefix.equalsIgnoreCase("jpeg")
//							||prefix.equalsIgnoreCase("png")
//							||prefix.equalsIgnoreCase("pneg")){//6.文件格式
//					//生成新的文件名   当前系统时间+随机数+"_Presonal.jpg"
//					String fileName=System.currentTimeMillis()+
//							RandomUtils.nextInt(1000000)+"_Presonal.jpg";
//					File targetFile=new File(path, fileName);//路径，新的文件名
//					if(!targetFile.exists()){//判断是否存在
//						targetFile.mkdirs();//不存在 创建
//					}
//					try {//保存
//						attach.transferTo(targetFile);
//					}catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						request.setAttribute(errorInfo, "上传失败！");
//						flag=false;
//					}
//					if(i==0){
//						idPicPath=path+File.separator+fileName;//记录上传文件的存储路径  往数据库更新
//					}else{
//						workPicPath=path+File.separator+fileName;//记录上传文件的存储路径  往数据库更新
//					}
//				}
//			}else {
//				request.setAttribute("uploadFileError", "* 上传图片格式不正确！");
//				flag=false;
//			}
//		}
//		if(flag){
//			user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
//			user.setCreationDate(new Date());
//			user.setIdPicPath(idPicPath);
//			user.setWorkPicPath(workPicPath);
//			if (userService.add(user)==true) {
//				return "redirect:/user/userlist";
//			}
//		}
//		return "useradd";
//	}
	@RequestMapping(value="/getrole",method=RequestMethod.GET)
	@ResponseBody
	public List<Role> getrole(){
		List<Role> roleList = roleService.getRoleList();
		return roleList;
	}
	
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usermodify",method=RequestMethod.GET)
	public String getUserById(@RequestParam String id,Model model){
		User user=userService.getUserById(id);
		model.addAttribute(user);
		return "usermodify";
	}
	/**
	 * 修改用户
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/modifyUserSave",method=RequestMethod.POST)
	public String modifyUserSave(User user,HttpSession session){
		user.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		user.setModifyDate(new Date());
		if(userService.modify(user)){
			return "redirect:/user/userlist";
		}
		return "usermodify";
	}
	/**
	 *根据id查看用户信息
	 * @param
	 * @param
	 * @PathVariable   REST风格  
	 * @return  
	 */
	/*@RequestMapping(value="/userview/{id}",method=RequestMethod.GET)
	public String view(@PathVariable String id,Model model){
		User user=userService.getUserById(id);
		System.out.println("@@@@@@@@@@@@@@@@");
		model.addAttribute(user);
		return "userview";
	}*/
	//判断用户是否重名
	@RequestMapping(value="/ucexist")
	@ResponseBody
	public Object userCodeIsExist(@RequestParam String userCode){
		HashMap<String, String>resultMap=new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(userCode)){
			resultMap.put("userCode", "exist");
		}else{
			User user = userService.selectUserCodeExist(userCode);
			if(user!=null){
				resultMap.put("userCode", "exist");
			}else{
				resultMap.put("userCode", "noexist");
			}
		}
			return JSONArray.toJSONString(resultMap);	
	}
	
/*	//异步刷新查看用户信息
	@RequestMapping(value="/view",method=RequestMethod.GET
			,produces={"applicaiton/json;charset=UTF-8"})
	@ResponseBody
	public Object view(@RequestParam String id){
		String cjson="";
		if(id==null ||id.equals("")){
			return "nodata";
		}else{
			try {
				User user = userService.getUserById(id);
				cjson=JSON.toJSONString(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";
			}
		}
		return cjson;
	}*/  //异步刷新查看用户信息 json 配置  WriteDateUseDateFormat
	@RequestMapping(value="/view",method=RequestMethod.GET)
	@ResponseBody
	public User view(@RequestParam String id){
		User user = userService.getUserById(id);
		return user;
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deluser",method=RequestMethod.GET)
	@ResponseBody
	public Object deluser(@RequestParam String id){
		HashMap<String, String>resultMap=new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(id)){
			resultMap.put("delResult", "notexist");
		}else{
			if(userService.del(id)){
				resultMap.put("delResult", "true");
			}else{
				resultMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequestMapping(value="/totoupdatepwd",method=RequestMethod.GET)
	public String toupdatepwd(){
		return "pwdmodify";
	}
	/**
	 * 更改密码
	 * @param
	 * @param newpassword
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatepwd",method=RequestMethod.POST)
	public String updatepwd(@RequestParam String newpassword,HttpSession session,HttpServletRequest request){
		//得到当前用户
		Object objuser = session.getAttribute(Constants.USER_SESSION);
		System.out.println(((User)objuser).getId());
		if (objuser!=null && !StringUtils.isNullOrEmpty(newpassword)) {
			if (userService.updatepwd(((User)objuser).getId(), newpassword)) {
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
			}else{
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
			}
		}else{
			request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
		}
		return "pwdmodify";
	}
	/**
	 * 判断旧密码
	 * @param oldpassword
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/oldpassword",method=RequestMethod.GET)
	@ResponseBody
	public Object oldpassword(@RequestParam String oldpassword,HttpSession session){
		Object objuser = session.getAttribute(Constants.USER_SESSION);
		HashMap<String, String>resultMap=new HashMap<String, String>();
		if (objuser==null) {//session过期
			resultMap.put("result", "sessionerror");
		}else if(StringUtils.isNullOrEmpty(oldpassword)){//旧密码输入为空
			resultMap.put("result", "error");
		}else{
			if(oldpassword.equals(((User)objuser).getUserPassword())){
				resultMap.put("result", "true");
			}else{//旧密码输入不正确
				resultMap.put("result", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
