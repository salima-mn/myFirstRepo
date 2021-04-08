package com.android.android_repo.repository;

import com.android.android_repo.entity.IMEI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface IMEIRepository extends JpaRepository<IMEI, Integer> {

    IMEI findByValue(String value);
}
