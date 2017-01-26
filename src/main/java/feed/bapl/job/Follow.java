package feed.bapl.job;

import java.util.Date;

public class Follow {

	private long followId;
	private Date lastPulishDate;
	public long getFollowId() {
		return followId;
	}
	public void setFollowId(long followId) {
		this.followId = followId;
	}
	public Date getLastPulishDate() {
		return lastPulishDate;
	}
	public void setLastPulishDate(Date lastPulishDate) {
		this.lastPulishDate = lastPulishDate;
	}
	
	
}
