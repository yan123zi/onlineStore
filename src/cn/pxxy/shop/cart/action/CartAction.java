package cn.pxxy.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.pxxy.shop.cart.entity.Cart;
import cn.pxxy.shop.cart.entity.CartItem;
import cn.pxxy.shop.product.service.ProductService;

public class CartAction extends ActionSupport{
	private ProductService productService;
	private Integer pid;
	private int count;
	public void setCount(int count) {
		this.count = count;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public String addCartItem() {
		Cart cart=getCart();
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));
		cart.addCartItem(cartItem);
		return "cart";
	}
	public String removeCartItem() {
		Cart cart=getCart();
		cart.removeCart(pid);
		return "cart";
	}
	public String clearCart() {
		Cart cart=getCart();
		cart.clearCart();
		return "cart";
	}
	public String myCart() {
		return "cart";
	}
	public Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
