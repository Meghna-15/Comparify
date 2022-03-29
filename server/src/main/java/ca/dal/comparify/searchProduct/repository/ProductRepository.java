package ca.dal.comparify.searchProduct.repository;


import ca.dal.comparify.mongo.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author Aman Singh Bhandari
 */

@Service
public class ProductRepository {
    @Autowired
    private MongoRepository mongoRepository;

}
