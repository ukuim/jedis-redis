package comapi.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.*;
import redis.clients.jedis.providers.PooledConnectionProvider;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }


    @Bean
    public UnifiedJedis unifiedJedis() {

        HostAndPort config = new HostAndPort("192.168.80.129", 6379);
        PooledConnectionProvider provider = new PooledConnectionProvider(config);
        UnifiedJedis unifiedJedis = new UnifiedJedis(provider);
        unifiedJedis.sendCommand(Protocol.Command.AUTH, new String[]{"root"});
        return unifiedJedis;
    }
}
