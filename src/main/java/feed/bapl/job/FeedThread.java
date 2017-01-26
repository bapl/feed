package feed.bapl.job;

import java.util.List;

import feed.bapl.dao.FeedDao;
import feed.bapl.redis.JedisTemplete;
import feed.bapl.vo.Feed;

public class FeedThread implements Runnable {

	private Follow follow;
	private List<Feed> feeds;
	private int page;

	public FeedThread(Follow follow,List<Feed> feeds,int page){
		this.follow=follow;
		this.feeds=feeds;
		this.page=page;
		
	}
	@Override
	public void run() {
		FeedDao dao=new FeedDao();
		 JedisTemplete jedis=new JedisTemplete();
		//判断redis 是否存在关注者最新的数据
		 
		//如果有直接返回
		//如果没有从数据获取关注前50条数据
		feeds.addAll(dao.getFeedsByAuth(follow.getFollowId(), follow.getLastPulishDate(), page));
		
	}

	
	
}
