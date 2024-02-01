package ru.geekbraines.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbraines.model.Customer;
import ru.geekbraines.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);  // этот метод задекларирован в интерфейсе CrudRepository
        // в этом интерфейсе сосредтотчены все стандартные методы.
        // есть еще JpaRepository - в нем больше методов чем в CrudRepository
        // Optional - это контейнер, в котором значения либо есть, либо нет
    }


    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByNumber(String number) {
        return customerRepository.findByPhoneNumber(number);
    }

    public Optional<Customer> findOldest() {
        return customerRepository.findOldest();
    }

    public List<Customer> findByNameLike(String name){
        return customerRepository.findByNameContaining(name);
    }

}
