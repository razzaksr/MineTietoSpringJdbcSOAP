package elements.spring.explore.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.WebServiceMessageReceiverHandlerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.xml.bind.annotation.XmlSchema;

@EnableWs
@Configuration
public class ServiceConfiguration extends WsConfigurerAdapter {
    @Bean(name = "bankers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bankersSchema){
        DefaultWsdl11Definition schemaObject=new DefaultWsdl11Definition();
        schemaObject.setPortTypeName("BankersPort");
        schemaObject.setTargetNamespace("http://services.soap");
        schemaObject.setLocationUri("/bankerspoint");
        schemaObject.setSchema(bankersSchema);
        return schemaObject;
    }
    @Bean
    public XsdSchema bankersSchema(){
        return new SimpleXsdSchema(new ClassPathResource("bankers.xsd"));
    }
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/bankerspoint/*");
    }
}