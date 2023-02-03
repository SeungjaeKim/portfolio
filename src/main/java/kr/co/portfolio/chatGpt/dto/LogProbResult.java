package kr.co.portfolio.chatGpt.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Log probabilities of different token options
 *
 * https://beta.openai.com/docs/api-reference/create-completion
 */
@Data
public class LogProbResult {

    /**
     * The tokens chosen by the completion api
     */
    List<String> tokens;

    List<Double> tokenLogprobs;

    /**
     * A map for each index in the completion result.
     */
    List<Map<String, Double>> topLogprobs;

    /**
     * The character offset from the start of the returned text for each of the chosen tokens.
     */
    List<Integer> textOffset;
}
