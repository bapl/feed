package feed.bapl.dao;

import java.util.Date;
import java.util.List;

public class FeedDao {

	private  long pageSize=25L;
	public  List getFeedsByAuth(Long userId,Date Date,int page){
		
		long limt=page*pageSize;
		String sql="select id from feed where user_id=? and pulish_date=? limit ?";
		List result=null;
		return result;
		
	}
}
