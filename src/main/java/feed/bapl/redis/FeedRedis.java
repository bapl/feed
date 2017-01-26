package feed.bapl.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import feed.bapl.job.FeedThread;
import feed.bapl.job.Follow;
import feed.bapl.vo.Feed;

public class FeedRedis {

	private JedisTemplete jedis;
	
	public List getLastFollows(long user_id, int lastfollowSize) {
		return null;
	}
	/**
	 * 
	 * @param follows
	 * @param followFeedSize
	 * @return
	 */
	public List refreshLastFeeds(List<Follow> follows, int page) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);  
		List<Feed> feeds=new ArrayList<Feed>();
		for(Follow follow:follows){
			fixedThreadPool.submit(new FeedThread(follow,feeds,page));
		}
		fixedThreadPool.shutdown();
		try {
			fixedThreadPool.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return feeds;
	}
	public void upadteLastFollowFeeds(long userId, List followsFeed) {
		//batch updated
		jedis.updateLastFollowFeeds(userId,followsFeed);
	}
	public List getLastFeeds(long userId,int page) {
		
		return jedis.getLastFeed(userId);
	}
	
	public void publish(Feed feed) {
		jedis.publishFeed(feed);
		
	}
	public void updateFeed(Feed feed) {
		//update feed detail 
		//update feed index
		//update author lastupdateTime
		
	}
}
