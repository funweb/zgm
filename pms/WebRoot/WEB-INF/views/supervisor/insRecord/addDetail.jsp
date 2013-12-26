<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加入库货物</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="${ctx }/js/validate/jquery.validate.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/admin/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/admin/theme1.css">

	<script type="text/javascript" src="${ctx }/js/jquery-1.6.1.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#myForm").validate();
		});
		
		function changeStyle(ob) {
			alert(ob.text);
			alert($("#style").text());
		}
		
		function changePurity(ob) {
			alert(ob.text);
		}
		
	</script>
	
  </head>
  
  <body>
    <form id="myForm" name="myForm" action="${ctx}/supervisor/insRecord/showDetailList" method="post">
    	<div id="content">
    		<div style="margin-bottom: 10px;padding: 5px 10px;" id="box">
    		<h3 id="adduser">添加入库货物</h3>
    		<br/>
    		<fieldset style="padding: 5px 10px;" id="personal">
    			<legend><h3>请输入相关信息</h3></legend>
    			<br/>
    				<table  cellpadding="0" cellspacing="0" width="100%"  class="list1">
					<tr>
						<td width="30%">
							款式大类:
						</td>
						<td width="70%">
							<select id="style" name="style.id" class="required" onchange="changeStyle(this)">
								<c:forEach items="${styleList}" var="style">
									<option value = "${style.id }">${style.name }</option>
								</c:forEach>
							</select>
							<input type="hidden" name="style.name" value="${styleList[0].name}">
						</td>
					</tr>
					<tr>
						<td width="30%">
							标明成色:
						</td>
						<td width="70%">
							<select name="pledgePurity.id" class="required" onchange="changePurity(this.value)">
								<c:forEach items="${pledgePurityList}" var="pledgePurity">
									<option value = "${pledgePurity.id }">${pledgePurity.name }</option>
								</c:forEach>
							</select>
							<input type="hidden" name="pledgePurity.name" value="${pledgePurityList[0].name}">
						</td>
					</tr>
					<tr>
						<td width="30%">
							标明规格重量（kg）:
						</td>
						<td width="70%">
							<input id="specWeight" name="specWeight" class="{required:true,number:true}" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							数量（件）:
						</td>
						<td width="70%">
							<input id="amount" name="amount" class="{required:true,number:true}" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							生产厂家:
						</td>
						<td width="70%">
							<input id="company" name="company" class="required" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							实际检测成色:
						</td>
						<td width="70%">
							<input id="checkPurity" name="checkPurity" class="required" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							实际检测重量规格（kg/件）：
						</td>
						<td width="70%">
							<input id="checkSpecWeight" name="checkSpecWeight" class="{required:true,number:true}" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							检测数量:
						</td>
						<td width="70%">
							<input id="checkAmount" name="checkAmount" class="{required:true,number:true}" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							检测重量（kg）:
						</td>
						<td width="70%">
							<input id="checkWeight" name="checkWeight" class="{required:true,number:true}" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					<tr>
						<td width="30%">
							检测方法:
						</td>
						<td width="70%">
							<select id="checkMethod" name="checkMethod">
			            		<option value="Spectrum" selected="selected">光谱法</option>
			            		<option value="Dissolve">溶金法</option>
			            	</select>
						</td>
					</tr>
					<tr>
						<td width="30%">
							备注:
						</td>
						<td width="70%">
							<input id="desc" name="desc" style="background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;"/>
						</td>
					</tr>
					</table>
    			<br/>
    		</fieldset>
    		<br/>
    		<div style="margin-bottom: 5px;padding: 3px;" align="center">
    				<input id="button1" type="submit" value="保存" style="cursor: pointer;font-weight: bold;margin-left: 8px;padding-right: 5px;width: 205px; background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;border: 1px solid #D9E6F0;"/>
    				<input id="button2" type="button" value="取消" onclick="javascript:history.back();" style="cursor: pointer;font-weight: bold;margin-left: 8px;padding-right: 5px;width: 205px;background: url('${ctx}/images/admin/images/form_blue.gif') repeat-x scroll left top #FFFFFF;border: 1px solid #D9E6F0;">
    		</div>
    	</div>
    	</div>
    </form>
  </body>
</html>