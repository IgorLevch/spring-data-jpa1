package ru.geekbraines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.geekbraines.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

// можно наследоваться от интерфейса, который называется просто public interface Repository<T, ID> (он пустой)
    // Спринг ищет в проекте все расширения репозитория, смотрит на методы, которые там есть
    // в момент  вызова метода спринг перехватывает и идет в БД и делает сам то, что должен был делать метод.


    Optional<Customer> findByPhoneNumber(String number);  // просто декларируем метод и все! не пишем его реализацию
    // (шли к этому методу «по цепочке» от Контроллера)
    @Query("select c from Customer c where c.age = (select max(c2.age) from Customer c2)") // в правой части ищем максимальный возраст
    // в левой - кастомера,  у которого этот возраст максимальный  --- это по СКьюЭль тексту
    Optional<Customer> findOldest();
   // декларируем метод findOldest (вообще непонятно, каким образом он должен замапить это в запрос)


    // Спринг позволяет писать свои собственные СКьюЭль запросы . Прямо на языке СКЛ
    // для этого - пишем метод, называем его как хотим
    // ставим аннотацию  Query  и пишем запрос на языке JQL


    // если хочу, чтобы выводил имена по начальным буквам
    List<Customer> findByNameContaining (String name);

    // @Query(nativeQuery = true, value="native sql")  если хотим писать на нативном СКЛь


}
