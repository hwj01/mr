package com.hwj.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("girl")
@RefreshScope
public class ConfigGril {
    private String name;
    private Integer age;
}
