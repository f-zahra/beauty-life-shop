package com.beautyLifeShop.ecom.service;


import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.models.*;
import com.beautyLifeShop.ecom.repository.AddressRepository;
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

    @Autowired
    private AddressRepository addressRepository;


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
        List<Order> foundOrders = orderRepository.findAllOrders();
      return  foundOrders;
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
    public int updateOrder(Long id, OrderRequest orderRequest)throws ExceptionHandler{
        //find order
        // Retrieve the existing order
        Order orderFound = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
       UserRole user_role = orderFound.getUser().getRole();
        //get address

       //user can only cancel order
       if(user_role.equals(UserRole.USER)){
          orderRequest.setOrderStatus(OrderStatus.CANCELED);
       }

       int updateCount =   orderRepository.updateOrder(
                id,
                orderRequest.getOrderStatus(),
                orderRequest.getShippingAddress().getId()
        );
        if (updateCount == 0) {
            throw new RuntimeException("Order update failed");
        }

        return updateCount;
    }



    /*delete*/
    //only admin can delete order
    public  void deleteOrder(Long id){
        Order order = this.getOrder(id);
        orderRepository.delete(order);
    }
}
