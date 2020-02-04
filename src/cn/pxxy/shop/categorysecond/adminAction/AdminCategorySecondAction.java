package cn.pxxy.shop.categorysecond.adminAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.category.entity.Category;
import cn.pxxy.shop.category.service.CategoryService;
import cn.pxxy.shop.categorysecond.entity.CategorySecond;
import cn.pxxy.shop.categorysecond.service.CategorySecondService;
import cn.pxxy.shop.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecondService categorySecondService=new CategorySecondService();
	private CategoryService categoryService=new CategoryService();
	private CategorySecond categorySecond=new CategorySecond();
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	public String findAllByPage() {
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSuccess";
	}
	//跳转到添加页面
	public String addPage() {
		List<Category> clist=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("clist", clist);
		return "addPageSuccess";
	}
	//二级分类的保存
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//删除二级分类的方法
	public String delete() {
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//编辑二级分类
	public String edit() {
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> clist=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("clist", clist);
		return "editSuccess";
	}
	//修改二级分类
	public String update() {
		System.out.println("========"+categorySecond.getCategory().getCid()+"=========");
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
