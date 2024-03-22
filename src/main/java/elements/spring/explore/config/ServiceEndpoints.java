package elements.spring.explore.config;
import elements.spring.explore.jdbc.BankersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.services.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class ServiceEndpoints {
    private static final String url="http://services.soap";
    private Logger logger= LoggerFactory.getLogger(ServiceEndpoints.class);

    @Autowired
    private BankersService service;

    @PayloadRoot(namespace = url,localPart = "deleteViaIdRequest")
    @ResponsePayload
    public DeleteViaIdResponse deleteViaIdResponse(@RequestPayload DeleteViaIdRequest deleteViaIdRequest){
        DeleteViaIdResponse response=new DeleteViaIdResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        String information=service.deleteOne(deleteViaIdRequest.getId());
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage(information);

        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = url,localPart = "getByIdRequest")
    @ResponsePayload
    public GetByIdResponse getByIdResponse(@RequestPayload GetByIdRequest getByIdRequest){
        logger.trace(getByIdRequest.getId()+" received");
        GetByIdResponse response=new GetByIdResponse();
        Bankers bankers=new Bankers();

        BeanUtils.copyProperties(service.listOne(getByIdRequest.getId()).get(),bankers);
        response.setBanker(bankers);

        return response;
    }

    @PayloadRoot(namespace = url,localPart = "listBankersRequest")
    @ResponsePayload
    public ListBankersResponse listBankersResponse(@RequestPayload ListBankersRequest listOfficialRequest){
        ListBankersResponse response=new ListBankersResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        List<elements.spring.explore.jdbc.Bankers> jpaComponent = service.listAll();// pojo objects
        List<Bankers> officialsList=new ArrayList<>();// xml list of objects as of its empty

        Iterator<elements.spring.explore.jdbc.Bankers> it= jpaComponent.iterator();
        while(it.hasNext()){
            Bankers officials=new Bankers();// XSD POJO
            BeanUtils.copyProperties(it.next(),officials);
            officialsList.add(officials);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Banker's are fetched from Table");

        response.setServiceStatus(serviceStatus);
        response.getBankers().addAll(officialsList);

        return response;
    }

    @PayloadRoot(namespace = url,localPart = "newBankersRequest")
    @ResponsePayload
    public NewBankersResponse newOfficialResponse(@RequestPayload NewBankersRequest newBankersRequest){
        NewBankersResponse newBankersResponse=new NewBankersResponse();
        elements.spring.explore.jdbc.Bankers pojo= new elements.spring.explore.jdbc.Bankers();
        ServiceStatus serviceStatus=new ServiceStatus();
        logger.info("Request object received yet to convert to Entity");
        // source,target
        BeanUtils.copyProperties(newBankersRequest.getBankers(),pojo);
        logger.info("Entity object received properties from request");
        service.insertion(pojo);
        logger.trace("Entity has inserted");
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("Object Inserted");
        newBankersResponse.setBankers(newBankersRequest.getBankers());
        newBankersResponse.setServiceStatus(serviceStatus);
        logger.info("response object assembled yet to send");
        return newBankersResponse;
    }
}