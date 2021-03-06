package org.apache.shiro.spring.boot.cache;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.AbstractCachingConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(AbstractCachingConfiguration.class)
@AutoConfigureBefore(ShiroWebAutoConfiguration.class)
@ConditionalOnClass(net.sf.ehcache.CacheManager.class)
@ConditionalOnProperty(prefix = ShiroCacheProperties.PREFIX, value = "type", havingValue = "ehcache")
public class ShiroEhCacheConfiguration {

	@Bean
	public CacheManager shiroCacheManager(@Autowired(required = false) net.sf.ehcache.CacheManager cacheManager) {
		// 构造Shiro的CacheManager
		EhCacheManager shiroCacheManager = new EhCacheManager();
		// 给 CacheManager设置值
		shiroCacheManager.setCacheManager(cacheManager);

		return shiroCacheManager;
	}

}
