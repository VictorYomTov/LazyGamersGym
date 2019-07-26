package com.model.api;

import com.model.Activity;
import com.model.User;
import java.util.List;

/**
 * list of methods to implement in HibernateGymDAO Class
 */
public interface IGymDAO {

	/**
	 * Get user by username.
	 *
	 * @param username user's username
	 * @return the user with the given username
	 */
	User getUserByUsername(final String username) throws GymDaoException;

	/**
	 * Check user's email and password.
	 *
	 * @param username user's username
	 * @param password user's password
	 * @return true if user credentials are ok
	 */
	boolean loginUser(final String username, final String password) throws GymDaoException;

	/**
	 * Create a user.
	 *
	 * @param user the user to create
	 * @return the created user
	 */
	User createUser(final User user) throws GymDaoException;

	/**
	 * Update a user.
	 *
	 * @param user the user to update.
	 * @return the updated user
	 */
	User updateUser(User user) throws GymDaoException;

	/**
	 * Remove a user.
	 *
	 * @param user the user to remove
	 */
	void removeUser(final User user) throws GymDaoException;

	/**
	 * Create activity.
	 *
	 * @param activity the activity to create
	 * @return the created activity
	 */
	Activity createActivity(Activity activity) throws GymDaoException;

	/**
	 * Update a user.
	 *
	 * @param activity the user to update.
	 * @return the updated activity
	 */
	Activity updateActivity(Activity activity) throws GymDaoException;

	/**
	 * Remove activity.
	 *
	 * @param activity the activity to remove
	 */
	void removeActivity(final Activity activity) throws GymDaoException;

	/**
	 * Get user by username.
	 *
	 * @param userId user's username
	 * @return the array of activities for the given username
	 */
	List <Activity> getAllUserActivities(final long userId) throws GymDaoException;

	/**
	 * Check in the DB if username is already exist
	 * @param username wanted username
	 * @return true if exist , false if not
	 */
	public boolean isUsernameExist(String username) throws GymDaoException;

	/**
	 * get user by id
	 * @param id of the wanted user
	 * @return wanted user
	 * @throws GymDaoException
	 */
	public User getUserById(long id) throws GymDaoException;
}
