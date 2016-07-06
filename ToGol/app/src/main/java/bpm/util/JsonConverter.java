package bpm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by igor on 13/06/16.
 */
public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            //TODO: lancar exception generica do projeto
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            //TODO: lancar exception generica do projeto
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            //TODO: lancar exception generica do projeto
            throw new RuntimeException(e);
        }
    }

    public static <K,V> Map<String, Object> fromJsonMap(String json, Class<K> keyClass, Class<V> valueClass) {
        try {
            return objectMapper.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, keyClass, valueClass));
        } catch (IOException e) {
            //TODO: lancar exception generica do projeto
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
