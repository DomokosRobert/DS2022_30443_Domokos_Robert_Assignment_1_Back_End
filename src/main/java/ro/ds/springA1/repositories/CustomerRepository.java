package ro.ds.springA1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ds.springA1.entities.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findByName(String name);

   /// @Query(value = "SELECT U " +
               // "FROM Users u " +
               // "WHERE u.username = :username " +
              //  "AND u.password =: password ")
    ///User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<Customer> findByUsername(String username);

}
