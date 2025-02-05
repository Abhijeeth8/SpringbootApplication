package com.healthcare.healthcare.Repositories;

import com.healthcare.healthcare.Entities.UserT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.InterfaceAddress;
@Repository
public interface UserRepository extends JpaRepository<UserT, Integer> {
    UserT findByEmail(String email);
}
