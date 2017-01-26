package feed.bapl.service;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import feed.bapl.localcache.LocalCache;
import feed.bapl.redis.FeedRedis;
import feed.bapl.vo.Feed;
import feed.bapl.vo.User;

public class FeedPublishService {
	private FeedRedis feedRedis;
	private int lastfollowSize;

	public void publishFeed(Feed feed){
		//存放缓存里，后台job 把缓存数据里更新到数据库
		feedRedis.publish(feed);
		//更新自己最近发布的feed，
		//更新3个地方，一个具体的数据，一个索引列表,以及自己最新发布微博的时间
		feedRedis.updateFeed(feed);	
	}

}
