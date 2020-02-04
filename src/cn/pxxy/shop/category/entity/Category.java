package cn.pxxy.shop.category.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.pxxy.shop.categorysecond.entity.CategorySecond;

/**
 * 一级分类实体类
 * @author 严子江
 *
 */
public class Category implements Serializable{
	private Integer cid;
	private String cname;
	//一级分类存放二级分类集合
	private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", categorySeconds=" + categorySeconds + "]";
	}
}
