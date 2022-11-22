package ro.ds.springA1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ds.springA1.entities.Device;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> getDevicesByCustomerId(Long customerId);
}
