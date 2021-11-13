package com.fsoft.igos.controller;

import com.fsoft.igos.config.AppConfig;
import com.fsoft.igos.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SupportController {
    private Date mUpTime = new Date();

    @Autowired
    private AppConfig mAppConfig;

    @GetMapping(path = "/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Igos Edxops");
        response.put("status", "Running");
        response.put("releaseDate", DateTimeUtils.convertDateToString(DateTimeUtils.toDate(mAppConfig.getBuildDate())));
        response.put("upTime", DateTimeUtils.toString(mUpTime));
        response.put("version", mAppConfig.getVersion());
        return ResponseEntity.ok(response);
    }
}
