package com.services.rest;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemCacheUtil {

	// private static final Logger logger =
	// LogManager.getLogger(MemCacheUtil.class);
	private static MemCachedClient client = null;

	static {
		String[] servers = { "localhost:11211" };
		SockIOPool pool = SockIOPool.getInstance("utdeats");
		pool.setServers(servers);
		pool.setFailover(true);
		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
		client = new MemCachedClient("utdeats");
	}

	public static boolean putInCache(String key, String value) {
		System.out.println("cache out vakue");
		return client.set(key, value);

	}

	public static String getFromCache(String key) {
		Object value = client.get(key);
		if (value == null) {
			// logger.info("Cache miss for key:{}", key);
			System.out.println("cache get miss");
			return null;

		} else {
			// logger.info("Cache hit for key:{}", key);
			System.out.println("cache get hit");
			return (String) value;
		}
	}

	public static void removeFromCache(String key) {
		client.delete(key);
		// logger.info("Removing key: {} from cache", key);
		System.out.println("cache remove");
	}

}