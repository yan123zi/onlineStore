package cn.pxxy.shop.product.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.category.service.CategoryService;
import cn.pxxy.shop.product.entity.Product;
import cn.pxxy.shop.product.service.ProductService;
import cn.pxxy.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();//用于接收数据的模型驱动
	private ProductService productService;
	//接收分类的cid
	private Integer cid;
	private CategoryService categoryService;
	private int page;
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getCid() {
		return cid;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//根据商品的id查询商品
	public String findByPid() {
		product=productService.findByPid(product.getPid());
		return "productPage";
	}
	//根据分类的cid查询商品
	public String findByCid() {
		PageBean<Product> pageBean=productService.findByCid(cid,page);//根据cid和page分页查询（根据一级分类查询商品）
		//把分页的对象放到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//根据二级分类id查询商品
	public String findByCsid() {
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		//把分页的对象放到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
