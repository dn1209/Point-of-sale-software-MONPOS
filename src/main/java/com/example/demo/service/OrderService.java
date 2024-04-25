package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.DetailOrder;
import com.example.demo.model.Orders;
import com.example.demo.model.Store;
import com.example.demo.model.payload.Response.customer.ListCustomer;
import com.example.demo.model.payload.Response.orders.ApiOrdersResponse;
import com.example.demo.model.payload.Response.orders.ListOrders;
import com.example.demo.model.payload.Response.orders.OrderCount;
import com.example.demo.model.payload.request.order.DetailOrderRequest;
import com.example.demo.model.payload.request.order.OrderRequest;
import com.example.demo.repository.DetailOrderRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    StoreService storeService;
    @Autowired
    ProductService productService;
    @Autowired
    DetailOrderRepository detailOrderRepository;
    @Autowired
    OrderRepository orderRepository;
    public void saveOrder(OrderRequest orderRequest,Long sId){
          LocalDate today = LocalDate.now();

        Store store = storeService.findStoreById(sId);
        Orders orders = new Orders();
        String code = "DH" + String.format("%05d", (orderRepository.getLastOrdersId() +1)); // Ví dụ mã PD00001, PD00002, ...
        orders.setStore(store);
        orders.setOutputCode(code);
        orders.setCustomerId(Integer.parseInt(orderRequest.getCustomer_id()));
        orders.setSellDate(today);
        orders.setNotes(orderRequest.getNotes());
        orders.setPaymentMethod(Integer.parseInt(orderRequest.getPayment_method()));
        orders.setCustomerPay(Float.valueOf(orderRequest.getCustomer_pay()));
        orders.setOrderStatus(1);
        orderRepository.save(orders);
        for (DetailOrderRequest orderRequest1 : orderRequest.getDetail_order()) {
            productService.updateProductWithPRDSLS(Float.valueOf(orderRequest1.getQuantity()),Long.parseLong(orderRequest1.getId()));
            DetailOrder detailOrder = new DetailOrder();
            detailOrder.setProductId(Long.parseLong(orderRequest1.getId()));
            detailOrder.setPrice(Integer.parseInt(orderRequest1.getPrice()));
            detailOrder.setQuantity(Integer.parseInt(orderRequest1.getQuantity()));
            detailOrder.setOrder(orders);
            detailOrderRepository.save(detailOrder);
        }

    }
    public ApiOrdersResponse getListOrder(LocalDate startsDateTime,LocalDate endsDateTime,Long sId){
        List<Orders> ordersList = orderRepository.findOrdersBetweenDates(sId,startsDateTime,endsDateTime);
        List<DetailOrderRequest> newList = new ArrayList<>();
        List<ListOrders> customerMappedList = ordersList.stream().map(orders -> {
            List<DetailOrderRequest> detailOrderList = orders.getDetailOrders().stream()
                    .map(detailOrder -> new DetailOrderRequest(
                            String.valueOf(detailOrder.getId()),
                            String.valueOf(detailOrder.getQuantity()),
                            String.valueOf(detailOrder.getPrice()),
                            String.valueOf(detailOrder.getDiscount())
                    ))
                    .collect(Collectors.toList());

            return new ListOrders(
                    String.valueOf(orders.getId()),
                    orders.getOutputCode(),
                    String.valueOf(orders.getCustomerId()),
                    String.valueOf(orders.getStore().getId()),
                    String.valueOf(orders.getSellDate()),
                    orders.getNotes(),
                    String.valueOf(orders.getPaymentMethod()),
                    String.format("%.2f", orders.getTotalPrice()),
                    String.format("%.2f", orders.getTotalPrice()),
                    String.valueOf(orders.getCoupon()),
                    String.format("%.2f", orders.getCustomerPay()),
                    String.format("%.2f", orders.getTotalMoney()),
                    String.valueOf(orders.getTotalQuantity()),
                    String.valueOf(orders.getLack()),
                    String.valueOf(orders.getOrderStatus()),
                    String.valueOf(orders.getDeleted()),
                    String.valueOf(orders.getSellDate()),
                    String.valueOf(orders.getSellDate()),
                    "0",
                    "0",
                    String.valueOf(orders.getStore().getId()),
                    "0",
                    detailOrderList
            );
        }).collect(Collectors.toList());

        long totalMoney = 0;
        long totalDebt = 0;
        for (Orders customer : ordersList) {
            totalMoney += customer.getTotalMoney();
            totalDebt += customer.getLack();
        }
        OrderCount orderCount = new OrderCount(
                String.valueOf(ordersList.size()),
                String.valueOf(totalMoney),
                String.valueOf(totalDebt)
        );
        ApiOrdersResponse apiOrdersResponse = new ApiOrdersResponse(
                "0",
                orderCount,
                customerMappedList
        );
        return apiOrdersResponse;

    }
}
