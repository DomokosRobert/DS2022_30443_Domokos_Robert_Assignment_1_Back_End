package ro.ds.springA1.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ds.springA1.entities.Customer;
import ro.ds.springA1.service.CustomerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping(value = "/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.findCustomers();
        return ResponseEntity.ok(customers);
    }


    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long userId) {
        Customer customer = customerService.findCustomerById(userId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<Long> insertCustomer(@Valid @RequestBody Customer customer){
        Long customerId = customerService.insert(customer);
        return new ResponseEntity<>(customerId,HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long customerId, @RequestBody Customer customer){
        Customer updated = customerService.update(customerId,customer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/customers/username={username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(customerService.getCustomerByUsername(username));
    }
}
