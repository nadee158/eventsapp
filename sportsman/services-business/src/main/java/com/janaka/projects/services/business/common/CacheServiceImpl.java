package com.janaka.projects.services.business.common;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.janaka.projects.services.common.CacheService;

/**
 * @author nadeeshani
 *
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;


  @Override
  public boolean checkIfValidKey(String key) {
    System.out.println("KEY $$$" + key);
    if (StringUtils.isNotEmpty(key)) {
      return redisTemplate.opsForValue().getOperations().hasKey(key);
    }
    return false;
  }

  @Override
  public <T> void addToCache(String key, T t) throws RuntimeException {
    System.out.println("KEY $$$" + key);
    System.out.println("t $%%%" + t);
    if (StringUtils.isNotEmpty(key) && t != null) {
      ObjectMapper mapper = new ObjectMapper();
      try {
        redisTemplate.opsForValue().set(key, mapper.writeValueAsString(t));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public <T> T getFromCache(String key, Class<T> contentClass) throws RuntimeException {
    System.out.println("KEY $$$" + key);
    System.out.println("contentClass $%%%" + contentClass);
    if (StringUtils.isNotEmpty(key)) {
      ObjectMapper mapper = new ObjectMapper();
      String jsonString = redisTemplate.opsForValue().get(key);
      if (StringUtils.isNotEmpty(jsonString)) {
        try {
          return mapper.readValue(jsonString, contentClass);
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
      }
    }
    return null;
  }

  @Override
  public <T> void updateCache(String key, T t) throws RuntimeException {
    System.out.println("KEY $$$" + key);
    System.out.println("T $%%%" + t);
    if (StringUtils.isNotEmpty(key)) {
      ObjectMapper mapper = new ObjectMapper();
      try {
        redisTemplate.opsForValue().set(key, mapper.writeValueAsString(t));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void deleteFromCache(String key) {
    System.out.println("KEY $$$" + key);
    if (StringUtils.isNotEmpty(key)) {
      redisTemplate.opsForValue().getOperations().delete(key);
    }
  }

}
