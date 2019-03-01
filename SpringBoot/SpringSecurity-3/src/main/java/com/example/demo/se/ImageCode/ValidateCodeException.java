package com.example.demo.se.ImageCode;


import org.springframework.security.core.AuthenticationException;

/**
 * @author ShotMoon
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}