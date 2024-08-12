package repository;

import entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();

    List<Customer> findByName(String name);

    List<Customer> findByPhoneOrEmail(int phone, String email);

    @Query("SELECT c FROM Customer c WHERE c.id = ?1")
    List<Customer> findByIdAsList(Integer id);

    @Query("SELECT c FROM Customer c WHERE c.sex = 'Male' AND YEAR(CURRENT_DATE) - YEAR(c.birdate) BETWEEN 20 AND 30")
    List<Customer> findMenAgedBetween20And30();
}