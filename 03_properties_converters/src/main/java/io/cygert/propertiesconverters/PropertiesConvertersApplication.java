package io.cygert.propertiesconverters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
class PropertiesConvertersApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PropertiesConvertersApplication.class, args);

        Map<String, Object> availableBeans = Arrays.stream(context.getBeanDefinitionNames())
                                                   .collect(Collectors.toMap(Function.identity(), context::getBean));
    }

    @Bean
    ConversionService conversionService(ConversionServiceFactoryBean serviceFactoryBean) {
        return serviceFactoryBean.getObject();
    }

    @Bean
    ConversionServiceFactoryBean conversionServiceFactoryBean(TcpAddressConverter tcpAddressConverter) {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(Set.of(tcpAddressConverter));
        return factory;
    }
}

@Slf4j
@Service
class FooService {

    @Value("${ip}")
    private TcpAddress ip;

    @PostConstruct
    void init() {
        log.info("Provided ip address = {} and port {}", ip.ip(), ip.port());
    }
}

record TcpAddress(String ip, int port) {
}

@Component
//@ConfigurationPropertiesBinding
class TcpAddressConverter implements Converter<String, TcpAddress> {

    @Override
    public TcpAddress convert(String property) {
        String[] input = property.split(":");
        return new TcpAddress(input[0], Integer.parseInt(input[1]));
    }
}

