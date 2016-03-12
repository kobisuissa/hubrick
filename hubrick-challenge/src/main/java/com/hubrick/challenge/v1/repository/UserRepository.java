package com.hubrick.challenge.v1.repository;

import com.hubrick.challenge.v1.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created by kobi on 06/03/16.
 */
@Component
public interface UserRepository extends ElasticsearchRepository<User, Long> {

    User findByEmail(String email);

}
