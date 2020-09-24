package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

@WebServlet("/doRegister")
public class DoRegister extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public DoRegister() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
	
	
	CustomerService service = (CustomerService) CustomerService.getInstance();
	Customer customer = new Customer();
	
	String page;
	
	if(id==null || password==null) {
		page = "/view/error.jsp";
	}
	else {	
		customer.setId(id);
		customer.setPassword(password);
		customer.setName(name);
		customer.setGender(gender);
		customer.setEmail(email);

		service.addCustomer(customer);
		page = "/view/registerSuccess.jsp";
		request.setAttribute("customer", customer);


	}
	RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	dispatcher.forward(request, response);
	}
	
}
