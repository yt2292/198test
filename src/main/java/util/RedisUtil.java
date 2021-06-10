package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static int MAX_ACTIVE = 3;
    private static int MAX_IDLE = 3;
    private static int MAX_WAIT = 10000;//最大等候时间
    public static int TIMEOUT = 10000;
    public static int RETRY_NUM = 5;

    private static String ADDR = "192.168.239.130";
    private static int PORT = 6379;

    private static String AUTH = "123456";

    private static JedisPool jedisPool;
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);



        jedisPool = new JedisPool(config,ADDR,PORT);

    }

    /**
     * 获取jedis
     * @return
     */
    public static Jedis openJedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth(AUTH);
        return jedis;
    }

    /**
     * 关闭jedis
     * @param jedis
     */
    public static void closeJedis(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }
}
