package ca.dal.comparify.feedback.services;


import ca.dal.comparify.feedback.repository.FeedbackRepository;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import ca.dal.comparify.feedback.model.Feedback;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import ca.dal.comparify.mongo.MongoRepository;

import java.util.Collections;
import java.util.List;


/**
 * @author Meghna Rupchandani
 */
@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public boolean addFeedback( Feedback f) {
        feedbackRepository.save(f);
        return true;
    }




 }



