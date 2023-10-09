package gr.ihu.dw.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MongoConnectionApplicationLiveTest {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoConnectionApplicationLiveTest(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Test
    void testMongoDBConnection() {
        boolean isMongoRunning = mongoTemplate.collectionExists("Keys");
        assertTrue(isMongoRunning, "MongoDB should be running and the collection ReceiverLog should exist.");
    }
}
