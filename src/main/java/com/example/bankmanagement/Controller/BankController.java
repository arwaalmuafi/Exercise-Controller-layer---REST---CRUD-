package com.example.bankmanagement.Controller;

import com.example.bankmanagement.ApiRespons.ApiResopnse;
import com.example.bankmanagement.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/bank")
public class BankController {
    ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Customer> costumer(){
        return customers;
    }
    @PostMapping("/add")
    public ApiResopnse addCostumer(@RequestBody Customer costumer){
       customers.add(costumer);
        return new ApiResopnse("costumer added") ;
    }

    @PutMapping("/update/{index}")
    public ApiResopnse updateCostumer(@PathVariable int index, @RequestBody Customer c) {
        customers.set(index, c);

        return new ApiResopnse("customer updated");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResopnse deleteCostumer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResopnse("Costumer deleted") ;
    }

    @PutMapping("/desposit/{index}")
    public ApiResopnse costumerDeposit(@PathVariable int index, @RequestParam double amount) {
        if (index >= 0 && index < customers.size()) {
            Customer customer = customers.get(index);
            customer.setBalance(customer.getBalance() + amount);
            return new ApiResopnse("Deposit successful. New balance: " + customer.getBalance());
        } else {
            return new ApiResopnse("Customer not found");
        }
    }

    @PutMapping("withdraw/{index}")
    public ApiResopnse customerWithdraw(@PathVariable int index, @RequestBody double amount) {
        if (index >= 0 && index < customers.size()) {
            Customer customer = customers.get(index);
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                return new ApiResopnse("Withdrawal successful. New balance: " + customer.getBalance());
            } else {
                return new ApiResopnse("Insufficient balance");
            }
        } else {
            return new ApiResopnse("Customer not found");
        }
    }

}


