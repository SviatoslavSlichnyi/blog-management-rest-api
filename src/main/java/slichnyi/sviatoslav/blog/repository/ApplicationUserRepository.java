package slichnyi.sviatoslav.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slichnyi.sviatoslav.blog.entity.ApplicationUser;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String authorUsername);

    Optional<ApplicationUser> findByEmail(String email);
}
