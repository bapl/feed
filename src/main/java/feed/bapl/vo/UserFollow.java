package feed.bapl.vo;

import java.util.Date;

public class UserFollow {

	private long userId;
	private long followUserId;
	private Date lastLoginDate;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(long followUserId) {
		this.followUserId = followUserId;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}
