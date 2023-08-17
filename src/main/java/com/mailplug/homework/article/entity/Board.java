package com.mailplug.homework.article.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Board {
    NOTICE("NOTICE"), FREE("FREE");

    private final String board;
}
