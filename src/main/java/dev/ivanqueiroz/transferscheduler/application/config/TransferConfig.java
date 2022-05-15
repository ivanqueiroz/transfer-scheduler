package dev.ivanqueiroz.transferscheduler.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class TransferConfig {

  @Bean
  public AcceptHeaderLocaleResolver localeResolver() {
    final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
    resolver.setDefaultLocale(Locale.US);
    return resolver;
  }
}
