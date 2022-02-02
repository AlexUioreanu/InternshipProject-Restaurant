package com.example.RestaurantCatalog.api;

import com.example.RestaurantCatalog.model.Order;
import com.example.RestaurantCatalog.model.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/restaurant")
public class RestaurantApi {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @PatchMapping("/updateOrder/{id}")
    @RolesAllowed("admin")
    public ResponseEntity<String> updateOrder(@RequestBody Order order, @PathVariable Long id, HttpServletRequest request) {
//        restTemplate.patchForObject("http://localhost:9081/order/update/" + id, order, Order.class);

        String[] token = new String[1];
        if (request.getHeader("authorization") != null) {
            token = request.getHeader("authorization").split(" ");
        }

        String[] finalToken = token;
        webClientBuilder.build()
                .patch()
                .uri("http://localhost:9081/order/update/" + id)
                .headers(header -> header.setBearerAuth(finalToken[1]))
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();

        return ResponseEntity.ok("Success UPDATED!!!!");
    }


    @PostMapping
    @RolesAllowed({"user", "admin"})
    public ResponseEntity bookOrder(@RequestBody Order order, HttpServletRequest request) {

//        restTemplate.postForObject("http://localhost:9081/order/", order, Order.class);
//        restTemplate.postForObject("http://localhost:9082/write", order, Order.class);

        String[] token = new String[1];
        if (request.getHeader("authorization") != null) {
            token = request.getHeader("authorization").split(" ");
        }

        String[] finalToken = token;
        webClientBuilder.build()
                .post()
                .uri("http://localhost:9081/order")
                .headers(header -> header.setBearerAuth(finalToken[1]))
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();

        webClientBuilder.build()
                .post()
                .uri("http://localhost:9082/write")
                .headers(header -> header.setBearerAuth(finalToken[1]))
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();

        return ResponseEntity.ok("Success Booked Order!!!!");
    }

    @GetMapping("/getOrder/{id}")
    @RolesAllowed("admin")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id, HttpServletRequest request) {
//        Order order = restTemplate.getForObject("http://localhost:9081/order/get/" + id, Order.class);

        String[] token = new String[1];
        if (request.getHeader("authorization") != null) {
            token = request.getHeader("authorization").split(" ");
        }

        String[] finalToken = token;
        return ResponseEntity.ok(webClientBuilder.build()
                .get()
                .uri("http://localhost:9081/order/get/" + id)
                .headers(header -> header.setBearerAuth(finalToken[1]))
                .retrieve()
                .bodyToMono(Order.class)
                .block());
    }


    @GetMapping("/getOrder/getAllOrders")
    @RolesAllowed("admin")
    public ResponseEntity<OrderList> getAllOrder(HttpServletRequest request) {

//        OrderList orderList = restTemplate.getForObject("http://localhost:9081/order/allOrders", OrderList.class);

        String[] token = new String[1];
        if (request.getHeader("authorization") != null) {
            token = request.getHeader("authorization").split(" ");
        }

        String[] finalToken = token;
        return ResponseEntity.ok(webClientBuilder.build()
                .get()
                .uri("http://localhost:9081/order/allOrders")
                .headers(header -> header.setBearerAuth(finalToken[1]))
                .retrieve()
                .bodyToMono(OrderList.class)
                .block());
    }


    @DeleteMapping("/deleteOrder/{id}")
    @RolesAllowed("admin")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id, HttpServletRequest request) {

//        restTemplate.delete("http://localhost:9081/order/delete/" + id, Order.class);

        String[] token = new String[1];
        if (request.getHeader("authorization") != null) {
            token = request.getHeader("authorization").split(" ");
        }

        String[] finalToken = token;
        webClientBuilder.build()
                .delete()
                .uri("http://localhost:9081/order/delete/" + id)
                .headers(header -> header.setBearerAuth(finalToken[1]))
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return ResponseEntity.ok("Success DELETED!!!!");
    }
}
