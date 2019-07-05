package com.instaone.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InstagramApp extends Utility{

	ChromeOptions chromeOptions = new ChromeOptions();
	WebDriver driver = new ChromeDriver(chromeOptions);

	private String temp = "";
	private int y = 0;
	private boolean unfollowfailed = false;
	private static final String tourismtn = "tourismtn";
	private static final String sabarisamrat = "sabarisamrat";
	private static final String vijay = "vijaytelevision";
	private static final String insta = "https://www.instagram.com/";
	private static final String privacyText = "This Account is Private";
	private static final String following = "Following";
	private static final String notFollowing = "Follow";
	private static final String followBack = "Follow Back";
	private static final String requested = "Requested";
	private static String tempfollowingCount = "";
	private static final String tamilnaduTourism = "https://www.instagram.com/explore/tags/tamilnadutourism/";
	private static int followedCount = 0;
	private static int followSkipCount = 0;
	private static int unfollowedCount = 0;

	public static void loadSelenium() {
		final String DRIVER_PATH = "C:\\Users\\sabarisamrat\\Desktop\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
	}

	public boolean doLogin() {
		driver.manage().window().maximize();
		Object obj=(Object)driver;
		
		chromeOptions.addArguments("--start-maximized");
		driver.get("https://www.instagram.com/accounts/login");
		WebElement username = driver.findElement(By.cssSelector("input[name=username]"));
		username.sendKeys(tourismtn);
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys(getProperties("password"));
		try {
		driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[4]/button")).click();
		}catch(Exception e) {
			try {
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[6]/button")).click();
			} catch(Exception ex) {
				try {
					driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[5]/button")).click();
				} catch (Exception exx) {
					System.out.println("Sorry boss, Can't Login.");
				}
			}
		}
		goToSleep(50000);
		return true;
	}

	public boolean unfollowUsers(String userName) {
		
		temp = userName;
		driver.get("https://instagram.com/" + userName);

		try {
			driver.findElement(
					By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/button"))
					.click();
			driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")).click();
		} catch (Exception xPathFailed) {
			System.out.println(xPathFailed.getMessage());
			try {
				driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[2]/button"))
						.click();
				driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
			} catch (Exception secondTimeFailure) {
				try {
					driver.findElement(
							By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/button")).click();
					driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
					System.out.println(y + ". " + userName + " Unfollow");
				} catch (Exception thirdTimeFailure) {
					try {
						driver.findElement(
								By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/button")).click();
						driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
					}catch(Exception fourthFailure) {
						try {
						driver.findElement(
								By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/span/span[1]/button"))
								.click();
						driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/button[1]")).click();
						}catch(Exception sothanai) {
							System.out.println("Failed almost");
						}
					}
				}
			}
		}
		return true;

	}

	public void getRequestedListAndUnfollow() {
		Utility timeValidation = new Utility();
		if (true) {
			driver.get("https://www.instagram.com/accounts/access_tool/current_follow_requests");
			List<WebElement> list = driver.findElements(By.className("-utLf"));
			if (list.isEmpty()) {
				System.out.println("Since Empty List, returning");
				return;
			}
			if (!temp.equals(list.get(0).getText()) || unfollowfailed) {
				String beforeUnfollow = temp;
				unfollowUsers(list.get(0).getText());
				if (!temp.equals(beforeUnfollow)) {
					unfollowfailed = false;
					y = y+1;
				} else if (temp.equalsIgnoreCase(beforeUnfollow)) {
					unfollowfailed = true;
					System.out.println(" failed");
					System.out.println("Exception: Unfollow failed for previous user 2nd time. Trying in 5 minutes");
					try {
						Thread.sleep(100000);
					} catch (Exception e) {
						System.out.println("Error Occured: " + e.getMessage());
					}
				}
				try {
					Thread.sleep(7000);
				} catch (Exception e) {
					System.out.println("Error Occured: " + e.getMessage());
				}
			} else {
				unfollowfailed = true;
				System.out.println("Unfollow failed for previous user. Trying in 16.6 minutes");
				try {
					Thread.sleep(1000000);
				} catch (Exception e) {
					System.out.println("Error Occured: " + e.getMessage());
				}
			}
			getRequestedListAndUnfollow();
		}
	}

	public static void main(String[] args) {
		loadSelenium();
		InstagramApp instagramApp = new InstagramApp();
		instagramApp.doLogin();
		//instagramApp.unfollowFollowingRequested();
		//instagramApp.gainFollowersByHashTagOrLocation("location",tamilnaduTourism);
		//instagramApp.unfollowFollowing();
		instagramApp.gainFollowersByPage(vijay);
		//instagramApp.gainFollowersByPage(vijay, false);
	}
	
	private void unfollowDirectly() {
		if (validateUnfollowTime()) {
			driver.get(insta + tourismtn);
			try {
				driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[3]/a")).click();
				goToSleep(16000);
				try {
					for (int i = 1; i < 16; i++) {
						followed:
						for(int j = 1; j <5 ; j++) {
							for(int k =1 ; k <5; k++) {
								try {
									/*
									 * if(i==9) { goToScroll(i); }
									 */
									driver.findElement(By.xpath("/html/body/div["+j+"]/div/div[2]/ul/div/li["+i+"]/div/div["+k+"]/button")).click();
									clickUnfollow();
									break followed;
								} catch(Exception e) {
									goToSleep(900);
								}
							}
						}
					}
					System.out.println("Unfollowed " + unfollowedCount + " customers.");
					goToSleep(300300);
					unfollowDirectly();
				} catch (Exception e) {
					
				}
				/*
				 * for(int i = 1 ; i< 8;i++) { goToScroll(i, driver); goToSleep(3000); }
				 */
			} catch (Exception e) {
				System.out.println("Exception while unfollowing directly.");
			}
		}else if(validateFollowTime()) {
			gainFollowersByPage(vijay);
		}
	}

	private void clickUnfollow() {
		try {
			goToSleep(5000);
			try {
				driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/button[1]")).click();
			}catch(Exception e) {
				try {
				driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")).click();
				}catch(Exception ex) {
					try {
						driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
						}catch(Exception exc) {
							System.out.println("Can't fetch unfollowing button");
						}
				}
			}
			goToSleep(2000);
			unfollowedCount = unfollowedCount + 1;
		} catch (Exception e) {
			System.out.println("Can't fetch unfollowing button");
		}
		
	}

	public void unfollowFollowing() {
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
		
		List<String> followingUsers = getfollowingList();
		if (followingUsers != null && followingUsers.size() > 0) {
			for (String user : followingUsers) {
				unfollowCustomer(user);
			}
		} else {
			System.out.println("unfollowFollowing: Exception Occured.");
		}
		System.out.println("Succesfully unfollowed "+unfollowedCount+ " customers.");
		goToSleep(120000);
		unfollowFollowing();
	}

	public void unfollowFollowingRequested() {
		Utility timeValidation = new Utility();
		if (true) {
			getRequestedListAndUnfollow();
		}
	}
	
	public boolean gainFollowersByPage(String pageName) {
		driver.get(insta + pageName + "/");
		if (validateFollowTime()) {
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[2]/a")).click();
			goToSleep(18000);
			startFollowing(getCustomers(pageName));
			System.out.println("Successfully Followed "+ followedCount +" customers.");
			System.out.println("Skipped " + followSkipCount + " cutomers.");
			goToSleep(1200300);
		} else if (validateUnfollowTime()) {
			unfollowDirectly();
		}
		gainFollowersByPage(pageName);
		return true;
	}
	
	public boolean gainFollowersByHashTagOrLocation(String obj, String name) {
		
		while(true) {
			startFollowing(getHashTagOrLocationCustomerList(name));
		}
	}
	
	private List<String> getHashTagOrLocationCustomerList(String name) {
		driver.get(name);
		List<String> customerList = new ArrayList<>();
		try {
			try {
				goToSleep(3000);
				driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/article/div[2]/div/div[1]/div[1]/a/div/div[2]")).click();
			} catch (Exception e) {
				System.out.println("Exception finding username for given location or hashtag." + e.getMessage());
			}
			for (int i = 0; i < 20; i++) {
				try {
					goToSleep(3000);
					String customer = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/header/div[2]/div[1]/div[1]/h2/a")).getText();
					customerList.add(customer);
					System.out.println("Added: "+customer);
					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a[2]")).click();
				} catch (Exception ex) {
					try {
						goToSleep(3000);
						String customer = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/article/header/div[2]/div[1]/div[1]/h2/a")).getText();
						customerList.add(customer);
						System.out.println("Added: "+customer);
						driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a[2]")).click();
					} catch (Exception e) {
						System.out.println("Exception getting username for given location or hashtag." + ex.getMessage());
					}
				}

			}
		}catch(Exception e) {
			System.out.println("Master: Exception while gaining followers");
		}
		return customerList;
	}

	private List<String> getCustomers(String pageName) {
		
		//List<String> userLists = new ArrayList<>();
		/*for(int i = 1; i< 20 ; i++) {*/
			return tryWithImageAltName();
			/*try {
			String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[2]/div[1]/a")).getText();
			System.out.println(invitingUser);
			userLists.add(invitingUser);
			}catch(Exception e) {
				try {
					driver.navigate().refresh();
					driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[2]/a")).click();
					goToSleep(18000);
					String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[2]/div[1]/a")).getText();
					System.out.println("By Username"+invitingUser);
					userLists.add(invitingUser);
				}catch(Exception ex) {
					System.out.println("Can't get list, Trying with Alt name.");
					userLists = tryWithImageAltName();
				}
			}*/
		//}
		//return userLists;
	}

	private void startFollowing(List<String> userLists) {

		//System.out.println("Followers and Following before Following: "+getFollowers(tourismtn)+", "+getFollowing(tourismtn, driver));
		if (userLists != null && userLists.size() > 0) {
			for (String user : userLists) {
				try {
					goToFollow(user);
				} catch (Exception e) {
					System.out.println("Exception Occured while following: " + user);
				}
			}
		}
		//System.out.println("Followers and Following after Following: "+getFollowers(tourismtn)+", "+getFollowing(tourismtn, driver));		
	}

	private List<String> tryWithImageAltName() {
		
		driver.get(insta + vijay + "/");
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[2]/a"));
		ele.click();
		goToSleep(18000);
		List<String> userLists = new ArrayList<>();
		for(int i = 1; i< 20 ; i++) {
			try {
				String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div["+i+"]/div[1]/div/a/img")).getAttribute("alt");
				invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
				//System.out.println("By Alt name: "+invitingUser);
				userLists.add(invitingUser);
				}catch(Exception e) {
					try {
						String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[1]/span/img")).getAttribute("alt");
						invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
						//System.out.println("By Alt name: "+invitingUser);
						userLists.add(invitingUser);
					}catch(Exception ex) {
						try {
							String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div["+i+"]/div[1]/div/span/img")).getAttribute("alt");
							invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
							//System.out.println("By Alt name: "+invitingUser);
							userLists.add(invitingUser);
						}catch(Exception exc) {
							try {
								String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div["+i+"]/div[1]/div/a/img")).getAttribute("alt");
								invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
								//System.out.println("By Alt name: "+invitingUser);
								userLists.add(invitingUser);
							}catch(Exception exce) {
								try {
									String invitingUser = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div/div/div["+i+"]/div[1]/div/a/img")).getAttribute("alt");
									invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
									//System.out.println("By Alt name: "+invitingUser);
									userLists.add(invitingUser);
								}catch(Exception excep) {
									try {
										String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[1]/a/img")).getAttribute("alt");
										invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
										//System.out.println("By Alt name: "+invitingUser);
										userLists.add(invitingUser);
									} catch (Exception except) {
										try {
											String invitingUser = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[1]/a/img")).getAttribute("alt");
											invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
											//System.out.println("By Alt name: "+invitingUser);
											userLists.add(invitingUser);
										} catch (Exception excepti) {
											try {
												String invitingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[1]/a/img")).getAttribute("alt");
												invitingUser = invitingUser.substring( 0, invitingUser.indexOf("'"));
												//System.out.println("By Alt name: "+invitingUser);
												userLists.add(invitingUser);
											} catch (Exception exceptio) {
												System.out.println("Exception occured while getting usernames.");
											}
										}
									}
								}
							}
						}
					}
				}
		}
		return userLists;
		
	}

	private void goToFollow(String invitingUser) {
		
		String followers = null;
		String posts = null;
		String following = null;
		String privacy = null;
		try {
			followers = getFollowers(invitingUser);
			try {
				posts = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[1]/span/span")).getText();
			}catch(Exception e) {
				try {
					posts = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[1]/a/span")).getText();
				}catch(Exception ex) {
					System.out.println("Execption in getting posts.");
					return;
				}
			}
			following = getFollowing(invitingUser, driver);
			try {
			privacy = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/article/div[1]/div/h2")).getText();
			}catch(Exception e) {
				
			}
			if (followers != null && posts != null && following != null) {
				if (validateCustomer(followers, posts, following, privacy, invitingUser)) {
					doFollow(invitingUser);
				}
			}else {
				System.out.println("Execption: Something wrong.: "+invitingUser +"a"+followers+"b"+posts+"c"+following);
			}
		} catch (Exception e) {
			System.out.println("Exception: for user "+invitingUser +e.getMessage());
		}
		
	}

	private String getFollowers(String invitingUser) {
		driver.get(insta+invitingUser);
		String followers = null;
		try {
			followers = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[2]/a/span")).getText();
		}catch(Exception e) {
			try {
				followers = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[2]/span/span")).getText();
			}catch(Exception ex) {
				System.out.println("Execption in followers for "+invitingUser+".");
				return null;
			}
		}
		return followers;
	}
	private String getFollowing(String invitingUser, WebDriver webdriver) {
		webdriver.get(insta+invitingUser);
		String following = null;
		try {
			following = webdriver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[3]/span/span")).getText();
		}catch(Exception e) {
			try {
				following = webdriver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[3]/a/span")).getText();
			}catch(Exception ex) {
				System.out.println("Exception: Sorry boss can't help you!. Execption in following.");
				return null;
			}
		}
		return following;
	}

	private boolean doFollow(String invitingUser) {
		try {
			goToSleep(18000);
				try {
					String followingOrnot = "";
					try {
					followingOrnot = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/span/span[1]/button")).getText();
					followedCount = followedCount + 1;
					}catch(Exception e) {
						followingOrnot = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/button")).getText();
						followedCount = followedCount + 1;
					}
					if(notFollowing.equals(followingOrnot)) {
						try {
							driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/span/span[1]/button")).click();
							followedCount = followedCount + 1;
						}catch(Exception e) {
							driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/button")).click();
							followedCount = followedCount + 1;
					}
					}
				}catch(Exception ex) {
					System.out.println("Exception while following "+invitingUser);
				}
			goToSleep(18000);
		}catch(Exception e) {
			System.out.println("Exception: Following failed for user."+invitingUser);
		}
		return false;
	}

	private boolean validateCustomer(String followers, String posts, String following, String privacy, String invitingUser) {
		int tryingCount = 0;
		try {
			int followersCount = Integer.valueOf(followers.replaceAll(",", ""));
			int followingCount = Integer.valueOf(following.replaceAll(",", ""));
			int postsCount = Integer.valueOf(posts.replaceAll(",", ""));
			if (followersCount > 1666) {
				//System.out.println("Fail: Followers count is more than 1666. So skipping. "+invitingUser);
				followSkipCount = followSkipCount + 1;
				return false;
			} else if (followingCount > 1500) {
				//System.out.println("Fail: Following count is more than 1500. So skipping. "+invitingUser);
				followSkipCount = followSkipCount + 1;
				return false;
			} else if(postsCount == 0){
				return true;
			} else if(tryingCount < 3 && privacyText.equals(privacy)) {
				//System.out.println("Fail: Privacy User. So skipping."+invitingUser);
				followSkipCount = followSkipCount + 1;
				tryingCount = tryingCount + 1;
				return false;
			} else if(tryingCount > 3 && (privacyText.equals(privacy) || postsCount ==0 )) {
				System.out.println("Warn: Private account or No post found. Still following, as it is 3rd user similarly we trying. "+invitingUser);
				tryingCount = 0;
				return true;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception: Something went wronng. So, Skipping the user. "+invitingUser +" " +e.getMessage());
		}
		return false;
	}
	
	private List<String> getfollowingList() {
		System.out.println("Getting Following List.");
		List<String> followingList = new ArrayList<>();
		driver.get(insta+tourismtn);
		try {
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[3]/a")).click();
			goToSleep(16000);
			for(int i = 1 ; i< 8;i++) {
				goToScroll(i);
				goToSleep(3000);
			}
			for (int i = 20; i < 40; i++) {
				try {
				String followingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[1]/a/img")).getAttribute("alt");
				followingUser = followingUser.substring( 0, followingUser.indexOf("'"));
				//System.out.println("1. We're following "+followingUser);
				followingList.add(followingUser);
				Collections.reverse(followingList);
				} catch (Exception e) {
					try {
					String followingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div/a/img")).getAttribute("alt");
					followingUser = followingUser.substring( 0, followingUser.indexOf("'"));
					//System.out.println("2. We're following "+followingUser);
					followingList.add(followingUser);
					Collections.reverse(followingList);
					} catch (Exception ex) {
						try {
						String followingUser = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div[1]/a/img")).getAttribute("alt");
						followingUser = followingUser.substring( 0, followingUser.indexOf("'"));
						//System.out.println("3. We're following "+followingUser);
						followingList.add(followingUser);
						Collections.reverse(followingList);
						} catch (Exception exception) {
							try {
							String followingUser = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div/a/img")).getAttribute("alt");
							followingUser = followingUser.substring( 0, followingUser.indexOf("'"));
							//System.out.println("4. We're following "+followingUser);
							followingList.add(followingUser);
							Collections.reverse(followingList);
							} catch (Exception exc) {
								try {
									String followingUser = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div/span/img")).getAttribute("alt");
									followingUser = followingUser.substring( 0, followingUser.indexOf("'"));
									//System.out.println("5. We're following "+followingUser);
									followingList.add(followingUser);
									Collections.reverse(followingList);
									} catch (Exception exce) {
										try {
											String followingUser = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/div/li["+i+"]/div/div[1]/div/a/img")).getAttribute("alt");
											followingUser = followingUser.substring( 0, followingUser.indexOf("'"));
											//System.out.println("6. We're following "+followingUser);
											followingList.add(followingUser);
											Collections.reverse(followingList);
											} catch (Exception excep) {
												System.out.println("Need some more effort."+e.getMessage());
												break;
											}
									}
							}
						}
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Exception occured in getfollowingList."+e.getMessage());
			return null;
		}
		goToSleep(10000);
		return followingList;
	}
	
	private void goToScroll(int i) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		js.executeScript("arguments[0].scrollTop = arguments[1];",driver.findElement(By.xpath("/html/body/div[4]/div/div[2]")), i*300);
		}catch(Exception e) {
			try {
				js.executeScript("arguments[0].scrollTop = arguments[1];",driver.findElement(By.xpath("/html/body/div[3]/div/div[2]")), i*200);
				}catch(Exception ex) {
					try {
						js.executeScript("arguments[0].scrollTop = arguments[1];",driver.findElement(By.xpath("/html/body/div[2]/div/div[2]")), i*200);
						}catch(Exception exc) {
							System.out.println("Exception in scrolling."+exc.getMessage());
						}
				}
		}
		
	}

	private void unfollowCustomer(String customerName) {
		driver.get(insta+customerName);
		try {
			String followingOrNot = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/span/span[1]/button")).getText();
			if(following.equals(followingOrNot)) {
				try {
				driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/span/span[1]/button")).click();
				goToSleep(7000);
					try {
						driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/button[1]")).click();
					} catch (Exception e) {
						try {
							driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")).click();
						} catch (Exception ex) {
							try {
								driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
							} catch (Exception exc) {
								System.out.println("Button not found.");
							}
						}
					}
				goToSleep(7000);
				followingOrNot = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/div[1]/div[1]/span/span[1]/button")).getText();
				if(notFollowing.equals(followingOrNot) || followBack.equals(followingOrNot) || requested.equals(followingOrNot)) {
					if(tempfollowingCount.equals(getFollowing(tourismtn, driver))) {
						//System.out.println("Seems like previous unfollow failed. Stopped for 5 mins");
						//gainFollowersByPage(vijay, true);
						goToSleep(300300);
					}
					tempfollowingCount = getFollowing(tourismtn, driver);
					//System.out.println("Success: Current following: "+tempfollowingCount);
					unfollowedCount = unfollowedCount + 1;
				}}catch(Exception ex) {
					System.out.println("Master: Exception while unfollowing "+customerName);
				}
			}else {
				System.out.println("We are not following "+customerName);
			}
		}catch(Exception e) {
			System.out.println("Exception Occured while unfollowing "+customerName);
		}
	}
	
}
