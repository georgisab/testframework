package com.example.api;

import com.example.common.Config;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.equalTo;

class SampleApiTest {
    private static WireMockServer server;

    @BeforeAll
    static void startServer() {
        String baseUrl = Config.get("API_BASE_URL", "");
        if (baseUrl.isEmpty()) {
            server = new WireMockServer(WireMockConfiguration.options().dynamicPort());
            server.start();
            configureFor("localhost", server.port());
            stubFor(get("/hello").willReturn(aResponse().withHeader("Content-Type", "application/json").withBody("{\"message\":\"hi\"}")));
            RestAssured.baseURI = "http://localhost:" + server.port();
        } else {
            RestAssured.baseURI = baseUrl;
        }
    }

    @AfterAll
    static void stopServer() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    void helloEndpointReturnsMessage() {
        RestAssured
            .given()
            .when().get("/hello")
            .then()
            .statusCode(200)
            .body("message", equalTo("hi"));
    }
}
