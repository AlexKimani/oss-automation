package com.safaricom.hackathon.ossautomation.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class UserIdentifier implements Serializable {
    @NotNull
    @Size(max = 255)
    @Column(name = "USER_CODE")
    private String userCode;

    public UserIdentifier() {
    }

    public UserIdentifier(@NotNull @Size(max = 255) String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
