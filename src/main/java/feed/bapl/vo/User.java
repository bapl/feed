package feed.bapl.vo;


import java.util.SortedSet;

public class User<T> {

	private long userId;
	private String userName;
	private SortedSet<T> follows;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public SortedSet getFollows() {
		return follows;
	}
	public void setFollows(SortedSet follows) {
		this.follows = follows;
	}
	
	
}
