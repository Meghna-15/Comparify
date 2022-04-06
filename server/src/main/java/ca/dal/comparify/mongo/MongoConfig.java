package ca.dal.comparify.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Configuration
@Service
public class MongoConfig {

<<<<<<< HEAD
    private static final int W = 2;
    private static final int W_TIMEOUT = 2500;
=======
    public static final int W = 2;
    public static final int W_TIMEOUT = 2500;
>>>>>>> 9334de5d99a3434bc4a7cb8658d329221ce1c397

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String connectionString) {

        ConnectionString connString = new ConnectionString(connectionString);

        WriteConcern writeConcern = new WriteConcern(W);
        writeConcern = writeConcern.withWTimeout(W_TIMEOUT, TimeUnit.MILLISECONDS);

        MongoClientSettings options = MongoClientSettings
                .builder()
                .applyConnectionString(connString)
                .writeConcern(writeConcern)
                .build();

        return MongoClients.create(options);
    }
}

