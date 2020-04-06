package com.heapcode.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import static com.heapcode.analytics.ApplicationConstants.INGEST_APP_PORT;;

/**
 * @author Manjunath Sampath
 *
 */
@Component
public class ServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	@Autowired
	private Environment envrionment;

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		factory.setPort(new Integer(envrionment.getProperty(INGEST_APP_PORT)));
	}
}