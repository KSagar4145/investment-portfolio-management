package com.investment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investment.pojos.Asset;
import com.investment.repository.IAssetRepository;

@Service
@Transactional
public class AssetServiceImpl implements IAssetService{
	
	   @Autowired
	    private IAssetRepository assetRepository;

	    // Create or Update Asset
	    public Asset saveAsset(Asset asset) {
	        return assetRepository.save(asset);
	    }

	    // Get All Assets
	    public List<Asset> getAllAssets() {
	        return assetRepository.findAll();
	    }

	    // Get Asset by ID
	    public Optional<Asset> getAssetById(Long assetId) {
	        return assetRepository.findById(assetId);
	    }

	    // Delete Asset by ID
	    public void deleteAsset(Long assetId) {
	        assetRepository.deleteById(assetId);
	    }	
	    
	    
	 // In the AssetService class
	    public List<Asset> saveAssets(List<Asset> assets) {
	        return assetRepository.saveAll(assets);  // saveAll() method will save a list of assets in the database
	    }


}
