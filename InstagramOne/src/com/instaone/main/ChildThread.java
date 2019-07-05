package com.instaone.main;

public class ChildThread extends Thread{
	InstagramApp instaApp = new InstagramApp();
	@Override
	public void run() {
		try {
		instaApp.unfollowFollowing();
		}catch(Exception e) {
			System.out.println("Exception in Unfollowing.");
		}
	}
}
