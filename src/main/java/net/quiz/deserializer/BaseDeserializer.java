package net.quiz.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public abstract class BaseDeserializer<T> extends JsonDeserializer<T> {

  public abstract T fromText(String text);

  @Override
  public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    ObjectCodec codec = jsonParser.getCodec();
    JsonNode jsonNode = codec.readTree(jsonParser);

    if (jsonNode == null) {
      return null;
    }
    String text = jsonNode.textValue();
    if (text == null) {
      return null;
    }

    return this.fromText(text);
  }
}
