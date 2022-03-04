package ca.dal.comparify.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Harsh Shah
 */
public class ObjectUtils {

    private static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * @author Harsh Shah
     */
    private ObjectUtils(){}

    /**
     * @param json
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public static <T> T read(String json, Class<T> classOf){
        try {
            return mapper.readValue(json, classOf);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
