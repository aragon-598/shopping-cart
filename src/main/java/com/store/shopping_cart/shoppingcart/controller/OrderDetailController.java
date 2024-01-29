package com.store.shopping_cart.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.shopping_cart.shoppingcart.dto.OrderDetailDto;
import com.store.shopping_cart.shoppingcart.dto.OrderResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "order detail API", description = "order detail endpoints")
@RequestMapping("/api/v1/order-detail")
public interface OrderDetailController {
    
    @Operation(description = "Return order details by order", summary = "Return 404 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @GetMapping( path = "/by-order", produces = {"application/json"})
    default ResponseEntity<List<OrderDetailDto>> findOrderDetailByOrder(@RequestBody OrderResponse idOrder){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(description = "Return order details by id", summary = "Return 404 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})    
    @GetMapping( path = "/{idOrderDetail}", produces = {"application/json"})
    default ResponseEntity<OrderDetailDto> findOrderDetailById(@PathVariable int idOrderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(description = "Save orderDetail information", summary = "Return 201 if data is good")
    @ApiResponses(value = {@ApiResponse(responseCode="201",description="Succeded")})
   @PostMapping(path = "", produces = {"application/json"})
    default ResponseEntity<OrderDetailDto> createOrderDetail(@RequestBody OrderDetailDto orderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(description = "Update order detail information looking for id", summary = "Return 404 if the orde detail not exists")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @PutMapping(path = "/{idOrderDetail}", produces = {"application/json"})
    default ResponseEntity<OrderDetailDto> updateOrderDetail( @PathVariable("idOrderDetail") int idOrderDetail, @RequestBody OrderDetailDto orderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @DeleteMapping(path = "/{idOrderDetail}")
    default ResponseEntity<String> deletedOrderDetail(@PathVariable int idOrderDetail){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
