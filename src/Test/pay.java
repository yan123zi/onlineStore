package Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import cn.pxxy.shop.utils.GetLoader;
import cn.pxxy.shop.utils.PaymentUtil;

public class pay {
	@Test
	public void fun() throws IOException {
		InputStream input=new GetLoader().getLoader("merchantInfo.properties");
		Properties prop=new Properties();
		prop.load(input);
		StringBuffer sb=new StringBuffer();
		sb.append(prop.getProperty("url"));
		String p0_Cmd="Buy";
		String p1_MerId=prop.getProperty("p1_MerId");
		String p2_Order="151888";
		String p3_Amt="0.01";
		String p4_Cur="CNY";
		String p5_Pid="";
		String p6_Pcat="";
		String p7_Pdesc="";
		String p8_Url="http://localhost:8080/Shop/order_back";
		String p9_SAF="";
		String pa_MP="";
		String pd_FrpId="PINGANBANK-NET";
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
		sb.append("pr_NeedResponse="+pr_NeedResponse).append("&");
		sb.append("hmac="+hmac).append("&");
		System.out.println(sb.toString());
	}
}
