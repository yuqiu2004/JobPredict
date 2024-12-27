package com.whu.pojo.vo;

import lombok.Data;

@Data
public class R<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> R<T> success() {
        R<T> result = new R<T>();
        result.code = 1;
        return result;
    }

    public static <T> R<T> success(T object) {
        R<T> result = new R<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> R<T> error(String msg) {
        R result = new R();
        result.message = msg;
        result.code = 0;
        return result;
    }
}
