package com.coelho.estevao.tecnonutrifeed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by estevao on 06/11/17.
 */

public class RequestClass {
    private Boolean success;
    @SerializedName("t")
    private Long timeMillis;
    @SerializedName("p")
    private int page;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
