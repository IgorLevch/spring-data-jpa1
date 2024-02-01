package ru.geekbraines.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.geekbraines.model.Customer;
import ru.geekbraines.repository.CustomerRepository;

@Component
public class CustomerDataGenerator {
    @Autowired
    private CustomerRepository customerRepository;
    @EventListener(ApplicationReadyEvent.class)  // эта аннотация говорит Спрингу, что этот метод надо запустить, когда данное собитие в Контексте произойдет
    // и данное событие происходит , когда сервис запустился.
    // Когда пишется лог: : Started Application in 10.837 seconds (process running for 11.954) -- сразу запускается этот метод
    public void generateDataOnStartup(){  // метод будет вызываться в тот момент, когда приложение будет запускаться

        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setPhoneNumber(faker.number().digits(6));
            customer.setAge(faker.number().numberBetween(20,60));

            customerRepository.save(customer);
            // Спринг перехватывает вызов этого метода. Он знает, какая БД внутри себя.
            // И Спринг сам открывает сессию, выполняет запрос, коммитит его и закрывает сессию -- это то, что делает Спринг.Дата JPA
            
        }

    }


}
