package com.izzi.integra.console.web.response;

import com.izzi.integra.console.dao.entity.CatUser;

/**
 * Created by Rafael on 15/11/2016.
 */
public class UserRestResponse {
    private boolean success;

    private String message;

    private CatUser catUser;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CatUser getCatUser() {
        return catUser;
    }

    public void setCatUser(CatUser catUser) {
        this.catUser = catUser;
    }

    public UserRestResponse(boolean success, String message, CatUser catUser) {
        this.success = success;
        this.message = message;
        this.catUser = catUser;
    }

    @Override
    public String toString() {
        return "UserRestResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", catUser=" + catUser +
                '}';
    }
}
