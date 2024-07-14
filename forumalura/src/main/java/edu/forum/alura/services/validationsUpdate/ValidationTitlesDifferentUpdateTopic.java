package edu.forum.alura.services.validationsUpdate;

import edu.forum.alura.controllers.dtos.TopicUpdateDto;
import edu.forum.alura.exceptions.ValidationException;
import edu.forum.alura.models.entities.TopicEntity;
import edu.forum.alura.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ValidationTitlesDifferentUpdateTopic implements ValidationUpdateTopic {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void  validation(long id, TopicUpdateDto request) {
        Optional<TopicEntity> topic = topicRepository.findById(id);
        if (topic.isPresent() && (!Objects.equals(topic.get().getTitle(),
                request.title())))
            throw new ValidationException("The title of the topic is " +
                        "different as the one in the database.");
    }
}
