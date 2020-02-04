package cn.pxxy.shop.product.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.pxxy.shop.product.entity.Product;
import cn.pxxy.shop.utils.PageHibernateCallback;
/**
 * 商品持久层
 * @author 严子江
 *
 */
public class ProductDao extends HibernateDaoSupport{
	//首页上热门商品查询
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//查询热门的商品，条件就是is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list=(List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	//首页上最新商品查询
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list=(List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	//根据商品id查询商品
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	//查询该分类下所有商品数
	public int findCountByCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql, cid);
		if(list !=null&&list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//该分页显示的商品集合
	public List<Product> findByCidPage(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, new Object[] {cid}, begin, limit));
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	//根据二级分类查询商品数量
	public int findCountByCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql, csid);
		if(list !=null&&list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据二级分类查询商品集合
	public List<Product> findByCsidPage(Integer csid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, new Object[] {csid}, begin, limit));	
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	public int findProductCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
		if(list !=null&&list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	public List<Product> findByProductPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="from Product";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));	
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	//dao保存商品
	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}
	public void delete(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(product);
	}
	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}
}
