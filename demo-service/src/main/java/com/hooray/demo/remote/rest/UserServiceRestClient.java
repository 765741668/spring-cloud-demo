package com.hooray.demo.remote.rest;

import com.hooray.common.utils.FastJsonUtils;
import com.hooray.common.utils.api.APIResult;
import com.hooray.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceRestClient {

    @Autowired
    private RestTemplate restTemplate;
    
    private final static String URL = "http://user-service/user";

    public APIResult findById(Long id){
        return restTemplate.getForObject(URL + "/{id}", APIResult.class, id);
    }

    public APIResult findByName(String name){
        return restTemplate.getForObject(URL + "/name/{name}", APIResult.class, name);
    }

    public APIResult findByUser(UserVO user){
        return restTemplate.postForObject(URL + "/userInfo?user={user}", user, APIResult.class,user);
    }

    public APIResult save(UserVO user){
        return restTemplate.postForObject(URL + "/save?user={user}", user, APIResult.class, user);
    }

    public void update(UserVO user){
        restTemplate.put(URL + "/update?user={user}", user, UserVO.class);
    }

    public void delete(Long id){
        restTemplate.delete(URL + "/delete/{id}", id);
    }

    private HttpEntity buildParam(Object... params){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        String json = FastJsonUtils.toJson(params);

        return new HttpEntity<String>(json, headers);
    }
}