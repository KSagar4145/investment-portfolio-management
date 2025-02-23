package com.investment.service;

import java.util.List;
import java.util.Optional;

import com.investment.pojos.Asset;

public interface IAssetService {

	// Create or Update Asset
	public Asset saveAsset(Asset asset) ;

	// Get All Assets
	public List<Asset> getAllAssets() ;

	// Get Asset by ID
	public Optional<Asset> getAssetById(Long assetId) ;

	// Delete Asset by ID
	public void deleteAsset(Long assetId) ;

	public List<Asset> saveAssets(List<Asset> assets);

}
