package com.blazarevic.demo.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Frame {

    private String uid;
    private long ts;

    Map<String, Object> details = new LinkedHashMap<>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @JsonAnySetter
    public void setDetail(String key, Object value) {
        details.put(key, value);
    }

    public Frame withUID(String uid) {
        this.uid = uid;
        return this;
    }

    public Frame withTS(Long ts) {
        this.ts = ts;
        return this;
    }

}
