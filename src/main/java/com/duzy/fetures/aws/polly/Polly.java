package com.duzy.fetures.aws.polly;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhiyuandu
 * @since 2022/9/6 13:54
 * @description
 */
public class Polly {

    private static final String SAMPLE = "你好，我是机器人。";

    public static void main(String[] args) {

        PollyClient polly = PollyClient.builder()
                                       .region(Region.AP_NORTHEAST_1)
                                       .credentialsProvider(ProfileCredentialsProvider.create())
                                       .build();

        talkPolly(polly);
        polly.close();
    }

    public static void talkPolly(PollyClient polly) {

        try {
            DescribeVoicesRequest describeVoiceRequest =
                    DescribeVoicesRequest.builder().languageCode(LanguageCode.CMN_CN).engine(Engine.NEURAL).build();

            DescribeVoicesResponse describeVoicesResult = polly.describeVoices(describeVoiceRequest);
            Voice voice = describeVoicesResult.voices().get(0);
            InputStream stream = synthesize(polly, SAMPLE, voice, OutputFormat.MP3);
            AdvancedPlayer player = new AdvancedPlayer(stream, javazoom.jl.player.FactoryRegistry.systemRegistry()
                                                                                                 .createAudioDevice());
            player.setPlayBackListener(new PlaybackListener() {

                @Override
                public void playbackStarted(PlaybackEvent evt) {
                    System.out.println("Playback started");
                    System.out.println(SAMPLE);
                }

                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    System.out.println("Playback finished");
                }
            });

            // play it!
            player.play();

        } catch (PollyException | JavaLayerException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static InputStream synthesize(PollyClient polly, String text, Voice voice,
                                         OutputFormat format) throws IOException {

        SynthesizeSpeechRequest synthReq = SynthesizeSpeechRequest.builder()
                                                                  .text(text)
                                                                  .voiceId(voice.id())
                                                                  .outputFormat(format)
                                                                  .build();

        ResponseInputStream<SynthesizeSpeechResponse> synthRes = polly.synthesizeSpeech(synthReq);
        return synthRes;
    }
}
