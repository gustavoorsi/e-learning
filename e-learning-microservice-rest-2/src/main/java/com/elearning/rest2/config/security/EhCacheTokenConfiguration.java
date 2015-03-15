package com.elearning.rest2.config.security;

import net.sf.ehcache.config.CacheConfiguration;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Lets configure an eHcache manager that stores authentication tokens.
 * 
 * @author Gustavo Orsi
 *
 */
@Configuration
@EnableCaching
public class EhCacheTokenConfiguration implements CachingConfigurer {

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("restApiAuthTokenCache");
		cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
		cacheConfiguration.setMaxEntriesLocalHeap(0); // 0 = MAX
		cacheConfiguration.setTimeToLiveSeconds(14400); // 4 hours.
		cacheConfiguration.setEternal(false);

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfiguration);

		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager() {
		EhCacheCacheManager cacheManager = new EhCacheCacheManager();
		cacheManager.setCacheManager(ehCacheManager());
		return cacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
