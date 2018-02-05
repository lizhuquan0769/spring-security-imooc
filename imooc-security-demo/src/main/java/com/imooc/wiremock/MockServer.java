package com.imooc.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class MockServer {
	
	public static void main(String[] args) {
		configureFor(8081);
		removeAllMappings();
		
		stubFor(get(urlPathEqualTo("/order/1"))
			.willReturn(aResponse()
				.withBody("{\"id\":111}")
				.withStatus(200)));
	}
}
