package vn.plusplus.spring.services;

import vn.plusplus.spring.controller.request.LoginRequest;

public interface UserInterface {

    String login(LoginRequest loginRequest);
}
