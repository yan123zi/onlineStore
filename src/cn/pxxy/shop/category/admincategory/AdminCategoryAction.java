package cn.pxxy.shop.category.admincategory;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.category.entity.Category;
import cn.pxxy.shop.category.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private CategoryService categoryService=new CategoryService();
	private Category category=new Category();
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	public String findAll() {
		List<Category> clist=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("clist", clist);
		return "findAllCategory";
	}
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}
	public String delete() {
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	public String edit() {
		category=categoryService.findByCid(category.getCid());
		return "findCidSuccess";
	}
	public String update() {
		categoryService.update(category);
		return "editSuccess";
	}
}
