package com.itda.C_TeamProject.user;

import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserFindNameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByEmailAndPhoneNumberAndDateOfBirth(String email, String phoneNumber, String dateOfBirth);
}
