package com.model.api;

import com.model.Activity;
import com.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.*;
import java.util.List;

public class HibernateGymDAO implements IGymDAO {

	private static HibernateGymDAO ourInstance;

	private SessionFactory getSession() {
		return SessionFactoryDao.getSession();
	}

	public synchronized static HibernateGymDAO getInstance() {
		if (ourInstance == null) {
			ourInstance = new HibernateGymDAO();
		}
		return ourInstance;
	}

	private HibernateGymDAO () {
	}

	/**
	 * Get user by username.
	 *
	 * @param username user's username
	 * @return the user with the given username
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) throws GymDaoException {
		System.out.println("HGD->GUBU->39");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from User user where user.username = :username");
			query.setParameter("username", username);
			System.out.println("HGD->GUBU->47");
			transaction.commit();
			System.out.println("HGD->GUBU->49");
			System.out.println(query);
			List <User> users = query.list();
			System.out.println("HGD->GUBU->51");
			return (users != null && !users.isEmpty()) ? users.get(0) : null;

		}
		catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new GymDaoException("Exception in getUserByUsername", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->getUserByUsername->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Check user's email and password.
	 *
	 * @param username user's userName
	 * @param password user's password
	 * @return true if user credentials are ok
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean loginUser(String username, String password) throws GymDaoException {
		System.out.println("HGD->LU->74");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();

			// create a query to check if the user exist in the users table
			Query query = session.createQuery("from User user where user.username = :username AND user.password = :password");
			// change the query parameters to the function params
			query.setParameter("username", username);
			query.setParameter("password", password);
			transaction.commit();
			List <User> users = query.list();

			return (users != null && !users.isEmpty());

		}
		catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new GymDaoException("Exception in login", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->loginUser->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Create a user.
	 *
	 * @param user the user to create
	 * @return the created user or null if already exist
	 */
	@Override
	public User createUser(User user) throws GymDaoException {
		System.out.println("HGD->CU->112");
		Session session = null;
		Transaction transaction = null;
		try {
			System.out.println("HGD->CU->116");
			if (this.isUsernameExist(user.getUsername())) {
				System.out.println("HGD->CU->118");
				System.out.println("HGD->CreateUser->Username already exists");
				return null;
			}
			System.out.println("126");
			session = getSession().openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			System.out.println("HGD->CU->126");
			return user;
		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->createUser->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in create user", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->createUser->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Update a user.
	 *
	 * @param user the user to update.
	 * @return the updated user
	 */
	@Override
	public User updateUser(User user) throws GymDaoException {
		System.out.println("HGD->UU->154");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			return user;
		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->updateUser->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in update user", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->updateUser->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Remove a user.
	 *
	 * @param user the user to remove
	 */
	@Override
	public void removeUser(User user) throws GymDaoException {
		System.out.println("HGD->RU->186");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->removeUser->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in update user", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->removeUser->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Create activity.
	 *
	 * @param activity the activity to create
	 * @return the created activity
	 */
	@Override
	public Activity createActivity(Activity activity) throws GymDaoException {
		System.out.println("HGD->CA->218");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			session.save(activity);
			transaction.commit();
			return activity;
		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->createActivity->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in create activity", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->createActivity->ClosingSession");
				session.close();
			}
		}

	}

	/**
	 * Update a user.
	 *
	 * @param activity the user to update.
	 * @return the updated activity
	 */
	@Override
	public Activity updateActivity(Activity activity) throws GymDaoException {
		System.out.println("HGD->UA->252");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			session.update(activity);
			transaction.commit();
			return activity;
		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->updateActivity->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in update activity", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->updateActivity->ClosingSession");
				session.close();
			}
		}

	}

	/**
	 * Remove activity.
	 *
	 * @param activity the activity to remove
	 */
	@Override
	public void removeActivity(Activity activity) throws GymDaoException {
		System.out.println("HGD->RA->285");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			session.delete(activity);
			transaction.commit();
		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->removeActivity->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in remove user", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->removeActivity->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Get user by username.
	 *
	 * @param userId user username
	 * @return the array of activities for the given username
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List <Activity> getAllUserActivities(long userId) throws GymDaoException {
		System.out.println("HGD->GAUA->318");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Activity activity where activity.userId = :userId");
			// change the query parameters to the function params
			query.setParameter("userId", userId);
			transaction.commit();
			List <Activity> activities = query.list();
			return activities;

		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->getAllUserActivities->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in getAllUserActivities", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->getAllUserActivities->ClosingSession");
				session.close();
			}
		}
	}

	/**
	 * Check in the DB if username is already exist
	 *
	 * @param username wanted username
	 * @return true if exist , false if not
	 */
	@Override
	public boolean isUsernameExist(String username) throws GymDaoException {
		System.out.println("HGD->IUE->355");
		User user = this.getUserByUsername(username);
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * get user by id
	 *
	 * @param id of the wanted user
	 * @return wanted user
	 * @throws GymDaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(long id) throws GymDaoException {
		System.out.println("HGD->GUBI->373");
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from User user where user.id = :id");
			// change the query parameters to the function params
			query.setParameter("id", id);
			transaction.commit();
			List <User> user = query.list();
			return (user != null && !user.isEmpty()) ? user.get(0) : null;

		}
		catch (HibernateException he) {
			if (transaction != null) {
				System.out.println("HGD->getUserById->CommitingRollback");
				transaction.rollback();
			}
			throw new GymDaoException("Exception in getUserById", he);
		}
		finally {
			if (session != null) {
				System.out.println("HGD->getUserById->ClosingSession");
				session.close();
			}
		}
	}
}
