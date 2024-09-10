package stock_microservices.adapters.driving.http.utils;

import stock_microservices.adapters.driving.http.utils.exceptions.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private JsonParser(){
        throw new IllegalStateException("Utility class");
    }

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
