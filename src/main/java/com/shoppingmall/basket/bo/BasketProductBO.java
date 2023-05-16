package com.shoppingmall.basket.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basket.dao.BasketProductMapper;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketProduct;
import com.shoppingmall.basket.model.BasketView;
import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@Service
public class BasketProductBO {
	
	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private ProductBO productBO;

	@Autowired
	private ProductOptionBO productOptionBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private BasketProductMapper basketProductMapper;
	
	public void addBasketProduct(int userId, int producId, String color, String size, int count) {
		// 유저의 장바구니 찾기
		Basket basket = basketBO.getBasketByUserId(userId);
		
		// 장바구니가 존재하지 않는다면 장바구니 생성
		if(basket == null) {
			basketBO.addBasket(userId);
			basket = basketBO.getBasketByUserId(userId);
		}
		
		// 상품이 장바구니에 존재하지 않는다면 BasketProduct 생성 후 장바구니에 추가
		int optionId = productOptionBO.getProductOptionByProductId(producId, color, size).getId();
		BasketProduct basketProduct = getBasketProductByBasketId(basket.getId(), producId, optionId);
		
		Product product = productBO.getProductByProductId(producId);
		int totalPrice = basket.getTotalPrice() + (product.getPrice() * count);
		if(basketProduct == null) {
			createBasketProduct(userId, basket.getId(), producId, optionId, count);
		} else { // 장바구니에 존재하면 수량만 증가
			updateBasketProduct(basketProduct.getId(), basketProduct.getCount() + count);
		}
		
		// 장바구니 총 금액 증가
		basketBO.updateTotalPrice(basket.getId(), totalPrice);
	}
	
	public void createBasketProduct(int userId, int basketId, int productId, int optionId, int count) {
		basketProductMapper.insertBasketProduct(userId, basketId, productId, optionId, count);
	}
	
	public void updateBasketProduct(int basketProductId, int count) {
		basketProductMapper.updateBasketProduct(basketProductId, count);
	}
	
	public BasketProduct getBasketProductByBasketId(int basketId, int productId, int productOptionId) {
		return basketProductMapper.selectBasketProductByBasketId(basketId, productId, productOptionId);
	}
	
	public List<BasketProduct> getBasketListByBasketId(int basketId){
		return basketProductMapper.selectBasketListByBasketId(basketId);
	}
	
	public List<BasketView> generateBasket(int userId){
		List<BasketView> basketViewList = new ArrayList<>();
		
		// 장바구니 정보
		Basket basket = basketBO.getBasketByUserId(userId);
		// 장바구니 상품 정보
		List<BasketProduct> basketProductList = getBasketListByBasketId(basket.getId());
		
		for(BasketProduct basketProduct : basketProductList) {
			BasketView basketView = new BasketView();
			basketView.setBasketProduct(basketProduct);
			
			Product product = productBO.getProductByProductId(basketProduct.getProductId());
			basketView.setProduct(product);
			
			ProductOption option = productOptionBO.getProductOptionByProductOptionId(basketProduct.getOptionId());
			basketView.setProductOption(option);
			
			User user = userBO.getUserByUserId(basketProduct.getUserId());
			basketView.setUser(user);
			
			basketViewList.add(basketView);
		}
		
		return basketViewList;
	}
	
	public int deleteBasketProductByBasketProductId(int userId, int basketId, int basketProductId) {
		BasketProduct basketProduct = getBasketProductByBasketProductId(basketProductId);
		Product product = productBO.getProductByProductId(basketProduct.getProductId());
		int productPrice = product.getPrice();
		int count = basketProduct.getCount();
		
		// 장바구니 총 금액 변경
		int totalPrice = basketBO.getBasketByUserId(userId).getTotalPrice();
		totalPrice -= productPrice * count;
		basketBO.updateTotalPrice(basketId, totalPrice);
		
		return basketProductMapper.deleteBasketProductByBasketProductId(basketProductId);
	}
	
	public BasketProduct getBasketProductByBasketProductId(int basketProductId) {
		return basketProductMapper.selectBasketProductByBasketProductId(basketProductId);
	}
}
