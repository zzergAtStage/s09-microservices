package org.zergatstage.examinator.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/** Custom endpoint for the actuator
 * @author father
 */
@Component
@Endpoint(id = "exLimitQuestions")
@AllArgsConstructor
public class LimitsQuestions {

    @ReadOperation
    public CustomResponse readEndpoint(){
        return new CustomResponse("service status checks are unsupported", "null");
    }
    @Data //to return json instead object we need to add getters!
    @AllArgsConstructor
    public static class CustomResponse {
        private String message;
        private String messageBody;


    }
}
