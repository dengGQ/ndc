package com.ndc.channel.common;

import com.ndc.channel.common.service.IIdGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class ApplicationStartup {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	@Resource
	private IIdGeneratorService idGeneratorService;

	@PostConstruct
	public void startup() {
		idGeneratorService.initRedisPrimaryKey();
	}

}
