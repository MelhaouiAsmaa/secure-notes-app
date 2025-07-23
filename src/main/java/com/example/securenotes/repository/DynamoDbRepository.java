package com.example.securenotes.repository;

import com.example.securenotes.model.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoDbRepository {

    @Value("${aws.tableName}")
    private String tableName;

    private final DynamoDbClient client;

    public DynamoDbRepository(DynamoDbClient client){
        this.client = client;
    }

    public void save(Note note) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(note.getId()).build());
        item.put("content", AttributeValue.builder().s(note.getContent()).build());

        client.putItem(PutItemRequest.builder().tableName(tableName).item(item).build());
    }

    public Note get(String id) {
        Map<String, AttributeValue> key = Map.of("id", AttributeValue.builder().s(id).build());
        GetItemResponse response = client.getItem(GetItemRequest.builder().tableName(tableName).key(key).build());

        Map<String, AttributeValue> item = response.item();
        return new Note(item.get("id").s(), item.get("content").s());
    }

    public void delete(String id) {
        Map<String, AttributeValue> key = Map.of("id", AttributeValue.builder().s(id).build());
        client.deleteItem(DeleteItemRequest.builder().tableName(tableName).key(key).build());
    }
}

