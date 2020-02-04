package cn.pxxy.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.pxxy.shop.category.entity.Category;
/**
 * 商品类目的持久化层
 * @author 严子江
 *
 */
public class CategoryDao extends HibernateDaoSupport{
	/**
	 * 查询商品类目表所有内容的查询
	 * @return
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql="from Category";
		return (List<Category>) this.getHibernateTemplate().find(hql);
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
	}

	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return  this.getHibernateTemplate().get(Category.class, cid);
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
	}
}
