package com.doziem.pos.system;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private boolean success;
    private String message;
    private Object data;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
