package org.example.repositories;

import java.util.Optional;

import org.example.tables.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This annotation indicates that this interface is a Spring Data JPA repository.
// It provides CRUD (Create, Read, Update, Delete) operations for the 'Users' entity and uses 'Long' as the type for the entity's primary key.
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // The 'findByUsername' method is a query method that is automatically generated
    // by Spring Data JPA.
    // It will execute a query like: 'SELECT * FROM users WHERE username = ?1'
    // The 'Optional<Users>' return type means the result may be empty if no user is
    // found with the given username.
    Optional<Users> findByUsername(String username);

    // This is a custom query method using the @Query annotation to define a JPQL
    // query.
    // JPQL (Java Persistence Query Language) queries operate on entities, not
    // directly on database tables.
    // Here, the query fetches the 'username' field from the 'Users' entity where
    // the 'id' matches the parameter.
    // The '?' syntax is used for parameter substitution (i.e., 'id = ?1' binds the
    // first parameter to the query).
    @Query("SELECT u.username FROM Users u WHERE u.id = ?1")
    String findUsernameById(Long id);

}
