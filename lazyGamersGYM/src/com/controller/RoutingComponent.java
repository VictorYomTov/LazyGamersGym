package com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoutingComponent
 */
//@WebServlet(name = "RoutingComponent")
@WebServlet("/controller/*")
public class RoutingComponent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * -
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public RoutingComponent() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RC->DG->36");
		// Echo client's request information

		System.out.println("Request URI: " + request.getRequestURI());

		String[] splitedUri = request.getRequestURI().split("/", 5);
		String controllerName = splitedUri[2];
		String actionName = splitedUri[3];
		String strAfterAction = splitedUri.length == 5 ? splitedUri[4] : null;
		System.out.println(controllerName + " " + actionName + (strAfterAction != null ? " " + strAfterAction : ""));
		// instantiating the controller class

		try {
			if (actionName.contains(".jsp")) {
				RequestDispatcher dispatcher = null;
				dispatcher = request.getServletContext().getRequestDispatcher("/" + actionName);
				dispatcher.forward(request, response);
				System.out.println("RC->DG->DISPATCH");
			} else {
				System.out.println("RC->DG->49");
				@SuppressWarnings("rawtypes")
				Class controllerClass = Class.forName(
						"com.controller." + controllerName.substring(0, 1).toUpperCase() + controllerName.substring(1));
				Object controller = controllerClass.getDeclaredConstructor().newInstance();
				Method method;
				method = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class,
						String.class);
				method.invoke(controller, request, response, strAfterAction);
			}
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("RC->DG->68");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
