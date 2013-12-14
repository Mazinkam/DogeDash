package com.alicode.game.dogedash;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * FriendStore stores the logged in users
 * 
 * @author
 * 
 */
public class FriendStore {
	private Set<String> friendsList = new HashSet<String>();
	private static FriendStore instance;
	private static final Logger logger = Logger.getLogger(FriendServlet.class
			.getCanonicalName());

	/**
	 * Dummy constructor for Singleton Class implementation
	 */
	private FriendStore() {
	}

	/**
	 * Gives the singleton object of FriendStore class
	 * 
	 * @return FriendStore object
	 */
	public static FriendStore getInstance() {
		if (instance == null)
			instance = new FriendStore();
		return instance;
	}

	/**
	 * Adds the new user
	 * 
	 * @param user
	 *            The user to be added in the set
	 */
	void addNewFriend(String user) {
		logger.log(Level.INFO, "User {0} is added to the list", user);
		friendsList.add(user);
	}

	/**
	 * Removes a new user from the set
	 * 
	 * @param user
	 *            The user that needs to be removed from the set
	 */
	void removeFriend(String user) {
		logger.log(Level.INFO, "User {0} is removed from the list", user);
		friendsList.remove(user);
	}

	/**
	 * Gives the complete set of users sorted
	 * 
	 * @return The TreeSet object of users (String)
	 */
	Set<String> getFriends() {
		logger.log(Level.INFO, "Users sorted and the set returned");
		return new TreeSet<String>(friendsList);
	}
}