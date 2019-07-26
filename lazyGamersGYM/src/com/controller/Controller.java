package com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Activity;
import com.model.Aerobic;
import com.model.Anaerobic;
import com.model.User;
import com.model.api.GymDaoException;
import com.model.api.HibernateGymDAO;

/**
 * general controller that execute the call from user request and take care to
 * response
 */
public class Controller {

	// ========================== User Methods
	// ========================================

	/**
	 * create new user in database
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws GymDaoException
	 * @throws ServletException
	 * @throws IOException
	 */
	public void createUser(HttpServletRequest request, HttpServletResponse response, String str)
			throws GymDaoException, ServletException, IOException {
		System.out.println("Controller->CU->17");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);
		String errorStr = "Unknown Error";
		try {
			System.out.println("Controller->CU->23");
			HibernateGymDAO hgd = HibernateGymDAO.getInstance();
			if (password.length() > 0 && username != "") {
				System.out.println("Controller->CU->26");
				User newUser = new User(username, password);
				if (hgd.createUser(newUser) != null) { // Checking if the user already exist
					System.out.println("Controller->CU->32");
					session.setAttribute("username", username);
					dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
					dispatcher.forward(request, response);
					System.out.println("Controller->CU->36");
				} else {
					errorStr = "Username Already Exist";
					System.out.println("Controller->CreateUser->" + errorStr);
					request.setAttribute("errorType", errorStr);
					dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage2.jsp");
					dispatcher.forward(request, response);
				}
			} else {// error
				errorStr = "Username or password is empty";
				System.out.println("Controller->CreateUser->" + errorStr);
				request.setAttribute("errorType", errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage2.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) { // Unknown Error
			request.setAttribute("errorType", errorStr);
			System.out.println("Unknown Error");
			dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage2.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * check if the user and the password is already in the database and making
	 * login
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws GymDaoException
	 * @throws ServletException
	 * @throws IOException
	 */

	public void loginUser(HttpServletRequest request, HttpServletResponse response, String str)
			throws GymDaoException, ServletException, IOException {
		System.out.println("Controller->LU->55");
		RequestDispatcher dispatcher = null;
		String password = (String) request.getParameter("password");
		String username = (String) request.getParameter("username");
		System.out.println(password);
		System.out.println(username);
		String errorStr = "Unknown Error";

		try {
			if (password.length() > 0 && username != "") {
				System.out.println("Controller->LU->58");
				HibernateGymDAO hgd = HibernateGymDAO.getInstance();
				if (hgd.loginUser(username, password) == true) { // fine
					HttpSession session = request.getSession();
					System.out.println("Controller->LU->61");
					session.setAttribute("username", username);
					dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
					System.out.println("Controller->LU->70");
					dispatcher.forward(request, response);
					System.out.println("Controller->LU->72");
				} else { // Error User doesn't exist
					errorStr = "Username or Password are incorrect !";
					request.setAttribute("errorType", errorStr);
					System.out.println("Controller->LoginUser->" + errorStr);
					dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
				}
			} else { // Error Username or password is empty
				errorStr = "Username or password is empty";
				System.out.println("Controller->LoginUser->" + errorStr);
				request.setAttribute("errorType", errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) { // Error Go back after Logout
			errorStr = "Go back after Logout";
			request.setAttribute("errorType", errorStr);
			System.out.println("Unknown Error || Go back after logout");
			dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * disconnect the user
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logoutUser(HttpServletRequest request, HttpServletResponse response, String str)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = null;
		request.getSession().invalidate();
		dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * changing password for current user , and checking if the password he enter is
	 * equals to the old password
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void changePassword(HttpServletRequest request, HttpServletResponse response, String str)
			throws GymDaoException, ServletException, IOException {
		String oldPassword = (String) request.getParameter("password");
		String newPassword = (String) request.getParameter("password1");
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);
		String errorStr = "Unknown Error";
		try {
			System.out.println("Controller->CU->23");
			HibernateGymDAO hgd = HibernateGymDAO.getInstance();
			String currentSessionUsername = (String) session.getAttribute("username");
			User currentSessionUser = hgd.getUserByUsername(currentSessionUsername);
			String username = (String) currentSessionUser.getUsername();
			System.out.println("UN - > " + username);
			System.out.println("OLDPASSWORD - > " + oldPassword);
			System.out.println("NEW PASSWORD - > " + newPassword);

			if (currentSessionUser.getPassword().toString().equals(oldPassword)) {
				System.out.println("EQUALS");
				currentSessionUser.setPassword(newPassword);
				hgd.updateUser(currentSessionUser);
				System.out.println("Success to change password");
				System.out.println(currentSessionUser.getPassword().toString());
				dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
				System.out.println("Controller->CP->135");
				dispatcher.forward(request, response);
				System.out.println("Finish");
			} else { // password isnt correct
				errorStr = "password isnt correct";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->ChangePassword->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) { // Unknown Error
			request.setAttribute("errorType", errorStr);
			System.out.println("Unknown Error");
			dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage2.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}

	}

	// ======================================== Activity Methods
	// ========================================

	/**
	 * creating activity in database
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws GymDaoException
	 * @throws ServletException
	 * @throws IOException
	 */

	public void createActivity(HttpServletRequest request, HttpServletResponse response, String str)
			throws GymDaoException, ServletException, IOException {
		System.out.println("Controller->CA->103");
		RequestDispatcher dispatcher = null;
		HibernateGymDAO hgd = HibernateGymDAO.getInstance();
		HttpSession session = request.getSession(true);
		String errorStr = "Unknown Error";
		String currentSessionUsername = (String) session.getAttribute("username");
		User currentSessionUser = hgd.getUserByUsername(currentSessionUsername);
		System.out.println("Controller->CA->" + str);
		switch (str) {// checking which activity is it
		case "RunningOnTreadmill":
			Integer time1 = Integer.parseInt(request.getParameter("time"));
			Activity newActivity1 = new Activity(Aerobic.RunningOnTreadmill, null, 0, 0, time1,
					currentSessionUser.getId());
			if (hgd.createActivity(newActivity1) != null) { // Checking if the activity already exist
				dispatcher = request.getServletContext().getRequestDispatcher("/AerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else { // error
				errorStr = "Couldn't create the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->createActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "StairStepper":
			Integer time2 = Integer.parseInt(request.getParameter("time"));
			Activity newActivity2 = new Activity(Aerobic.StairStepper, null, 0, 0, time2, currentSessionUser.getId());
			if (hgd.createActivity(newActivity2) != null) { // Checking if the activity already exist
				dispatcher = request.getServletContext().getRequestDispatcher("/AerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else { // error
				errorStr = "Couldn't create the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->createActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "StationaryBike":
			Integer time3 = Integer.parseInt(request.getParameter("time"));
			Activity newActivity3 = new Activity(Aerobic.StationaryBike, null, 0, 0, time3, currentSessionUser.getId());
			if (hgd.createActivity(newActivity3) != null) { // Checking if the activity already exist
				dispatcher = request.getServletContext().getRequestDispatcher("/AerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else { // error
				errorStr = "Couldn't create the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->createActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "Squat":
			Integer set1 = Integer.parseInt(request.getParameter("set"));
			Integer rep1 = Integer.parseInt(request.getParameter("rep"));
			Activity newActivity4 = new Activity(null, Anaerobic.Squat, set1, rep1, 0, currentSessionUser.getId());
			// I think the if is pointless here.
			if (hgd.createActivity(newActivity4) != null) { // Checking if the activity already exist
				dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else { // error
				errorStr = "Couldn't create the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->createActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "ChestPress":
			Integer set2 = Integer.parseInt(request.getParameter("set"));
			Integer rep2 = Integer.parseInt(request.getParameter("rep"));
			Activity newActivity5 = new Activity(null, Anaerobic.ChestPress, set2, rep2, 0, currentSessionUser.getId());
			if (hgd.createActivity(newActivity5) != null) { // Checking if the activity already exist
				dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else { // error
				errorStr = "Couldn't create the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->createActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "ShoulderPress":
			Integer set3 = Integer.parseInt(request.getParameter("set"));
			Integer rep3 = Integer.parseInt(request.getParameter("rep"));
			Activity newActivity6 = new Activity(null, Anaerobic.ShoulderPress, set3, rep3, 0,
					currentSessionUser.getId());
			if (hgd.createActivity(newActivity6) != null) { // Checking if the activity already exist
				dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else { // error
				errorStr = "Couldn't create the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->createActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		default:
			errorStr = "Couldn't find activity"; // the activity isnt exist
			request.setAttribute("errorType", errorStr);
			System.out.println("Controller->createActivity->" + errorStr);
			dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

	/**
	 * remove activity in database
	 * 
	 * @param request
	 * @param response
	 * @param str      activityId
	 * @throws ServletException
	 * @throws IOException
	 * @throws GymDaoException
	 * @throws NumberFormatException
	 */
	public void removeActivity(HttpServletRequest request, HttpServletResponse response, String str)
			throws GymDaoException, ServletException, IOException {
		System.out.println("Controller->RA->232->The Start of RA");
		HibernateGymDAO hgd = HibernateGymDAO.getInstance();
		HttpSession session = request.getSession(true);
		RequestDispatcher dispatcher = null;
		String currentSessionUsername = (String) session.getAttribute("username");
		User currentSessionUser = hgd.getUserByUsername(currentSessionUsername);
		Long activityId = null;
		String activityIdstr = (String) request.getParameter("activityid");
		System.out.println("activity id:" + activityIdstr);
		activityId = Long.parseLong(activityIdstr);
		List<Activity> userActivities = new ArrayList<Activity>();
		userActivities = hgd.getAllUserActivities(currentSessionUser.getId());
		Activity activityToDelete = null;
		System.out.println("Controller->RA->272->finding the activity..");
		for (Activity tempActivity : userActivities)
			if (tempActivity.getActivityId().equals(activityId))
				activityToDelete = tempActivity;
		if (activityToDelete.getAerobic() != null) {
			hgd.removeActivity(activityToDelete);
			System.out.println("Controller->removeActivity->Success->Reports");
			request.setAttribute("alertMsg", "Remove data success");
			dispatcher = request.getServletContext().getRequestDispatcher("/ReportsAerobic.jsp");
		} else {
			hgd.removeActivity(activityToDelete);
			System.out.println("Controller->removeActivity->Success->Reports2");
			request.setAttribute("alertMsg", "Remove data success");
			dispatcher = request.getServletContext().getRequestDispatcher("/ReportsAnaerobic.jsp");
		}
		dispatcher.forward(request, response);

		System.out.println("Controller->RA->257->The End of RA");
	}

	/**
	 * update the activity in data base
	 * 
	 * @param request
	 * @param response
	 * @param str      activityId sent
	 * @throws ServletException
	 * @throws IOException
	 * @throws GymDaoException
	 */
	public void updateActivity(HttpServletRequest request, HttpServletResponse response, String str)
			throws GymDaoException, InvocationTargetException, ServletException, IOException {
		System.out.println("Controller->UA->261->The Start of UA");
		RequestDispatcher dispatcher = null;
		String errorStr = "Uknown Error";
		HibernateGymDAO hgd = HibernateGymDAO.getInstance();
		HttpSession session = request.getSession(true);
		String currentSessionUsername = (String) session.getAttribute("username");
		User currentSessionUser = hgd.getUserByUsername(currentSessionUsername);
		Long activityId = null;
		String activityIdstr = (String) request.getParameter("activityid");
		System.out.println("activity id:" + activityIdstr);
		activityId = Long.parseLong(activityIdstr);
		System.out.println("activity id:" + activityId.toString());
		List<Activity> userActivities = new ArrayList<Activity>();
		userActivities = hgd.getAllUserActivities(currentSessionUser.getId());
		Activity activityToUpdate = null;
		System.out.println("Controller->UA->272->finding the activity..");
		for (Activity tempActivity : userActivities)
			if (tempActivity.getActivityId().equals(activityId))
				activityToUpdate = tempActivity;
		System.out.println("activity:" + activityToUpdate.toString());
		if (activityToUpdate.getAerobic() != null) {
			System.out.println("Controller->UA->278->it's aerobic..");
			Integer time = Integer.parseInt((String) request.getParameter("time"));
			activityToUpdate.setTime(time);
			if (hgd.updateActivity(activityToUpdate) != null) { // SUCCESS
				System.out.println("Controller->updateActivity->Success");
				dispatcher = request.getServletContext().getRequestDispatcher("/AerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else {
				errorStr = "Couldn't update the activity"; // failed
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->updateActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
		} else if (activityToUpdate.getAnaerobic() != null) {
			Integer rep = Integer.parseInt((String) request.getParameter("rep"));
			Integer set = Integer.parseInt((String) request.getParameter("set"));
			activityToUpdate.setReps(rep);
			activityToUpdate.setSets(set);
			if (hgd.updateActivity(activityToUpdate) != null) {
				System.out.println("Controller->updateActivity->Success");
				dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicTraining.jsp");
				dispatcher.forward(request, response);
			} else {
				errorStr = "Couldn't update the activity";
				request.setAttribute("errorType", errorStr);
				System.out.println("Controller->updateActivity->" + errorStr);
				dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			errorStr = "Couldn't retrieve the activity";
			request.setAttribute("errorType", errorStr);
			System.out.println("Controller->createActivity->" + errorStr);
			dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}
		System.out.println("Controller->UA->281->The End of UA");
	}

	// ======================================== ROUTES Methods
	// ========================================
	/**
	 * navigate to login page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toLogin(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
		System.out.println("Controller->toLogin->Dispatched..");
	}

	/**
	 * navigate to registration page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toRegistration(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/Registration.jsp");
		dispatcher.forward(request, response);
		System.out.println("Controller->toRegistration->Dispatched..");
	}

	/**
	 * navigate to home page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */

	public void toHome(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request, response);
		System.out.println("Controller->toHome->Dispatched..");
	}

	/**
	 * navigate to aerobic reports page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */

	public void toReportsAerobic(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/ReportsAerobic.jsp");
		dispatcher.forward(request, response);
		System.out.println("Controller->toReportsAerobic->Dispatched..");
	}

	/**
	 * navigate to anaerobic reports page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toReportsAnaerobic(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/ReportsAnaerobic.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to aerobic training page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAerobicTraining(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AerobicTraining.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to anaerobic training page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAnaerobicTraining(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicTraining.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to exercise 1 (aerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAerobicEx1(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AerobicEx1.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to exercise 2 (aerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAerobicEx2(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AerobicEx2.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to exercise 3 (aerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */

	public void toAerobicEx3(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AerobicEx3.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to exercise 1 (anaerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAnaerobicEx1(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicEx1.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to exercise 2 (anaerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */

	public void toAnaerobicEx2(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicEx2.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to exercise 3 (anaerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */

	public void toAnaerobicEx3(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/AnaerobicEx3.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * navigate to graph (aerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 * @throws GymDaoException
	 */

	public void toGraphAerobic(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException, GymDaoException {
		RequestDispatcher dispatcher = null;
		if (request.getSession().getAttribute("username") != null) {
			System.out.println("Reports2->Start");
			HttpSession session = request.getSession(true);
			List<Activity> fullActivitiesReports = new ArrayList<Activity>();
			List<Activity> aerobicActivitiesReports = new ArrayList<Activity>();
			HibernateGymDAO hgd = HibernateGymDAO.getInstance();
			String userName = (String) request.getSession().getAttribute("username");
			System.out.println("Reports2->userName: " + userName);
			User curUser = hgd.getUserByUsername(userName);
			fullActivitiesReports = hgd.getAllUserActivities(curUser.getId());
			System.out.println("activitiesReports->: " + fullActivitiesReports.toString());
			if (aerobicActivitiesReports != null)
				for (Activity tempActivity : fullActivitiesReports) {
					System.out.println("tempActivity->: " + tempActivity.toString());
					if (tempActivity != null)
						if (tempActivity.getAerobic() != null) {
							System.out.println("Adding..");
							aerobicActivitiesReports.add(tempActivity);
							System.out.println("Added!");
						}
				}
			Integer sumAerobicEx1 = 0;
			Integer sumAerobicEx2 = 0;
			Integer sumAerobicEx3 = 0;
			for (Activity tempActivity : aerobicActivitiesReports) {
				if (tempActivity.getAerobic().toString().contains("RunningOnTreadmill"))
					sumAerobicEx1 += tempActivity.getTime();
				else if (tempActivity.getAerobic().toString().contains("StairStepper"))
					sumAerobicEx2 += tempActivity.getTime();
				else if (tempActivity.getAerobic().toString().contains("StationaryBike"))
					sumAerobicEx3 += tempActivity.getTime();
			}
			System.out.println("sumAerobicEx1 is: " + sumAerobicEx1);
			System.out.println("sumAerobicEx2 is: " + sumAerobicEx2);
			System.out.println("sumAerobicEx3 is: " + sumAerobicEx3);
			session.setAttribute("sumAerobicEx1", sumAerobicEx1);
			session.setAttribute("sumAerobicEx2", sumAerobicEx2);
			session.setAttribute("sumAerobicEx3", sumAerobicEx3);
			dispatcher = request.getServletContext().getRequestDispatcher("/GraphAerobic.jsp");
			dispatcher.forward(request, response);
			System.out.println("Controller->toGraphAerobic->Dispatched GRAPH..");
		} else {
			dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
			System.out.println("Controller->toGraphAerobic->Dispatched LOGIN..");
		}
	}

	/**
	 * navigate to graph (anaerobic) page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 * @throws GymDaoException
	 */

	public void toGraphAnaerobic(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException, GymDaoException {
		RequestDispatcher dispatcher = null;

		if (request.getSession().getAttribute("username") != null) {
			System.out.println("Reports2->Start");
			List<Activity> fullActivitiesReports = new ArrayList<Activity>();
			List<Activity> anaerobicActivitiesReports = new ArrayList<Activity>();
			HibernateGymDAO hgd = HibernateGymDAO.getInstance();
			String userName = (String) request.getSession().getAttribute("username");
			System.out.println("Reports2->userName: " + userName);
			User curUser = hgd.getUserByUsername(userName);
			fullActivitiesReports = hgd.getAllUserActivities(curUser.getId());
			System.out.println("activitiesReports->: " + fullActivitiesReports.toString());
			if (anaerobicActivitiesReports != null)
				for (Activity tempActivity : fullActivitiesReports) {
					System.out.println("tempActivity->: " + tempActivity.toString());
					if (tempActivity != null)
						if (tempActivity.getAnaerobic() != null) {
							System.out.println("Adding..");
							anaerobicActivitiesReports.add(tempActivity);
							System.out.println("Added!");
						}
				}

			dispatcher = request.getServletContext().getRequestDispatcher("/GraphAnaerobic.jsp");
			dispatcher.forward(request, response);
		} else {
			dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * navigate to change password page
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws ServletException
	 * @throws IOException
	 */

	public void toChangePassword(HttpServletRequest request, HttpServletResponse response, String str)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getServletContext().getRequestDispatcher("/ChangePassword.jsp");
		dispatcher.forward(request, response);
		System.out.println("Controller->toChangePassword->Dispatched..");
	}

}
