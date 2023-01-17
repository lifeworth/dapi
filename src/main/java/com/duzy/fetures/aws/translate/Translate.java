package com.duzy.fetures.aws.translate;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.translate.TranslateClient;
import software.amazon.awssdk.services.translate.model.TranslateException;
import software.amazon.awssdk.services.translate.model.TranslateTextRequest;
import software.amazon.awssdk.services.translate.model.TranslateTextResponse;

/**
 * @author zhiyuandu
 * @since 2022/9/4 15:36
 * @description
 */
@Component
public class Translate {
    public static void main(String[] args) {
        Region region = Region.US_WEST_2;
        TranslateClient translateClient = TranslateClient.builder()
                                                         .region(region)
                                                         .credentialsProvider(ProfileCredentialsProvider.create())
                                                         .build();

        textTranslate(translateClient);
        translateClient.close();
    }

    public static void textTranslate(TranslateClient translateClient) {

        try {
            TranslateTextRequest textRequest = TranslateTextRequest.builder()
                                                                   .sourceLanguageCode("en")
                                                                   .targetLanguageCode("zh")
                                                                   .text("Its a sunny day today")
                                                                   .build();

            TranslateTextResponse textResponse = translateClient.translateText(textRequest);
            System.out.println(textResponse.translatedText());

        } catch (TranslateException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
