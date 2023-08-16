package com.petit.toon.service.cartoon;

import com.petit.toon.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ViewCountScheduler {

    private final RedisUtil redisUtil;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void resetViewCountCache() {
        Set<String> keys = redisUtil.getKeys(CartoonService.VIEW_KEY_PREFIX);
        keys.forEach(redisUtil::delete);
    }
}
