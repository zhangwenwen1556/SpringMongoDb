package com.junlenet.mongodb.demo.Thread;

public class UserDBThread implements Runnable {
	
	
	private User user;
	
	public UserDBThread() {
		super();
	}
	
	public UserDBThread(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(user);
	}

}
