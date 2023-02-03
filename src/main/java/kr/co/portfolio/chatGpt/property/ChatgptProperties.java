package kr.co.portfolio.chatGpt.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "chatgpt")
public class ChatgptProperties {

    private String apiKey = "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";

    private String model = "text-davinci-003";

    private Integer maxTokens = 900;

    private Double temperature = 0.0;

    private Double topP = 1.0;

    private String organization = "org-pA297Enj024yhwlDH7WG7Ehs";

}
