package com.model;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Class Activity to describe the activity
 */
@Entity
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aerobic aerobic;
	private Anaerobic anaerobic;
	private int sets, reps, time;
	private Long userId, activityId;

	/**
	 * default constructor
	 */
	public Activity () {
	}

	/**
	 * Constructor for new activity
	 * 
	 * @param aerobic Enum aerobice for new activity
	 * @param aerobic Enum anaerobice for new activity
	 * @param sets    num of sets for new activity, 0 in case of aerobic activity
	 * @param reps    num of reps for new activity, 0 in case of aerobic activity
	 * @param userId  which username make the activity
	 * @param time    Time of aerobic activity , is 0 in case of anaerobic activity
	 */
	public Activity (Aerobic aero, Anaerobic anaero, int sets, int reps, int time, long userId) {
		this.setAerobic(aero);
		this.setAnaerobic(anaero);
		this.setSets(sets);
		this.setReps(reps);
		this.setTime(time);
		this.setUserId(userId);
	}

	/**
	 * @return the aerobic
	 */
	public Aerobic getAerobic() {
		return aerobic;
	}

	/**
	 * @param aerobic the aerobic to set
	 */
	public void setAerobic(Aerobic aerobic) {
		this.aerobic = aerobic;
	}

	/**
	 * @return the anaerobic
	 */
	public Anaerobic getAnaerobic() {
		return anaerobic;
	}

	/**
	 * @param anaerobic the anaerobic to set
	 */
	public void setAnaerobic(Anaerobic anaerobic) {
		this.anaerobic = anaerobic;
	}

	/**
	 * @return the sets
	 */
	public int getSets() {
		return sets;
	}

	/**
	 * @param sets the sets to set
	 */
	public void setSets(int sets) {
		this.sets = sets;
	}

	/**
	 * @return the reps
	 */
	public int getReps() {
		return reps;
	}

	/**
	 * @param reps the reps to set
	 */
	public void setReps(int reps) {
		this.reps = reps;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the activityId
	 */
	public Long getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	/* (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return "Activity [" + (aerobic != null ? "aerobic=" + aerobic + ", " : "")
				+ (anaerobic != null ? "anaerobic=" + anaerobic + ", " : "") + (sets != 0 ? "sets=" + sets + ", " : "")
				+ (reps != 0 ? "reps=" + reps + ", " : "") + (time != 0 ? "time=" + time + ", " : "")
				+ (userId != null ? "userId=" + userId + ", " : "") + (activityId != null ? "activityId=" + activityId : "")
				+ "]";
	}

}
