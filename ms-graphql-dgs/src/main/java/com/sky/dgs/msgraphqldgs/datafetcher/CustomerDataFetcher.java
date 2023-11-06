package com.sky.dgs.msgraphqldgs.datafetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.*;
import com.sky.dgs.msgraphqldgs.model.Account;
import com.sky.dgs.msgraphqldgs.model.Customer;
import com.sky.dgs.msgraphqldgs.repository.AccountRepository;
import com.sky.dgs.msgraphqldgs.repository.CustomerRepository;
import com.sky.dgs.msgraphqldgs.request.AccountInput;
import com.sky.dgs.msgraphqldgs.request.CustomerInput;
import com.sky.dgs.msgraphqldgs.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class CustomerDataFetcher {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;


    @DgsData(parentType = "Query", field = "getCustomers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @DgsData(parentType = "Mutation", field = "createCustomer")
    public Customer createCustomer(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
        Map<String,Object> input = dgsDataFetchingEnvironment.getArgument("input");
        CustomerInput customerInput = new ObjectMapper().convertValue(input, CustomerInput.class);
        Customer customer = Customer.builder()
                .contact(customerInput.getContact())
                .name(customerInput.getName())
                .gender(customerInput.getGender())
                .mail(customerInput.getMail())
                .accounts(mapCustomerAccounts(customerInput.getAccounts()))
                .build();
        return customerRepository.save(customer);
    }

    private List<Account> mapCustomerAccounts(List<AccountInput> accountIpnut) {
        return accountIpnut.stream().map(accInput -> Account.builder()
                .accountBalance(accInput.getAccountBalance())
                .accountNumber(accInput.getAccountNumber())
                .accountStatus(accInput.getAccountStatus())
                .build()).collect(Collectors.toList());
    }

    @DgsData(parentType = "Mutation", field = "deleteCustomer")
    public CustomerResponse deleteCustomer(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
        Integer id = dgsDataFetchingEnvironment.getArgument("customerNumber");
        Optional<Customer> customer = customerRepository.findById(Long.valueOf(id));

        if (customer.isEmpty()){
            return new CustomerResponse(Long.valueOf(id), "Customer Not Found");
        }
        customerRepository.delete(customer.get());
        return new CustomerResponse(Long.valueOf(id), "Customer deleted successfully");
    }

    @DgsData(parentType = "Mutation", field = "updateCustomer")
    public CustomerResponse updateCustomer(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
        Integer id = dgsDataFetchingEnvironment.getArgument("customerNumber");
        String name = dgsDataFetchingEnvironment.getArgument("name");
        if (id == null || name == null){
            return new CustomerResponse(Long.valueOf(id), "CustomerNo/Name is not present");
        }

        Optional<Customer> customer = customerRepository.findById(Long.valueOf(id));
        if (customer.isEmpty()){
            return new CustomerResponse(Long.valueOf(id), "Customer Not Found");
        }
        customer.get().setName(name);
        customerRepository.save(customer.get());
        return new CustomerResponse(Long.valueOf(id), "Customer updated successfully");
    }
}
