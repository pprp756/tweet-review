package com.tr.tweetsreview;

import com.tr.tweetsreview.service.TweetsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.tr")
public class TweetsReviewApplication implements CommandLineRunner {

	@Autowired
	TweetsLoader tweetsLoader;

	public static void main(String[] args) {
		SpringApplication.run(TweetsReviewApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		tweetsLoader.loadTweets();
	}
}
