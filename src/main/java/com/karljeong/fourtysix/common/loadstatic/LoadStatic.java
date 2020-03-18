package com.karljeong.fourtysix.common.loadstatic;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.karljeong.fourtysix.common.loadstatic.service.LoadStaticService;

@Component
public class LoadStatic {

	final LoadStaticService loadStaticService;

	LoadStatic(LoadStaticService loadStaticService) {
		this.loadStaticService = loadStaticService;
	}

	private Map<String, Map<String, Object>> systemCode;

	@PostConstruct
	private void setSystemCode() {
		this.systemCode = loadStaticService.loadSystemCode();
	}

	public void resetSystemCode() {
		this.systemCode = loadStaticService.loadSystemCode();
	}

	public Map<String, Map<String, Object>> getSystemCode() {
		return systemCode;
	}

}
