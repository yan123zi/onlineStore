package cn.pxxy.shop.order.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.cart.entity.Cart;
import cn.pxxy.shop.cart.entity.CartItem;
import cn.pxxy.shop.order.entity.Order;
import cn.pxxy.shop.order.entity.OrderItem;
import cn.pxxy.shop.order.service.OrderService;
import cn.pxxy.shop.user.entity.User;
import cn.pxxy.shop.utils.GetLoader;
import cn.pxxy.shop.utils.PageBean;
import cn.pxxy.shop.utils.PaymentUtil;

/**
 *订单的实体类
 * 
 * @author 严子江
 * 
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象
		private Order order = new Order();

		public Order getModel() {
			return order;
		}

		// 接收支付通道编码:
		private String pd_FrpId;

		public void setPd_FrpId(String pd_FrpId) {
			this.pd_FrpId = pd_FrpId;
		}
		// 接收付款成功后的参数:
		private String r3_Amt;
		private String r6_Order;
		
		public void setR3_Amt(String r3_Amt) {
			this.r3_Amt = r3_Amt;
		}

		public void setR6_Order(String r6_Order) {
			this.r6_Order = r6_Order;
		}

		// 接收page
		private Integer page;

		public void setPage(Integer page) {
			this.page = page;
		}

		// 注入OrderService
		private OrderService orderService;

		public void setOrderService(OrderService orderService) {
			this.orderService = orderService;
		}

		// 生成订单的执行的方法:
		public String save() {

			// 调用Service完成数据库插入的操作:
			// Order order = new Order();
			// 设置订单的总金额:订单的总金额应该是购物车中总金额:
			// 购物车在session中,从session总获得购物车信息.
			Cart cart = (Cart) ServletActionContext.getRequest().getSession()
					.getAttribute("cart");
			if (cart == null) {
				this.addActionMessage("亲!您还没有购物!");
				return "msg";
			}
			order.setTotal(cart.getTotal());
			// 设置订单的状态
			order.setState(1); // 1:未付款.
			// 设置订单时间
			order.setOrdertime(new Date());
			// 设置订单关联的客户:
			User existUser = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			if (existUser == null) {
				this.addActionMessage("亲!您还没有登录!");
				return "msg";
			}
			order.setUser(existUser);
			// 设置订单项集合:
			for (CartItem cartItem : cart.getCartItems()) {
				// 订单项的信息从购物项获得的.
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setOrder(order);

				order.getOrderItems().add(orderItem);
			}
			orderService.save(order);
			// 清空购物车:
			cart.clearCart();

			// 页面需要回显订单信息:
			// 使用模型驱动了 所有可以不使用值栈保存了
			// ActionContext.getContext().getValueStack().set("order", order);

			return "saveOrder";
		}

		// 查询我的订单:
		public String findByUid() {
			// 获得用户的id.
			User existUser = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			// 获得用户的id
			Integer uid = existUser.getUid();
			// 根据用户的id查询订单:
			PageBean<Order> pageBean = orderService.findByPageUid(uid, page);
			// 将PageBean数据带到页面上.
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
			return "findByUid";
		}
		//根据oid查询订单
		public String findByOid() {
			order=orderService.findByOid(order.getOid());
			return "findByOidSuccess";
		}
		//付款
		public String payOrder() throws IOException {
			// 1.修改数据:
			Order currOrder = orderService.findByOid(order.getOid());
			currOrder.setAddr(order.getAddr());
			currOrder.setName(order.getName());
			currOrder.setPhone(order.getPhone());
			// 修改订单
			orderService.update(currOrder);
			//请求参数
			InputStream input=new GetLoader().getLoader("merchantInfo.properties");
			Properties prop=new Properties();
			prop.load(input);
			StringBuffer sb=new StringBuffer();
			sb.append(prop.getProperty("url"));
			String p0_Cmd="Buy";
			String p1_MerId=prop.getProperty("p1_MerId");
			String p2_Order=order.getOid().toString()+"_"+System.currentTimeMillis();;
			String p3_Amt="0.01";
			String p4_Cur="CNY";
			String p5_Pid="";
			String p6_Pcat="";
			String p7_Pdesc="";
			String p8_Url=prop.getProperty("p8_Url");
			String p9_SAF="";
			String pa_MP="";
			String pd_FrpId=this.pd_FrpId;
			String pr_NeedResponse="1";
			String keyValue=prop.getProperty("keyValue");
			String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
			System.out.println(hmac);
			sb.append("p0_Cmd="+p0_Cmd).append("&");
			sb.append("p1_MerId="+p1_MerId).append("&");
			sb.append("p2_Order="+p2_Order).append("&");
			sb.append("p3_Amt="+p3_Amt).append("&");
			sb.append("p4_Cur="+p4_Cur).append("&");
			sb.append("p5_Pid="+p5_Pid).append("&");
			sb.append("p6_Pcat="+p6_Pcat).append("&");
			sb.append("p7_Pdesc="+p7_Pdesc).append("&");
			sb.append("p8_Url="+p8_Url).append("&");
			sb.append("p9_SAF="+p9_SAF).append("&");
			sb.append("pa_MP="+pa_MP).append("&");
			sb.append("pd_FrpId="+pd_FrpId).append("&");
			sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sb.append("hmac="+hmac);
			ServletActionContext.getResponse().sendRedirect(sb.toString());
			return NONE;
		}
		//支付回调
		public String callBack() {
			//修改订单状态为2
			String oid=r6_Order.substring(0, r6_Order.indexOf("_"));
			Order currOrder = orderService.findByOid(Integer.parseInt(oid));
			currOrder.setState(2);
			orderService.update(currOrder);
			this.addActionMessage("支付成功!订单编号为:"+r6_Order+"付款金额为:"+r3_Amt);
			return "msg";
		}
		//修改订单状态
		public String updateState() {
			Order cuOrder=orderService.findByOid(order.getOid());
			cuOrder.setState(4);
			orderService.update(cuOrder);
			return "updateSuccess";
		}
}
