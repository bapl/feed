package feed.bapl.service;

import java.util.List;

import feed.bapl.localcache.LocalCache;
import feed.bapl.redis.FeedRedis;
import feed.bapl.vo.User;

public class FeedPullService {

	private FeedRedis feedRedis;
	private LocalCache localCache;
	private int lastfollowSize;
	private int followFeedSize;

	public List firstFeedData(User user){	
		return lastFeedData(user, 1);
	}
	public List getCurrentFeedData(User user){
		return null;
	}
	public List nextFeedData(User user,int page){
		List followsFeed=feedRedis.getFeeds(user.getUserId(),page);
		if(followsFeed==null||followsFeed.isEmpty()){
			return feedRedis.getLastFeeds(user.getUserId(),page);
		}
		return followsFeed;
	}
	
	public List lastFeedData(User user,int page){
		//先判断redis 最近feed 集合是否有数据
		 List followsFeed=feedRedis.getLastFeeds(user.getUserId(),page);
		 if(followsFeed==null||followsFeed.isEmpty()){
			//1 根据timeline 的时间排序存在Redis sortedSet集合里面
			 List follows=feedRedis.getLastFollows(user.getUserId(),lastfollowSize);
			//根据 过滤后的最近的发布微博的人获取他们最新50数据
			  followsFeed=feedRedis.refreshLastFeeds(follows, page);
			//更新本地localCache
			 feedRedis.upadteLastFollowFeeds(user.getUserId(),followsFeed);	
			 followsFeed=feedRedis.getLastFeeds(user.getUserId(),page);
		 }
		 return followsFeed;
	}

}
