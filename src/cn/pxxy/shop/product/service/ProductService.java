package cn.pxxy.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.pxxy.shop.product.dao.ProductDao;
import cn.pxxy.shop.product.entity.Product;
import cn.pxxy.shop.utils.PageBean;

/**
 * 商品业务层代码
 * @author 严子江
 *
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//热门商品查询
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}
	//最新商品查询
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}
	//根据商品id查询商品详情
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}
	//根据一级分类的cid分页查询商品
	public PageBean<Product> findByCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);//每页显示记录数
		int totalCount=0;
		totalCount=productDao.findCountByCid(cid);//从数据库中查询该cid下的总记录数
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=(totalCount/limit)+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByCidPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		// TODO Auto-generated method stub
				PageBean<Product> pageBean=new PageBean();
				pageBean.setPage(page);
				int limit=8;
				pageBean.setLimit(limit);//每页显示记录数
				int totalCount=0;
				totalCount=productDao.findCountByCsid(csid);//从数据库中查询该cid下的总记录数
				pageBean.setTotalCount(totalCount);
				int totalPage=0;
				if(totalCount%limit==0) {
					totalPage=totalCount/limit;
				}else {
					totalPage=(totalCount/limit)+1;
				}
				pageBean.setTotalPage(totalPage);
				//每页显示的数据集合
				//从哪开始
				int begin=(page-1)*limit;
				List<Product> list=productDao.findByCsidPage(csid,begin,limit);
				pageBean.setList(list);
				return pageBean;
	}
	public PageBean<Product> findAllByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean();
		pageBean.setPage(page);
		int limit=10;
		pageBean.setLimit(limit);//每页显示记录数
		int totalCount=0;
		totalCount=productDao.findProductCount();//从数据库中查询总记录数
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=(totalCount/limit)+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByProductPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}
	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}
}
