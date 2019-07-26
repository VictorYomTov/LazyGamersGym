package com.test;

import com.model.Activity;
import com.model.Aerobic;
import com.model.Anaerobic;
import com.model.User;
import com.model.api.GymDaoException;
import com.model.api.HibernateGymDAO;

public class mainTesting {
	public static void main(String[] args) throws GymDaoException {
		System.out.println("Testing new creation of user: vikotal.");
		HibernateGymDAO hib = HibernateGymDAO.getInstance();
		User vikotal = hib.createUser(new User("vikotal", "123456"));
		System.out.println("Verifying user creation in DB: " + vikotal.toString());
		System.out.println("Adding 2 activities");
		Activity activity1 = new Activity(Aerobic.RunningOnTreadmill, null, 0, 0, 40, vikotal.getId());
		Activity activity2 = new Activity(null, Anaerobic.Squat, 3, 10, 0, vikotal.getId());
		System.out.println("Activties Added");
		hib.createActivity(activity1);
		hib.createActivity(activity2);
		System.out.println("Printing the activities: ");
		System.out.println(activity1.toString());
		System.out.println(activity2.toString());
		System.out.println("Testing activity update: ");
		activity1.setAerobic(Aerobic.RunningOnTreadmill);
		hib.updateActivity(activity1);
		System.out.println(activity1.toString());
		System.out.println("Deleting 2 activities:");
		hib.removeActivity(activity1);
		hib.removeActivity(activity2);
		System.out.println("Getting Username " + vikotal.getUsername() + " And Password: " + vikotal.getPassword());
		System.out.println("Checking reply of false password:" + hib.loginUser("Vikotal", "1111"));
		System.out.println("Checking reply of true password :" + hib.loginUser("vikotal", "123456"));

		System.out.println("Trying to edit user and password");
		vikotal.setUsername("viko");
		vikotal.setPassword("2222");
		vikotal = hib.updateUser(vikotal);
		System.out.println(vikotal.toString());
		System.out.println("Testing user delete");
		hib.removeUser(vikotal);
		System.out.println("Test Was a Success. ");
	}
}
