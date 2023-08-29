package com.soulcode.goserviceapp.repository;

import com.soulcode.goserviceapp.domain.UsuarioLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLogRepository extends MongoRepository<UsuarioLog, String> {

}
