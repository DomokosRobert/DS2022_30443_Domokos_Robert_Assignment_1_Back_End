package ro.ds.springA1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ds.springA1.controllers.handlers.exceptions.ResourceNotFoundException;
import ro.ds.springA1.entities.Customer;
import ro.ds.springA1.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> findCustomers(){
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id){
        Optional<Customer> user = customerRepository.findById(id);
        if(!user.isPresent()){
            LOGGER.error("No user with id {} found",id);
            throw new ResourceNotFoundException(Customer.class.getSimpleName() + " with id :" + id);
        }
        return user.get();
    }

    public Long insert(Customer customer){
        customer = customerRepository.save(customer);
        LOGGER.debug("User with id {} was inserted in db", customer.getId());
        return customer.getId();
    }
    public Customer update(Long id,Customer customer){
        Customer customerOld = this.findCustomerById(id);
        customerOld.setName(customer.getName());
        customerOld.setEmail(customer.getEmail());
        customerOld.setUsername(customer.getUsername());
        customerOld.setPassword(customer.getPassword());
        return customerRepository.save(customerOld);
    }

    public void delete(Long id){
        Customer deletedCustomer = this.findCustomerById(id);
        customerRepository.delete(deletedCustomer);
    }

    public Customer getCustomerByUsername(String username){
        Optional<Customer> user = customerRepository.findByUsername(username);
        if(!user.isPresent()){
            LOGGER.error("No user with username {} found",username);
            throw new ResourceNotFoundException(Customer.class.getSimpleName() + " with username :" + username);
        }
        return user.get();
    }

}
