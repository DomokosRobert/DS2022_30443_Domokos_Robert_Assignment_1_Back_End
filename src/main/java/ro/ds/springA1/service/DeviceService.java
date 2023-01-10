package ro.ds.springA1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ds.springA1.controllers.handlers.exceptions.ResourceNotFoundException;
import ro.ds.springA1.entities.Device;
import ro.ds.springA1.repositories.CustomerRepository;
import ro.ds.springA1.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private final DeviceRepository deviceRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    public List<Device> findDevices(){
        return deviceRepository.findAll();
    }

    public Device findDeviceById(Long id){
        Optional<Device> device = deviceRepository.findById(id);
        if(!device.isPresent()){
            LOGGER.error("Device with id {} not found",id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + "with id :" + id);
        }
        return device.get();
    }

    public Long insert(Device device){
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db",device.getId());
        return device.getId();
    }

    public Device update(Long id,Device device){
        Device oldDevice = this.findDeviceById(id);
        oldDevice.setDescription(device.getDescription());
        oldDevice.setAddress(device.getAddress());
        oldDevice.setCustomer(device.getCustomer());
        oldDevice.setMaxHourlyConsumption(device.getMaxHourlyConsumption());
        oldDevice.setCurrentConsumption(device.getCurrentConsumption());
        return deviceRepository.save(oldDevice);
    }
    public Device addCurrentConsumption(Long id,Double value){
        Device oldDevice = this.findDeviceById(id);
        oldDevice.setCurrentConsumption(value);
        return deviceRepository.save(oldDevice);
    }
    public void delete(Long id){
        Device deletedDevice = this.findDeviceById(id);
        deviceRepository.delete(deletedDevice);
    }
    public List<Device> getDeviceByCustomerId(Long customerId){
        return deviceRepository.getDevicesByCustomerId(customerId);
    }
    public Boolean isConsumptionHigher(Long id){
        Device ourDevice = deviceRepository.getById(id);
        return ourDevice.getCurrentConsumption() > ourDevice.getMaxHourlyConsumption();
    }
}

