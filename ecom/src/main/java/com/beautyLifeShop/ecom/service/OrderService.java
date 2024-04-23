package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.models.*;
import com.beautyLifeShop.ecom.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;



    /*add*/
    public Order placeOrder(Order order, HttpSession session) throws ExceptionHandler{

        //get shopping cart

        ShoppingCart userCart = shoppingCartService.getCart(session);
       //get user
        User user = userService.getConnectedUser();

       //creating new order items and adding them to order
        if(!userCart.isEmpty()){
       userCart.getCartItems().stream().forEach(i -> {
           OrderItem orderItem = new OrderItem();
           orderItem.setQuantity(i.getQuantity());
            orderItem.setOrder(order);
             orderItem.setProduct(i.getProduct());
             //add item to order
                order.getItems().add(orderItem);
       });}else {
            throw  new RuntimeException("cart is empty");
        }
       //set user

        order.setUser(user);
        //when a user submit new shipping address form it uses addAddress func
        orderRepository.save(order);
       session.setAttribute("shoppingCart", null);

        return  order;

    }

   /*public Order addOrder*/

    /*read*/
    //get all
    public List<Order> getOrders() throws ExceptionHandler {

      return orderRepository.findAll();
    }
    //get  order by id
    public  Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(()-> {throw  new RuntimeException("order not found");});
    }

    //get orders by user id
    public List<Order> getUserOrders(Long userId) throws ExceptionHandler{

        List<Order> order =  orderRepository.findByUserId(userId);
        if(!order.isEmpty())
            return order;
        else
        { throw new RuntimeException("no order found");}


    }

    /*update*/
    /*
    *  user can update shipping address, cancel order
    * SP can shipping address, update order status
    * */
    public Order updateOrder(Long id, OrderRequest orderRequest)throws ExceptionHandler{
        //find order
       Order orderFound = this.getOrder(id);
       orderFound.setShippingAddress(orderRequest.getShippingAddress());
       //user can only cancel order
       if(orderFound.getUser().getAuthorities().equals("ROLE_USER")){
           orderFound.setOrderStatus(OrderStatus.CANCELED);
       }else { orderFound.setOrderStatus(orderRequest.getOrderStatus());}
       orderFound.setOrderStatus(orderRequest.getOrderStatus());
       return orderRepository.save(orderFound);

    }



    /*delete*/
    //only admin can delete order
    public  void deleteOrder(Long id){
        Order order = this.getOrder(id);
        orderRepository.delete(order);
    }
}
