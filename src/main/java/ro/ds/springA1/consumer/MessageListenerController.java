package ro.ds.springA1.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.ds.springA1.entities.Device;
import ro.ds.springA1.service.DeviceService;

import javax.xml.ws.Response;


@Controller
public class MessageListenerController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SimpMessagingTemplate template;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message){
        System.out.println(message);
        SendingMessage toBeSent = new SendingMessage();
        deviceService.addCurrentConsumption(message.getDeviceId(), message.getMeasurementValue());
        Device ourDevice = deviceService.findDeviceById(message.getDeviceId());
        if(deviceService.isConsumptionHigher(message.getDeviceId())){
            toBeSent.setMessage("Device with id = " + message.getDeviceId() + " has it current consumption " + ourDevice.getCurrentConsumption()  + " than the maximum one " + ourDevice.getMaxHourlyConsumption() + " belonging to the customer " + ourDevice.getCustomer().getId());
            toBeSent.setCustomerId(ourDevice.getCustomer().getId().toString());
            template.convertAndSend("/topic/message",toBeSent);
            System.out.println("Higher consumption\n");
        }

    }




}
