package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Store;
import com.example.demo.model.payload.Response.customer.ApiCustomerResponse;
import com.example.demo.model.payload.Response.customer.CustomerCount;
import com.example.demo.model.payload.Response.customer.ListCustomer;
import com.example.demo.model.payload.Response.groupProduct.GroupProductMapped;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    StoreService storeService;
    public ApiCustomerResponse getCustomerByStoreId(Long sId, String keyword){
        List<Customer> customers = customerRepository.getCustomerByStoreIdAndKeyquord(sId,keyword);
        List<ListCustomer> customerMappedList = customers.stream().map(
                customer -> new ListCustomer(
                        String.valueOf(customer.getId()),
                        customer.getCustomerCode(),
                        customer.getCustomerName(),
                        customer.getCustomerPhone(),
                        customer.getCustomerAddr(),
                        customer.getSellDateLast(),
                        String.valueOf(customer.getTotalMoney()),
                        String.valueOf(customer.getTotalDebt())
                )).collect(Collectors.toList());
        long totalMoney = 0;
        long totalDebt = 0;

        for (Customer customer : customers) {
            totalMoney += customer.getTotalMoney();
            totalDebt += customer.getTotalDebt();
        }
        CustomerCount customerCount = new CustomerCount(String.valueOf(totalMoney),
                String.valueOf(totalDebt),
                String.valueOf(customerMappedList.size())
        );

        ApiCustomerResponse apiCustomerResponse = new ApiCustomerResponse(
                "0",
                customerCount,
                customerMappedList
        );
        return apiCustomerResponse;


    }



    public Customer saveCustomer(Long sId,String cName, String cCode,
                                 String cPhone, String cEmail, String cAddr, String gender, String birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Store store = storeService.findStoreById(sId);
        Customer customer = new Customer();
        customer.setCustomerName(cName);
        customer.setCustomerAddr(cAddr);
        customer.setCustomerEmail(cEmail);
        customer.setCustomerPhone(cPhone);
        customer.setCustomerGender(Integer.parseInt(gender));
        customer.setBirthday(dateFormat.parse(birthday));
        String a = cCode;
        if(cCode.isEmpty()){
            a = "CS" + String.format("%05d", (customerRepository.getLastCustomerId() +1)); // Ví dụ mã PD00001, PD00002, ...
        }
        customer.setCustomerCode(a);
        customer.setStore(store);
        customer.setCustomerStatus(0);
        return customerRepository.save(customer);
    }
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
