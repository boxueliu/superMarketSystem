package cn.smbms.controller;

import cn.smbms.service.bill.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mac on 18/5/1.
 */
@Controller
@RequestMapping("/shop")
public class Shopcontroller {
    @Resource
    private BillService billService;
    @RequestMapping("/query")
    public  void query(HttpServletResponse response) throws IOException {
//        if(rfid==null){
//            return  "syserror";
//        }
         response.sendRedirect("show");
    }

//    @RequestMapping(value ="/order/{num}/{id}",method = RequestMethod.GET)
//    public boolean orderCommit(@PathVariable("id") String id,@PathVariable("num") String num){
//        if(num==null && id==null){
//            return false;
//        }
////        int num1 =Integer.valueOf(num);
////        BigDecimal num1 = BigDecimal.valueOf(num);
//        int orderId = Integer.valueOf(id);
//        Bill bill =new Bill();
//        bill.setId(orderId);
////        bill.setProductCount(num1);
//        billService.update(bill);
//        return true;
//    }
    @RequestMapping("/show")
    public String show(){
        return "shop";
    }

}
