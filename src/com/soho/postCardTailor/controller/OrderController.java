package com.soho.postCardTailor.controller;

import com.soho.postCardTailor.business.IOrderBusiness;
import com.soho.postCardTailor.business.IPostCardBusiness;
import com.soho.postCardTailor.business.ISizeBusiness;
import com.soho.postCardTailor.exception.SizeFormatWrongException;
import com.soho.postCardTailor.pojo.Operator;
import com.soho.postCardTailor.pojo.order.Order;
import com.soho.postCardTailor.pojo.order.OrderDTO;
import com.soho.postCardTailor.pojo.order.OrderHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * 订单相关操作
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Resource
    ISizeBusiness sizeBusiness;
    @Resource
    IOrderBusiness orderBusiness;
    //    @Resource
//    IPostCardDAO postCardDAO;
    @Resource
    IPostCardBusiness postCardBusiness;

    /**
     * 获取新订单页面
     *
     * @param model 页面所需Model
     * @return 页面
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newOrder_GET(Model model, HttpSession session) {
        if (session.getAttribute("uuid") == null) {
            session.setAttribute("uuid", UUID.randomUUID().toString());//生成一个随机数，防止重复提交
        }
        model.addAttribute("sizes", sizeBusiness.getAll());
        return "order/order_new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newOrder_POST(OrderDTO orderDTO, HttpSession session, String uuid) throws SizeFormatWrongException {
        Order order = OrderHelper.fromOrderDTO(orderDTO);
        if (uuid.equals(session.getAttribute("uuid"))) {//判断当前订单没有提交
            sizeBusiness.insert(order.getSize());//将尺寸存到数据库中，以备以后使用
            order.setOperator((Operator) session.getAttribute("operator"));//设置操作员
            orderBusiness.insert(order);//写入数据库
            session.removeAttribute("uuid");//删除uuid,防止再次提交
        }
        return "redirect:detail/" + order.getId() + "/upload";
    }

    @RequestMapping(value = "detail/{orderId}/upload")
    public String upload(@PathVariable("orderId") Integer orderId, Model model) {
        Order order = orderBusiness.findById(orderId);
        System.out.println(order);
        model.addAttribute("order", OrderHelper.fromOrder(order));
        model.addAttribute("uploadPath", "../order/detail/" + orderId + "/upload");
        System.out.println(OrderHelper.fromOrder(order));
        return "postCard_upload";
    }

    @RequestMapping(value = "detail/{orderId}/upload", method = RequestMethod.POST)
    public void upload(@PathVariable("orderId") Integer orderId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行此过程");
        FileItemFactory itemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(itemFactory);
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            //遍历fileItems，如果是文件，执行保存操作
            fileItems.stream().filter(fileItem -> !fileItem.isFormField()).forEach(fileItem -> postCardBusiness.insert(request.getServletContext().getRealPath("postCard"), orderId, fileItem));
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
}
