package cn.pxxy.shop.order.adminAction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.order.entity.Order;
import cn.pxxy.shop.order.entity.OrderItem;
import cn.pxxy.shop.order.service.OrderService;
import cn.pxxy.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order=new Order();
	private OrderService orderService;
	private int page;//接收page的参数
	public void setPage(int page) {
		this.page = page;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//订单分页的方法
	public String findAllByPage() {
		PageBean<Order> pageBean=orderService.findOrderByPage(page);//分页查询
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
	}
	//根据订单id查询订单项
	public String findOrderItem() {
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//修改订单状态
	public String update() {
		Order cuOrder=orderService.findByOid(order.getOid());
		cuOrder.setState(3);
		orderService.update(cuOrder);
		return "updateSuccess";
	}
	//根据订单状态查询订单列表
	public String findByState() {
		PageBean<Order> pageBean=orderService.findOrderByState(page,Integer.parseInt(ServletActionContext.getRequest().getParameter("state")));
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
	}
}
