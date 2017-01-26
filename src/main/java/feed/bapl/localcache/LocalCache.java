package feed.bapl.localcache;

import java.util.List;
import java.util.Map;

public class LocalCache {

	private Map cache;
	public boolean containsKey(long key) {
		if(cache.containsKey(key)){
			return true;
		}
		return false;
	}
	public List get(long key) {
		// TODO Auto-generated method stub
		return (List) cache.get(key);
	}

}
