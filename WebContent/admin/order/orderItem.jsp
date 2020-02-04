<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table>
	<s:iterator var="orderItem" value="list">
	<tr><td><s:property value="#orderItem.count"/></td>
	<td><s:property value="#orderItem.subtotal"/>￥</td>
	<td><img alt="商品图片" width="40" height="45" src="<s:property value="#orderItem.product.image"/>"></td></tr>
	</s:iterator>
</table>