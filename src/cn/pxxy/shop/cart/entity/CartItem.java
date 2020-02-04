package cn.pxxy.shop.cart.entity;

import cn.pxxy.shop.product.entity.Product;

public class CartItem {
	private Product product;//购物项中商品信息
	private int count;//某种商品数量
	private double subtotal;//该商品小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return product.getShop_price()*count;
	}
}
