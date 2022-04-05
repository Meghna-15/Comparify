package ca.dal.comparify.mongo;

import ca.dal.comparify.utils.UUIDUtils;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {MongoRepository.class, MongoConfig.class})
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoRepositoryTest {

    @Autowired
    private MongoRepository mongoRepository;

    private String collectionName;

    public static Stream<Arguments> testFindDatasource() {
        return Stream.of(
            Arguments.of(new Document(), Document.class, 3),
            Arguments.of(new Document("name", "Herry"), Document.class, 1),
            Arguments.of(new Document("name", "Herry"), null, 0));
    }

    public static Stream<Arguments> testFindWithPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(),
                new PaginationOptions(new Document()), Document.class, 3),
            Arguments.of(new Document("name", "Herry"),
                new PaginationOptions(new Document("name", 1)), Document.class, 1),
            Arguments.of(new Document("name", "Herry"), null, null, 0));
    }

    public static Stream<Arguments> testFindWithProjectionDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document(), Document.class, 3),
            Arguments.of(new Document("name", "Herry"), new Document("name", 1), Document.class, 1),
            Arguments.of(new Document("name", "Herry"), null, null, 0),
            Arguments.of(new Document("name", "Herry"), null, Document.class, 1));
    }

    public static Stream<Arguments> testFindWithProjectionAndPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document(),
                new PaginationOptions(new Document()), Document.class, 3),
            Arguments.of(new Document("name", "Herry"), new Document("name", 1),
                new PaginationOptions(new Document("name", 1)), Document.class, 1),
            Arguments.of(new Document("name", "Herry"), null, null, null, 0),
            Arguments.of(new Document("name", "Herry"), null,
                new PaginationOptions(new Document()), Document.class, 1));
    }

    public static Stream<Arguments> testFindOneDatasource() {
        return Stream.of(
            Arguments.of(new Document(), Document.class, new Document("name", "John")),
            Arguments.of(new Document(), null, null)
        );
    }

    public static Stream<Arguments> testFindOneWithPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new PaginationOptions(new Document()),
                Document.class, new Document("name", "John")),
            Arguments.of(new Document(), new PaginationOptions(new Document()),
                null, null)
        );
    }

    public static Stream<Arguments> testFindOneWithProjectionDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document("_id", 0),
                Document.class, new Document("name", "John")),
            Arguments.of(new Document(), null, null, null),
            Arguments.of(new Document(), null, Document.class, new Document("name", "John"))
        );
    }

    @BeforeAll
    void setUpForTestSuite() {

        collectionName = UUIDUtils.generate();

        List<Document> dummy = new ArrayList<>();
        dummy.add(new Document("name", "John"));
        dummy.add(new Document("name", "Herry"));
        dummy.add(new Document("name", "David"));

        mongoRepository.insertMany(collectionName, dummy, Document.class);
    }

    @AfterAll
    void tearDownTestSuite() {
        mongoRepository.dropCollection(collectionName);
    }

    /**
     * @param query
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFind() = {0}")
    @MethodSource("testFindDatasource")
    <T> void testFind(Document query, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, tClass).size());
    }

    /**
     * @param query
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindWithPagination() = {0}")
    @MethodSource("testFindWithPaginationDatasource")
    <T> void testFindWithPagination(Document query, PaginationOptions options, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, options, tClass).size());
    }

    /**
     * @param query
     * @param projection
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindWithProjection() = {0}")
    @MethodSource("testFindWithProjectionDatasource")
    <T> void testFindWithProjection(Document query, Document projection, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, projection, tClass).size());
    }

    /**
     * @param query
     * @param projection
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindWithProjectionAndPagination() = {0}")
    @MethodSource("testFindWithProjectionAndPaginationDatasource")
    <T> void testFindWithProjectionAndPagination(Document query, Document projection,
                                                 PaginationOptions options,
                                                 Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, projection, options, tClass).size());
    }

    /**
     * @param query
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOne() = {0}")
    @MethodSource("testFindOneDatasource")
    <T> void testFindOne(Document query, Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, tClass));
        }
    }

    /**
     * @param query
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOneWithPagination() = {0}")
    @MethodSource("testFindOneWithPaginationDatasource")
    <T> void testFindOneWithPagination(Document query, PaginationOptions options,
                                       Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, options, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, options, tClass));
        }
    }

    /**
     * @param query
     * @param projection
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOneWithProjection() = {0}")
    @MethodSource("testFindOneWithProjectionDatasource")
    <T> void testFindOneWithProjection(Document query, Document projection,
                                       Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, projection, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, projection, tClass));
        }
    }

    public static Stream<Arguments> testFindOneWithProjectionAndPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document("_id", 0),
                new PaginationOptions(new Document()), Document.class, new Document("name", "John")),
            Arguments.of(new Document("name", "Herry"), new Document("_id", 0),
                new PaginationOptions(new Document("name", 1)), Document.class, new Document("name", "Herry")),
            Arguments.of(new Document("name", "Herry"), null, null, null, null),
            Arguments.of(new Document("name", "Herry"), null,
                new PaginationOptions(new Document()), Document.class, new Document("name", "Herry")));
    }

    @ParameterizedTest(name = "{index}: testFindOneWithProjectionAndPagination() = {0}")
    @MethodSource("testFindOneWithProjectionAndPaginationDatasource")
    <T> void testFindOneWithProjectionAndPagination(Document query, Document projection,
                                       PaginationOptions options, Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, projection, options, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, projection, options, tClass));
        }
    }

}

