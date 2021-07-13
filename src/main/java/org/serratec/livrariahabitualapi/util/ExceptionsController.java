package org.serratec.livrariahabitualapi.util;

import org.serratec.livrariahabitualapi.exceptions.AddressNotAssociatedWithClientException;
import org.serratec.livrariahabitualapi.exceptions.CategoryReferencedByProductException;
import org.serratec.livrariahabitualapi.exceptions.CpfNotEditableException;
import org.serratec.livrariahabitualapi.exceptions.ItemAlreadyExistsException;
import org.serratec.livrariahabitualapi.exceptions.ItemNotFoundException;
import org.serratec.livrariahabitualapi.exceptions.ProductStockLessThanRequestedException;
import org.serratec.livrariahabitualapi.exceptions.PurchaseOrderNotEditableException;
import org.serratec.livrariahabitualapi.exceptions.PurchaseOrderWithNoProductException;
import org.serratec.livrariahabitualapi.exceptions.QuantityCannotBeZeroException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(CpfNotEditableException.class)
	public ResponseEntity<String> handleCpfNotEditableException(CpfNotEditableException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(PurchaseOrderNotEditableException.class)
	public ResponseEntity<String> handlePurchaseOrderNotEditableException(PurchaseOrderNotEditableException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(PurchaseOrderWithNoProductException.class)
	public ResponseEntity<String> handlePurchaseOrderWithNoProductException(
			PurchaseOrderWithNoProductException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(CategoryReferencedByProductException.class)
	public ResponseEntity<String> handleCategoryReferencedByProductException(
			CategoryReferencedByProductException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(ProductStockLessThanRequestedException.class)
	public ResponseEntity<String> handleProductStockLessThanRequestedException(
			ProductStockLessThanRequestedException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(QuantityCannotBeZeroException.class)
	public ResponseEntity<String> handleQuantityCannotBeZeroException(QuantityCannotBeZeroException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(AddressNotAssociatedWithClientException.class)
	public ResponseEntity<String> handleAddressNotAssociatedWithClientException(
			AddressNotAssociatedWithClientException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<String> handleItemAlreadyExistsException(ItemAlreadyExistsException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException exception) {
		return ResponseEntity.notFound().header("x-erro-msg", exception.getMessage()).build();
	}
}
