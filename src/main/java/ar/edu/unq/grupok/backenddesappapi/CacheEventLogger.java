package ar.edu.unq.grupok.backenddesappapi;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger logger = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        logger.info("Cache Event: " + cacheEvent.getType()
                + ", Key: " + cacheEvent.getKey()
                + ", Old value: " + cacheEvent.getOldValue()
                + ", New value: " + cacheEvent.getNewValue());
    }
}
