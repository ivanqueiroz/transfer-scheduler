package dev.ivanqueiroz.transferscheduler.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@OpenAPIDefinition(info = @Info(title = "Transfer Scheduler API", description = "Information", contact = @Contact(name = "Ivan Frederico Bomfim Cruz de Queiroz", email = "ivanqueiroz@gmail.com")), servers = {
        @Server(url = "http://localhost:8080/"),})
@Configuration
public class TransferConfig {

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }
}
