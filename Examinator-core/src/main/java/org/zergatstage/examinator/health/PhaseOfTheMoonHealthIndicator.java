package org.zergatstage.examinator.health;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/** This class is useful to create custom health indicators
 * @author father
 */
@Component
public class PhaseOfTheMoonHealthIndicator implements HealthIndicator {
    private Random random;

    @PostConstruct
    public void init() {
        random = ThreadLocalRandom.current();
    }

    @Override
    public Health health() {
        if (random.nextInt(2) == 0) {
            return Health.down()
                    .status("Invalid phase of the Moon")
                    .build();
        }
        return Health.up().build();
    }
}
