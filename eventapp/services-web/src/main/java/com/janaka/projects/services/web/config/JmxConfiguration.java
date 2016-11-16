package com.janaka.projects.services.web.config;

import org.jminix.console.servlet.MiniConsoleServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

@Configuration
@EnableMBeanExport(defaultDomain = "com.janaka.projects.eventsapp")
public class JmxConfiguration {

  @Value("${jmx.rmi.host:localhost}")
  private String rmiHost;

  @Value("${jmx.rmi.port:1099}")
  private Integer rmiPort;

  @Bean
  public RmiRegistryFactoryBean rmiRegistry() {
    final RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
    rmiRegistryFactoryBean.setPort(rmiPort);
    rmiRegistryFactoryBean.setAlwaysCreate(true);
    return rmiRegistryFactoryBean;
  }

  @Bean
  @DependsOn("rmiRegistry")
  public ConnectorServerFactoryBean connectorServerFactoryBean() throws Exception {
    final ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
    connectorServerFactoryBean.setObjectName("connector:name=rmi");
    connectorServerFactoryBean.setServiceUrl(
        String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi", rmiHost, rmiPort, rmiHost, rmiPort));
    return connectorServerFactoryBean;
  }


  @Bean
  public ServletRegistrationBean jmxMiniConsoleServletRegistrationBean() {
    return new ServletRegistrationBean(new MiniConsoleServlet(), "/jmx/*");
  }


}
