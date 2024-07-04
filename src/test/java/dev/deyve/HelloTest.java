package dev.deyve;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloTest {

    private Hello hello;
    private Context context;

    @BeforeEach
    public void setUp() {
        hello = new Hello();
        context = Mockito.mock(Context.class);
        LambdaLogger logger = Mockito.mock(LambdaLogger.class);
        Mockito.when(context.getLogger()).thenReturn(logger);
    }

    @Test
    @DisplayName("Test handleRequest with valid input")
    public void testHandleRequest_validInput() {
        // Prepare input
        Map<String, Object> input = new HashMap<>();
        input.put("name", "John Doe");

        // Invoke the handler
        String result = hello.handleRequest(input, context);

        // Assert the result
        assertEquals("Hello John Doe!", result);
    }

    @Test
    @DisplayName("Test handleRequest with invalid input")
    public void testHandleRequest_invalidInput() {
        // Prepare input
        Map<String, Object> input = new HashMap<>();
        input.put("invalidKey", "invalidValue");

        // Invoke the handler
        String result = hello.handleRequest(input, context);

        // Assert the result
        assertEquals("{\"status\":\"error\"}", result);
    }

    @Test
    @DisplayName("Test handleRequest with empty input")
    public void testHandleRequest_emptyInput() {
        // Prepare input
        Map<String, Object> input = new HashMap<>();

        // Invoke the handler
        String result = hello.handleRequest(input, context);

        // Assert the result
        assertEquals("{\"status\":\"error\"}", result);
    }
}
