package ru.geekbraines.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbraines.model.Customer;
import ru.geekbraines.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    // еще один endpoint
    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @GetMapping("by_number") // еще один ендпойнт - поулчение пользователя по номеру телефона
    public Customer findByNumber(@RequestParam String number){
        return customerService.findByNumber(number).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/oldest")
    public Customer findOldest(){
        return customerService.findOldest().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

        @GetMapping("/search")
            public List<Customer> findByName(@RequestParam String name){
                return customerService.findByNameLike(name);
        }



}
