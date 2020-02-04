package cn.pxxy.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	//购物车属性
	//购物集合
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();
	//购物总计
	private double total;
	//购物车功能
	//清空购物车
	public void clearCart() {
		map.clear();
		total=0;
	}
	//删除购物项
	public void removeCart(Integer pid) {
		//将购物项移除购物车
		CartItem cartItem=map.remove(pid);
		//购物车总计减少
		total-=cartItem.getSubtotal();
	}
	//添加购物项
	public void addCartItem(CartItem cartItem) {
		Integer pid=cartItem.getProduct().getPid();
		CartItem _cartItem=map.get(pid);
		if(map.containsKey(pid)) {
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else {
			map.put(pid, cartItem);
		}
		total+=cartItem.getSubtotal();
	}
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public double getTotal() {
		return total;
	}
}
