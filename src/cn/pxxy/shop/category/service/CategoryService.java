package cn.pxxy.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.pxxy.shop.category.dao.CategoryDao;
import cn.pxxy.shop.category.entity.Category;
/**
 * 商品类目的业务层
 * @author 严子江
 *
 */
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	/**
	 * 返回商品类目里所有内容方法
	 * @return
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
}
