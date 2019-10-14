package com.bateng.guestroom.config.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class SpringDataConvert {

    @Autowired
     private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void addStringToRoomLevelsConvert(){
        ConfigurableWebBindingInitializer initializer= (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
           if(initializer!=null){
               GenericConversionService service= (GenericConversionService) initializer.getConversionService();
               service.addConverter(new StringToRoomLevelsConvert());
           }
    }

    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return requestMappingHandlerAdapter;
    }

    public void setRequestMappingHandlerAdapter(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }
}
