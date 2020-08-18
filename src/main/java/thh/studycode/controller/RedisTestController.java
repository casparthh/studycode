package thh.studycode.controller;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import thh.studycode.entity.User;

import java.util.*;


@Slf4j
@Api(value = "Redis Demo Api", tags = {"Redis"})
@RestController
public class RedisTestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "缓存的 Key 值", defaultValue = "caspar"),
            @ApiImplicitParam(name = "value", value = "缓存的 Value 值", defaultValue = "casapr"),
    })
    @ApiOperation(value = "set key value", response = ResponseEntity.class, tags = "Swagger")
    @PostMapping("/add/{key}/{value}")
    public ResponseEntity add(@PathVariable String key, @PathVariable String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        String sv = stringRedisTemplate.opsForValue().get(key);

        log.info("string set key: {}, value:{}", key, sv);
        return ResponseEntity.ok(sv);
    }

    @ApiOperation(value = "set key value by bytes", response = ResponseEntity.class)
    @PostMapping("/addbytes/{key}/{value}")
    public ResponseEntity addbytes(@PathVariable String key, @PathVariable String value) {
        RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
        connection.set(key.getBytes(), value.getBytes());

        String dv = new String(connection.get(key.getBytes()));
        log.info("string set key: {}, value:{}", key, dv);

        return ResponseEntity.ok(dv);
    }

    @ApiOperation(value = "hset key field value", response = ResponseEntity.class)
    @PostMapping("/addhash/{key}/{field}/{value}")
    public ResponseEntity addhash(@PathVariable String key, @PathVariable String field, @PathVariable String value) {
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put(key, field, value);
        log.info("hash key:{}, field:{}, value:{}", key, field, value);

        Set<String> fields = hashOperations.keys(key);
        List<String> values = hashOperations.values(key);

        return ResponseEntity.ok("keys: [" + StringUtils.join(fields, ",") + "]" + "values:[" + StringUtils.join(values, ",") + "]");
    }

    @ApiOperation(value = "hset user01 key value", response = ResponseEntity.class)
    @PostMapping("/adduser/{name}/{age}")
    public ResponseEntity adduser(@PathVariable String name, @PathVariable Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setBirthday(new Date());

        HashOperations hashOperations = stringRedisTemplate.opsForHash();

        Jackson2HashMapper mapper = new Jackson2HashMapper(true);
        hashOperations.putAll("user01", mapper.toHash(user));

        Map<String, Object> userMap = hashOperations.entries("user01");
        User u1 = (User) mapper.fromHash(userMap);
        log.info("user: {}" + mapper.toHash(u1).toString());

        return ResponseEntity.ok(mapper.toHash(u1).toString());
    }

    @ApiOperation(value = "Publish channel message", response = ResponseEntity.class)
    @PostMapping("/publish/{channel}/{message}")
    public ResponseEntity publish(@PathVariable String channel, @PathVariable String message) {
        stringRedisTemplate.convertAndSend(channel, message);
        return ResponseEntity.ok("发送成功");
    }


    @ApiOperation(value = "Subscribe channel", response = ResponseEntity.class)
    @GetMapping("/subscribe/{channel}")
    public ResponseEntity subscribe(@PathVariable String channel) {
        stringRedisTemplate.getConnectionFactory().getConnection().subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                log.info("subscribe channel: {}", new String(message.getChannel()));
                String msg = new String(message.getBody());
                log.info("subscribe message: {}", msg);
            }
        }, channel.getBytes());
        return ResponseEntity.ok("订阅成功");
    }

    @ApiOperation(value = "Compare with pipeline and normal commands", response = ResponseEntity.class)
    @PostMapping("/pipeline")
    public ResponseEntity pipeline() {
        RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User u = new User();
            u.setId(i + 100);
            u.setAge(i + 200);
            u.setName("user" + i + i);
            userList.add(u);
        }

        log.info("begin pipeline");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stringRedisTemplate.executePipelined(new RedisCallback<List<User>>() {
            @Override
            public List<User> doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
                Jackson2HashMapper mapper = new Jackson2HashMapper(true);

                userList.forEach(u -> {
                    Map<String, Object> map = mapper.toHash(u);
                    map.forEach((k, v) -> {
                        stringRedisConnection.hSet("user:" + u.getId(), k, v.toString());
                    });
                });
                return null;
            }
        });
        stopWatch.stop();
        long pipelineTimes = stopWatch.getTime();
        log.info("use pipeine spend: {}", pipelineTimes);
        log.info("end pipeline");

        log.info("begin without piple");
        stopWatch.reset();
        stopWatch.start();
        List<User> l2 = stringRedisTemplate.execute(new RedisCallback<List<User>>() {
            @Override
            public List<User> doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
                Jackson2HashMapper mapper = new Jackson2HashMapper(true);

                userList.forEach(u -> {
                    Map<String, Object> map = mapper.toHash(u);
                    map.forEach((k, v) -> {
                        stringRedisConnection.hSet("user:" + u.getId(), k, v.toString());
                    });
                });
                return null;
            }
        });
        stopWatch.stop();
        long normalTimes = stopWatch.getTime();
        log.info("without pipeine spend: {}", pipelineTimes);
        log.info("end without piple");
        Map map = ImmutableMap.of("pipeline spend times", pipelineTimes, "without pipeline spend times", normalTimes);
        return ResponseEntity.ok("");
    }

    @ApiOperation(value = "execute ....", response = ResponseEntity.class)
    @PostMapping("/execute/{id}/{name}/{age}")
    public ResponseEntity execute(@PathVariable int id, @PathVariable String name, @PathVariable Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setBirthday(new Date());
        user.setId(id);

        User u = stringRedisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection) throws DataAccessException {
                Jackson2HashMapper mapper = new Jackson2HashMapper(true);
                log.info("serialize: {}", new String(serializerValue(user)));
                connection.set(serializerKey("user" + id), serializerValue(user));
                byte[] bytes = connection.get(serializerKey("user" + id));
                log.info("get user: {}", new String(bytes));
                return deserializer(bytes);
            }
        });
        return ResponseEntity.ok(u.toString());
    }

    public byte[] serializerKey(String id) {
        return ((RedisSerializer<String>) stringRedisTemplate.getKeySerializer()).serialize(id);
    }

    public byte[] serializerValue(User u) {
        return ((Jackson2JsonRedisSerializer<User>) stringRedisTemplate.getHashValueSerializer()).serialize(u);
    }

    public User deserializer(byte[] bytes) {
        return (User)((Jackson2JsonRedisSerializer<User>) stringRedisTemplate.getHashValueSerializer()).deserialize(bytes);
    }
}
