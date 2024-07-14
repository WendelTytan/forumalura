package edu.forum.alura.services.validationsCreate;

import edu.forum.alura.controllers.dtos.TopicRequestDto;
import edu.forum.alura.exceptions.ValidationException;
import edu.forum.alura.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationDuplicateDataCreateTopic implements ValidationCreateTopic {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void  validation(TopicRequestDto request) {
        boolean titleExists = topicRepository.existsByTitle(request.title());
        boolean messageExists =
                topicRepository.existsByContent(request.content());
        if (titleExists && messageExists) {
            throw new ValidationException("This topic already exists in the " +
                    "database.");
        }
    }
}
