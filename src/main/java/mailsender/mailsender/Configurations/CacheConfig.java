package mailsender.mailsender.Configurations;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;


@Configuration
public class CacheConfig {

    @Value("${server.code-life-time}")
    private Long CODE_LIFE_TIME;

    @Value("${server.code-cache-size}")
    private Long CODE_CACHE_SIZE;

    @Bean(name = "verificationCodes")
    public Cache<String, String> verificationCodes() {
        return Caffeine.newBuilder()
                .maximumSize(CODE_CACHE_SIZE)
                .expireAfterWrite(CODE_LIFE_TIME, TimeUnit.MINUTES)
                .build();
    }

}
