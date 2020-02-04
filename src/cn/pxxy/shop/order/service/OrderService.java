package cn.pxxy.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.pxxy.shop.order.dao.OrderDao;
import cn.pxxy.shop.order.entity.Order;
import cn.pxxy.shop.order.entity.OrderItem;
import cn.pxxy.shop.utils.PageBean;

/**
 * 订单的业务逻辑层
 * @author 严子江
 *
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	//保存订单
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}
	//我的订单业务层
	public PageBean findByPageUid(Integer uid, int page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		int limit=5;
		pageBean.setLimit(limit);
		int totalCount=0;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}
	public PageBean<Order> findOrderByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		//设置每页显示数
		int limit=10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=orderDao.findOrderCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findOrderList(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderItem(oid);
	}
	public PageBean<Order> findOrderByState(int page, Integer state) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		//设置每页显示数
		int limit=10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=orderDao.findOrderCountByState(state);
		System.out.println(totalCount);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findOrderListByState(state,begin,limit);
		System.out.println(list);
		pageBean.setList(list);
		return pageBean;
	}
	
}
