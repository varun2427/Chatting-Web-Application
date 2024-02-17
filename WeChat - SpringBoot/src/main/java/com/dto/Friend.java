package com.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend {
	
	@Id
	@GeneratedValue
	@Column(name = "friend_id")
	private int friendId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "friend_user_id", nullable = false)
	private User friendUser;

	public Friend() {
		super();
	}

	public Friend(int friendId, User user, User friendUser) {
		super();
		this.friendId = friendId;
		this.user = user;
		this.friendUser = friendUser;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriendUser() {
		return friendUser;
	}

	public void setFriendUser(User friendUser) {
		this.friendUser = friendUser;
	}

	@Override
	public String toString() {
		return "Friend [friendId=" + friendId + ", user=" + user + ", friendUser=" + friendUser + "]";
	}
}
