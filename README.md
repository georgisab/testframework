# Test Framework

This repository contains a reusable testing framework built with Java&nbsp;21. The project is organised as a multi-module Maven build and can be executed locally or inside Docker containers.

## Modules

- **common** – shared utilities such as configuration helpers.
- **library-site** – simple library web application for Selenium practice.
- **web-tests** – Selenium based tests for web applications.
- **api-tests** – REST API tests using RestAssured.

## Requirements

- Maven 3.9+
- JDK 21 (if running locally)
- Docker (to run inside containers)

## Run tests locally

```bash
mvn test
```

Web tests fall back to a headless HtmlUnit driver when the `SELENIUM_REMOTE_URL` variable is not provided.

## Run tests in Docker

A Dockerfile and docker-compose configuration are provided. The compose setup starts a Selenium Chrome container and executes the Maven tests in a separate container.

```bash
docker-compose up --build --exit-code-from tests
```

### Configuration

Both modules read configuration from system properties or environment variables via the `Config` helper.

- `SELENIUM_REMOTE_URL` – URL of the Selenium server used by web tests.
- `API_BASE_URL` – Base URL for API tests. When not supplied, a WireMock server is started locally.

These values can be customised per application or environment.
