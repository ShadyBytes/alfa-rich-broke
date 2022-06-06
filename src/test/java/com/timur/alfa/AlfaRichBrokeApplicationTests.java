package com.timur.alfa;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.timur.alfa.controllers.RichBrokeController;
import com.timur.alfa.controllers.Utils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
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

    @Test
    void testReturnedStatusOK() throws IOException {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/richorbroke")).willReturn(aResponse().withStatus(200)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8080/richorbroke");
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(200, httpResponse.getCode());

        wireMockServer.stop();
    }

}
