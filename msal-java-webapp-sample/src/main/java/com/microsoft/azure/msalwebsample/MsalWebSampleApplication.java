// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azure.msalwebsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// When running as standalone application, base class SpringBootServletInitializer is irelevant. It is used by container.
// Same applies to method SpringBootServletInitializer#configure(SpringApplicationBuilder builder). It will be called by container
@SpringBootApplication
public class MsalWebSampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MsalWebSampleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MsalWebSampleApplication.class);
	}
}

