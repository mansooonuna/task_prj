package com.mailplug.homework.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    private String message;
    private Object data;
}