package com.syncledger.transactionsAPI.config;

import com.merge.api.MergeApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MergeConfiguration {
    @Value("${merge.apiKey}")
    private String apiKey;

    @Bean
    public MergeApiClient mergeApiClient() {
        return MergeApiClient.builder().apiKey(apiKey).build();
    }
}
