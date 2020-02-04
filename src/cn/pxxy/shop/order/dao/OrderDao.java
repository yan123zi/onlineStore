package cn.pxxy.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.pxxy.shop.order.entity.Order;
import cn.pxxy.shop.order.entity.OrderItem;
import cn.pxxy.shop.utils.PageHibernateCallback;

/**
 * 订单的持久化层
 * @author 严子江
 *
 */
public class OrderDao extends HibernateDaoSupport{
	//dao层保存订单
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}

	// Dao层查询我的订单分页查询:统计个数
	public int findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	// Dao层查询我的订单分页查询:查询数据
	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}
	//查询订单数量
	public int findOrderCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size()>0&&list!=null) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//返回订单集合
	public List<Order> findOrderList(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="from Order";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return list;
	}
	//DAO层通过订单状态查询订单的个数
	public int findOrderCountByState(Integer state) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order where state=?";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql, state);
		if(list.size()>0&&list!=null) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据订单状态返回订单集合
	public List<Order> findOrderListByState(Integer state, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="from Order where state =?";
		Object[] o=new Object[] {state};
		List<Order> list=this.getHibernateTemplate().execute
				(new PageHibernateCallback<Order>(hql, o, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return list;
	}
	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=(List<OrderItem>) this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return list;
	}

	

	

}
