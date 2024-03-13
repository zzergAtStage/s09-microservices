package org.zergatstage.examinator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author father
 */
@Configuration
@EnableIntegration
public class IntegrationFileConfig {

    @Bean
    public MessageChannel textInputChannel(){
        return new DirectChannel();
    }

    @Bean MessageChannel textWriteChannel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "textWriteChannel")
    public GenericTransformer<String, String> mainTransformer() {
        Logger.getAnonymousLogger().info("IntegrationFileConfig.GenericTransformer created");
        return text -> {
            //какая-то логика
            return text;
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "textWriteChannel")
    public FileWritingMessageHandler messageHandler(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File("data/outbound") //TODO replace with profiled tune
        );
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return  handler;
    }
}
