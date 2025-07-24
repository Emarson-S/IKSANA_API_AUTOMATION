# Copilot Instructions for IKSANA_API_AUTOMATION

## Project Overview
This is a Java-based API automation project using TestNG and Playwright for end-to-end API testing. The codebase is organized for maintainability and extensibility, with clear separation between test logic, base utilities, and configuration.

## Architecture & Key Components
- **src/main/java/com/example/IKSANA_API_AUTOMATION/**: Main application code (minimal, mostly for bootstrapping).
- **src/test/java/api/**: Contains API test classes (e.g., `Login.java`, `Registration.java`). Each test class targets a specific API endpoint or workflow.
- **src/test/java/base/BaseClass.java**: Provides shared setup, teardown, and utility methods for API tests. All test classes extend this base.
- **src/main/resources/application.properties**: Centralized configuration for environment variables and endpoints.
- **testng.xml**: TestNG suite configuration, controls which tests run and their order.

## Developer Workflows
- **Build**: Use Maven wrapper (`mvnw` or `mvnw.cmd`) for builds. Example: `./mvnw clean install` (Linux/macOS) or `mvnw.cmd clean install` (Windows).
- **Run Tests**: Execute tests via TestNG. Example: `./mvnw test` or `mvnw.cmd test`. Test results are output to `test-output/`.
- **Debugging**: Use `System.out.println` for inline debugging. Errors are caught and logged in test methods.
- **Test Reports**: HTML and XML reports are generated in `test-output/` after each run. Review `emailable-report.html` and `testng-results.xml` for results.

## Patterns & Conventions
- **Test Structure**: Each test method is annotated with `@Test`. Use try/catch for error handling and assertions for validation.
- **API Calls**: Use Playwright's APIRequestContext for HTTP requests. Query parameters and headers are set via `RequestOptions`.
- **Response Validation**: Responses are parsed with Gson (`JsonParser`). Always check for valid JSON and status code before assertions.
- **Error Handling**: Catch exceptions in test methods and log error messages. Do not throw errors directly from tests.
- **Configuration**: Use `application.properties` for environment-specific values. Avoid hardcoding sensitive data in test classes.

## Integration Points
- **External Dependencies**: Playwright (for API requests), Gson (for JSON parsing), TestNG (for test orchestration).
- **Cross-Component Communication**: Test classes interact with base utilities via inheritance. No direct service boundaries; all logic is in test or base classes.

## Example: API Test Pattern
```java
@Test
public void LoginWithPhoneNumber() {
    try {
        APIResponse responseAPI = request().get(origin + "validate-phone-number", ...);
        int statusCode = responseAPI.status();
        String response = responseAPI.text();
        if (isJSONValid(response) && statusCode == 200) {
            JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
            Assert.assertEquals(responsebody.get("code").getAsString(), "0000");
        }
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}
```

## Key Files
- `src/test/java/api/Login.java`: Example of API test structure and error handling.
- `src/test/java/base/BaseClass.java`: Shared utilities and setup.
- `testng.xml`: Test suite configuration.
- `application.properties`: Environment config.

---

**Feedback Requested:**
- Are there any custom workflows, scripts, or patterns not covered here?
- Is the error handling or test reporting approach different from what is described?
- Are there integration points or dependencies that need more detail?

Please provide feedback or corrections to improve these instructions for future AI agents.
