package ro.ds.springA1.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="description",nullable = false)
    private String description;
    @Column(name="address",nullable = false)
    private String address;
    @Column(name="maxHourlyConsumption",nullable = false)
    private Double maxHourlyConsumption;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Device() {
    }

    public Device(Long id, String description, String address, Double maxHourlyConsumption, Customer customer) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxHourlyConsumption = maxHourlyConsumption;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMaxHourlyConsumption() {
        return maxHourlyConsumption;
    }

    public void setMaxHourlyConsumption(Double maxHourlyConsumption) {
        this.maxHourlyConsumption = maxHourlyConsumption;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", maxHourlyConsumption=" + maxHourlyConsumption +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) && Objects.equals(description, device.description) && Objects.equals(address, device.address) && Objects.equals(maxHourlyConsumption, device.maxHourlyConsumption) && Objects.equals(customer, device.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, address, maxHourlyConsumption, customer);
    }
}
