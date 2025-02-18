package ctv.core_service.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter userCreatedCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter("users.created");
    }

    @Bean
    public Timer userProcessingTimer(MeterRegistry meterRegistry) {
        return meterRegistry.timer("users.processing.time");
    }

    @Bean
    public AtomicInteger activeUsersGauge(MeterRegistry meterRegistry) {
        return meterRegistry.gauge("users.active.count", new AtomicInteger(0));
    }

    @Bean
    public DistributionSummary ageSummary(MeterRegistry meterRegistry) {
        return DistributionSummary.builder("users.age.distribution")
                .description("User age distribution")
                .register(meterRegistry);
    }
}
