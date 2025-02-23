package com.investment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investment.pojos.Asset;
import com.investment.service.IAssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {

	@Autowired
	private IAssetService assetService;

	// Create or Update Asset
	@PostMapping
	public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
		System.out.println(asset.toString());

		Asset savedAsset = assetService.saveAsset(asset);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAsset);
	}

	// Get All Assets
	@GetMapping
	public ResponseEntity<List<Asset>> getAllAssets() {
		List<Asset> assets = assetService.getAllAssets();
		return ResponseEntity.status(HttpStatus.CREATED).body(assets);
	}

	// Get Asset by ID
	@GetMapping("/{id}")
	public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
		Optional<Asset> asset = assetService.getAssetById(id);
		//asset.map((response)->ResponseEntity.ok(response)).orElseGet(ResponseEntity.notFound()::build);
		return asset.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Delete Asset by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAsset(@PathVariable Long id) {
		assetService.deleteAsset(id);
		return ResponseEntity.ok("Asset with ID " + id + " has been successfully deleted.");
		//return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/assetList")
	public ResponseEntity<List<Asset>> createAssets(@RequestBody List<Asset> assets) {
	    List<Asset> savedAssets = assetService.saveAssets(assets);  // Assuming saveAssets() method is implemented in the service
	    return ResponseEntity.ok(savedAssets);
	}
}
