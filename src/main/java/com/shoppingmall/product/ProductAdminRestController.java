package com.shoppingmall.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.product.bo.ProductAdminBO;
import com.shoppingmall.product.bo.ProductImageBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.bo.ProductOptionAdminBO;

@RequestMapping("/product_admin")
@RestController
public class ProductAdminRestController {

	@Autowired
	private ProductAdminBO productAdminBO;
	
	@Autowired
	private ProductOptionAdminBO poaBO;
	
	@Autowired
	private ProductImageBO productImageBO;
	
	@PostMapping("/product_create")
	public Map<String, Object> productCreate(
			@RequestParam("name") String name,
			@RequestParam("information") String information,
			@RequestParam("price") int price,
			@RequestParam("mainImage") MultipartFile mainImage,
			@RequestParam("detailedInfo") String detailedInfo,
			@RequestParam("gender") String gender,
			@RequestParam("productImages") List<MultipartFile> files){
		// db insert
		Product product = new Product();
		product.setName(name);
		product.setInformation(information);
		product.setPrice(price);
		product.setDetailedInfo(detailedInfo);
		product.setGender(gender);
		int rowCount = productAdminBO.addProduct(product, mainImage);

		int productId = product.getId();
		// 이미지 insert
		for(int i = 0; i < files.size(); i++) {
			productImageBO.addProductImage(productId, name, files.get(i));
		}
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "상품 등록에 성공했습니다.");
		} else {
			result.put("code", 500);
			result.put("erroMessage", "상품 등록에 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/product_update")
	public Map<String, Object> productUpdate(
			@RequestParam("productId") int productId,
			@RequestParam("name") String name,
			@RequestParam("information") String information,
			@RequestParam("price") int price,
			@RequestParam(value="mainImage", required=false) MultipartFile mainImage,
			@RequestParam("detailedInfo") String detailedInfo,
			@RequestParam("gender") String gender,
			@RequestParam("productImages") List<MultipartFile> files){
		// db update
		int rowCount = productAdminBO.updateProductByproductId(productId, name, information, price, mainImage, detailedInfo, gender, files);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "상품 수정에 성공하였습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상품 수정에 실패하였습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/product_delete")
	public Map<String, Object> productDelete(
			@RequestParam("productId") int productId) {
		// product delete
		int rowCount = productAdminBO.deleteProductByProductId(productId);
		// product option delete
		poaBO.deleteProductOptionByProductId(productId);
		
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "상품 삭제에 성공하셨습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상품 삭제에 실패했습니다.");
		}
		
		return result;
	}
}
