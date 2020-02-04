package cn.pxxy.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.pxxy.shop.category.entity.Category;
import cn.pxxy.shop.category.service.CategoryService;
import cn.pxxy.shop.product.entity.Product;
import cn.pxxy.shop.product.service.ProductService;
/**
 * 首页访问的action
 * @author 严子江
 *
 */
public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	/**
	 * 执行访问首页的方法
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		List<Category> list=categoryService.findAll();//返回商品类目表的所有内容（一级菜单)
		ActionContext.getContext().getSession().put("clist", list);//将一级分类加到session中
		//查询热门商品
		List<Product> hotlist=productService.findHot();
		//保存到值栈中去
		ActionContext.getContext().getValueStack().set("hlist", hotlist);
		//查询最新商品
		List<Product> newlist=productService.findNew();
		//保存到值栈中去
		ActionContext.getContext().getValueStack().set("nlist", newlist);
		return "index";
	}
}
