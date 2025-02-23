package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.Asset;

public interface IAssetRepository extends JpaRepository<Asset, Long>{

}
