package com.timur.alfa;

import com.timur.alfa.controllers.RichBrokeController;
import com.timur.alfa.controllers.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlfaRichBrokeApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private RichBrokeController controller;
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void testNumFormatConversions() {
		assertEquals("05", Utils.numberToXXFormatter(5));
	}

	@Test
	void testGiphyLinkCorrect() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/richorbroke",
				String.class)).contains("giphy.com/media");
	}

}
