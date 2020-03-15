package com.karljeong.fourtysix.application.admin.file.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "file-upload")
public class FileProperties {

    private String location;
}
