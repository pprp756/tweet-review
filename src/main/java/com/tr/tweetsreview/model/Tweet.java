package com.tr.tweetsreview.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Tweet {
    @Id
    private String tweetId;
    private String content;
    private Integer blockId;
    private String blockFileName;
}
