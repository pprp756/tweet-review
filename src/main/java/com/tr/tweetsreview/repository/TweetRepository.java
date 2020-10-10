package com.tr.tweetsreview.repository;

import com.tr.tweetsreview.model.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, String> {
}
