package cn.pxxy.shop.product.adminAction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.categorysecond.entity.CategorySecond;
import cn.pxxy.shop.categorysecond.service.CategorySecondService;
import cn.pxxy.shop.product.entity.Product;
import cn.pxxy.shop.product.service.ProductService;
import cn.pxxy.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private ProductService productService;
	private CategorySecondService categorySecondService;
	private Product product=new Product();
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//文件上传所需的参数
	private File upload;//上传的文件
	private String uploadFileName;//上传文件名称
	private String uploadContextType;//接收文件上传的MIME类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	public String findAllByPage() {
		PageBean<Product> pageBean=productService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findPageSuccess";
	}
	public String addProduct() {
		List<CategorySecond> cslist=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		return "addPageSuccess";
	}
	public String save() throws IOException {
		product.setPdate(new Date());
		if(upload!=null) {
			//获得文件上传的磁盘绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			System.out.println(diskFile);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	//删除商品的方法
	public String delete() {
		//先查再删
		product=productService.findByPid(product.getPid());
		//删除上传的图片
		String path=product.getImage();
		if(path!=null) {
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file=new File(realPath);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		return "deleteSuccess";
	}
	//编辑商品的方法
	public String edit() {
		//根据商品id查询商品
		product=productService.findByPid(product.getPid());
		//查询所有的二级分类的集合
		List<CategorySecond> cslist=categorySecondService.findAll();
		//将数据保存到页面
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		return "editSuccess";
	}
	//修改商品的方法
	public String update() throws IOException {
		product.setPdate(new Date());
		//文件上传
		if(upload!=null) {
			//将原来的图片删除
			String path=product.getImage();
			File file=new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			//获得文件上传的磁盘绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		//修改商品的数据到数据库
		productService.update(product);
		return "updateSuccess";
	}
}
