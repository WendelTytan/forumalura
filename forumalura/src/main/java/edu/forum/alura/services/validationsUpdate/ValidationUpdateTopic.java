package edu.forum.alura.services.validationsUpdate;

import edu.forum.alura.controllers.dtos.TopicUpdateDto;

public interface ValidationUpdateTopic {
    void validation(long id, TopicUpdateDto request);
}
