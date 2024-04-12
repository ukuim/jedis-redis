package comapi.redis.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.json.Path2;

@RestController
public class HelloController {
    @Autowired
    private UnifiedJedis unifiedJedis;

    @GetMapping("/test")
    public String test() {
        return start();
    }

    @GetMapping("/test1")
    public String test1() {
        return start1();
    }

    public String start() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(20);
        //unifiedJedis.jsonSet("user:zhangsan", JSON.toJSONString(user));
        unifiedJedis.jsonSet("user:" + user.getName(), JSON.toJSONString(user));
        //  unifiedJedis.jsonSet("user",new Path2(user.getName()),JSON.toJSONString(user));
        return "OK";
    }

    public String start1() {
        Path2 path = new Path2("data.data");
      //  Path2 path1=new Path2("data");
        String name = "zhangsan";
        Object u = unifiedJedis.jsonGet("user:" + name, path);
        return u.toString();

    }

    ;
    /*
    * set------get     String
    * hmget------gmset  Hash
    * lpush------lpop   List      lrange范围查找
    * sadd------smembers
    * zadd --------- zrange---zscore
    *XADD - 添加消息到末尾
    *JsonObject json = ...; // 你的 JSON 对象
Path2 path = new Path2().append("user").append("address").append("city"); // 创建一个 Path2 对象，表示 "user.address.city" 路径

// 使用 jsonSet 方法更新 JSON 对象中的值
jsonSet("user", path, "Los Angeles"); // 这将把 "user.address.city" 的值更新为 "Los Angeles"

    *
    *
    *
    *
    *
    * */
}
