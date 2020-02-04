package cn.pxxy.shop.utils;

import java.util.List;

public class PageBean<T> {
	private int page;//当前页数
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private int limit;//每页显示记录数
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	private List<T> list;//数据集合
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", limit=" + limit
				+ ", list=" + list + "]";
	}
}
