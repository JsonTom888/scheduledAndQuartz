package com.example.quartzdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/4 15:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    Integer code;

    String message;

    T result;

    public Response (String message,T result){
        this.code = 200;
        this.message = message;
        this.result = result;
    }

}
