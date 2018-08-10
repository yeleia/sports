package org.wingstudio.sports.service;

import org.springframework.http.HttpRequest;
import org.wingstudio.sports.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    Map<String,Object> login(User user, HttpServletRequest request);
}
