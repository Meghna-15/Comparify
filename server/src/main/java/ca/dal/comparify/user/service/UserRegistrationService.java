package ca.dal.comparify.user.service;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private MongoRepository mongoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int register(SignupRequest signupRequest) {

        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        signupRequest.setConfirmPassword(null);

        return mongoRepository.insertOne("user", signupRequest, SignupRequest.class);
    }
}
