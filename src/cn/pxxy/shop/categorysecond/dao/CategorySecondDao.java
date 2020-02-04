package cn.pxxy.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.pxxy.shop.categorysecond.entity.CategorySecond;
import cn.pxxy.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	public int findCound() {
		// TODO Auto-generated method stub
		String hql="select count(*) from CategorySecond";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findAll(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="from CategorySecond";
		List<CategorySecond> list= this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}

	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}

	public List<CategorySecond> fidAllCg() {
		// TODO Auto-generated method stub
		String hql="from CategorySecond";
		return (List<CategorySecond>) this.getHibernateTemplate().find(hql);
	}

}
