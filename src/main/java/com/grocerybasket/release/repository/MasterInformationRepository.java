package com.grocerybasket.release.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.grocerybasket.release.entity.MasterInformation;

public interface MasterInformationRepository extends JpaRepository<MasterInformation, Integer>, JpaSpecificationExecutor<MasterInformation> {
}
