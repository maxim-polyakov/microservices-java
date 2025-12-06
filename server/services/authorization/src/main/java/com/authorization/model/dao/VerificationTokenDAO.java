package com.authorization.model.dao;

import com.authorization.model.LocalUser;
import com.authorization.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for the VerificationToken data.
 */
@Repository
public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {

  Optional<VerificationToken> findByToken(String token);

  void deleteByUser(LocalUser user);

  List<VerificationToken> findByUser_IdOrderByIdDesc(Long id);

}
