package com.tbemerencio.products.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.tbemerencio.products.services.aws.dto.MessageDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    AmazonSNS snsClient;
    Topic catalogTopic;

    public AwsSnsService(AmazonSNS amazonSNS,
                         @Qualifier("catalogEventsTopic")Topic catalogTopic){
        snsClient = amazonSNS;
        this.catalogTopic = catalogTopic;
    }

    public void pubish(MessageDto messageDto){
        this.snsClient.publish(catalogTopic.getTopicArn(), messageDto.messageDto());
    }
}
