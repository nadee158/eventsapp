package com.janaka.projects.services.common;

/**
 * @author nadeeshani
 *
 */
public interface CacheService {

  public boolean checkIfValidKey(String key);

  public <T> void addToCache(String token, T t) throws RuntimeException;

  public <T> T getFromCache(String token, Class<T> contentClass) throws RuntimeException;

  public <T> void updateCache(String token, T t) throws RuntimeException;

  public void deleteFromCache(String key);



}
