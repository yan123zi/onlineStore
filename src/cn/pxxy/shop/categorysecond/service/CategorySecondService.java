package cn.pxxy.shop.categorysecond.service;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.pxxy.shop.categorysecond.dao.CategorySecondDao;
import cn.pxxy.shop.categorysecond.entity.CategorySecond;
import cn.pxxy.shop.utils.PageBean;
@Transactional
public class CategorySecondService{
	private CategorySecondDao categorySecondDao=new CategorySecondDao();

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	//业务层查询二级分类分页
	public PageBean<CategorySecond> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		//设置当前页数
		pageBean.setPage(page);
		//设置总记录数
		int totalCount=0;
		totalCount=categorySecondDao.findCound();
		pageBean.setTotalCount(totalCount);
		//设置每页显示数目
		int limit=10;
		pageBean.setLimit(limit);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findAll(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(categorySecond);
	}
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.fidAllCg();
	}
}
