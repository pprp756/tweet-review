package com.tr.tweetsreview.service;

import com.tr.tweetsreview.model.Tweet;
import com.tr.tweetsreview.repository.TweetRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TweetsLoader {

    static Logger LOGGER = LoggerFactory.getLogger(TweetsLoader.class);

    @Value("${tweets.filepath}")
    private String tweetsFilePath;

    @Value("${tweets.filepath}")
    private String tweetsperblock;

    @Autowired
    TweetRepository tweetRepository;

    public void loadTweets() throws IOException {
        final Workbook workbook;
        final List<Tweet> tweets = new ArrayList<>();
        LOGGER.info("Reading the Tweets File :" + tweetsFilePath);
        final File tweetsFile =  new File(tweetsFilePath);
        workbook = WorkbookFactory.create(tweetsFile);
        final Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            final Sheet sheet = sheetIterator.next();
            LOGGER.info("Reading the sheet :" + sheet.getSheetName());
            for (final Row row : sheet) {
                if (row.getRowNum() != 0) {
                    if(null != row.getCell(0)) {
                        final Tweet tweet = Tweet.builder()
                                .tweetId(row.getCell(0).getStringCellValue())
                                .content(row.getCell(1).getStringCellValue())
                                .blockId(1)
                                .blockFileName(tweetsFile.getName())
                                .build();
                        tweets.add(tweet);
                    }
                }
            }
            tweetRepository.saveAll(tweets);
        }
    }
}
