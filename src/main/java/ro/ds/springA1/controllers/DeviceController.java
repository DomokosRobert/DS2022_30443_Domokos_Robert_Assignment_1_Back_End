package ro.ds.springA1.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import ro.ds.springA1.entities.Device;
import ro.ds.springA1.service.DeviceService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping(value = "/")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getDevices(){
        List<Device> devices = deviceService.findDevices();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable("id") Long id){
        Device device = deviceService.findDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PostMapping("/devices")
    public ResponseEntity<Long> insertDevice(@Valid @RequestBody Device device){
        Long id = deviceService.insert(device);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/devices/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("id") Long id, @RequestBody Device device){
        Device updated = deviceService.update(id,device);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/devices/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteDevice(@PathVariable Long id){
        deviceService.delete(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{id}/devices")
    public ResponseEntity<List<Device>> getDevicesForCustomer(@PathVariable("id") Long customerId)
    {
        List<Device> devices = deviceService.getDeviceByCustomerId(customerId);
        return ResponseEntity.ok(devices);
    }

}

