package com.adilmx.jee_servlet_jsp_app.controller;

import com.adilmx.jee_servlet_jsp_app.model.Product;
import com.adilmx.jee_servlet_jsp_app.service.ProductService;
import com.adilmx.jee_servlet_jsp_app.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "*.do")
public class ProductServlet extends HttpServlet {
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		productService = new ProductServiceImpl();
		productService.initCatalog();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the path of Servlet (ex : /index.do)
		String path = request.getServletPath();
		if (path.equals("/ProductServlet.do") && request.getMethod().equals("GET")) {
			List<Product> products = productService.findAll();
			request.setAttribute("products", products);
			request.getRequestDispatcher("views/products.jsp").forward(request, response);
		} else if (path.equals("/search.do") && request.getMethod().equals("GET")) {
			String keyword = request.getParameter("keyWord");
			List<Product> products = productService.findByName(keyword);
			request.setAttribute("products", products);
			request.setAttribute("keyword", keyword);
			request.getRequestDispatcher("views/products.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			Product product = new Product();
			product.setLib(request.getParameter("lib"));
			product.setPrice(Double.valueOf(request.getParameter("price")));
			Product productSaved = productService.save(product);
			List<Product> products = productService.findAll();
			request.setAttribute("products", products);
			request.getRequestDispatcher("views/products.jsp").forward(request, response);
		} else if (path.equals("/delete.do")) {
			int result = productService.delete(Long.valueOf(request.getParameter("id")));

			if (result == 1) {
				List<Product> products = productService.findAll();
				request.setAttribute("products", products);
				String keyWord = request.getParameter("keyWord");
				// response.sendRedirect(request.getContextPath()+"/search.do?keyWord="+keyWord)
				if (!keyWord.equals("")) {
					response.sendRedirect("search.do?keyWord=" + keyWord);
				} else {
					response.sendRedirect("ProductServlet.do");
				}

			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}

		} else if (path.equals("/edit.do")) {
			Product currentProduct = productService.findById(Long.valueOf(request.getParameter("id")));

			if (currentProduct != null) {
				request.setAttribute("currentProduct", currentProduct);
				request.setAttribute("mode", "edit");
				List<Product> products = productService.findAll();
				request.setAttribute("products", products);
				request.getRequestDispatcher("views/products.jsp").forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}

		} else if (path.equals("/update.do") && request.getMethod().equals("POST")) {
			Product product = new Product();
			product.setId(Long.valueOf(request.getParameter("id")));
			product.setLib(request.getParameter("lib"));
			product.setPrice(Double.valueOf(request.getParameter("price")));
			Product productUpdated = productService.update(product);
			List<Product> products = productService.findAll();
			request.setAttribute("products", products);
			request.getRequestDispatcher("views/products.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
