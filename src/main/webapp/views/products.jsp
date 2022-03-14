<%@ page import="java.util.List"%>
<%@ page import="com.adilmx.jee_servlet_jsp_app.model.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
List<Product> products = (List<Product>) request.getAttribute("products");
String keyword = request.getAttribute("keyword") != null ? (String) request.getAttribute("keyword") : "";
String mode = request.getAttribute("mode") != null ? (String) request.getAttribute("mode") : "";
Product currentProduct = request.getAttribute("currentProduct") != null ? (Product) request.getAttribute("currentProduct") : new Product();
%>
<html>
<head>
<title>PRODUCTS</title>
<%--
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">--%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h5>SEARCH</h5>
		<form method="get" action="search.do" style="width: fit-content" class="form-inline d-flex flex-column ">
			<div class="d-flex">
			<!-- <label>THE KEY WORD :</label> -->
			<input value="<%=keyword%>"
				name="keyWord"
				class="form-controller ml-1"
				placeholder="KeyWord">
			</div>
			<button class="btn btn-success mt-1">SEARCH</button>
		</form>
		<h5>SAVE</h5>
		<form method="post" action="save.do" style="width: fit-content" class="form-inline d-flex flex-column ">
			<div class="d-flex">
				<!-- <label>THE NAME :</label> -->
				<input
					name="lib"
					class="form-controller ml-1"
					placeholder="Name">
			</div>
			<div class="d-flex mt-1">
				<!-- <label>THE PRICE :</label> -->
				<input
					name="price"
					class="form-controller ml-1"
					placeholder="Price">
			</div>
			<button class="btn btn-success mt-1">SAVE</button>
		</form>
		<h2>ALL PRODUCTS</h2>
		<form action="update.do?<%=currentProduct.getId()%>" method="post">
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>PRICE</th>
					<th>ACTION</th>
				</tr>
			</thead>
			<tbody>
			<%
				for (Product product : products) {
				%>
			<%if(mode.equalsIgnoreCase("edit") && product.getId().equals(currentProduct.getId())) {%>
				
				<tr>
					<td>
					<%=currentProduct.getId()%>
					<input
					name="id"
					class="form-controller ml-1"
					value="<%=currentProduct.getId()%>"
					hidden="true"></td>
					<td><input
					name="lib"
					class="form-controller ml-1"
					placeholder="Name"
					value="<%=currentProduct.getLib()%>"></td>
					<td><input
					name="price"
					class="form-controller ml-1"
					placeholder="Price"
					value="<%=currentProduct.getPrice()%>"></td>
					<td>
					<button class="btn btn-success mt-1">UPDATE</button>
					</td>
				</tr>
				<%}else{ %>
				<tr>
					<td><%=product.getId()%></td>
					<td><%=product.getLib()%></td>
					<td><%=product.getPrice()%></td>
					<td>
					<a class="btn btn-danger" href="delete.do?id=<%=product.getId()%>&keyWord=<%=keyword%>">DELETE</a>
					<a class="btn btn-success" href="edit.do?id=<%=product.getId()%>">EDIT</a>
					</td>
				</tr>
				<%} %>
				<%} %>
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>
