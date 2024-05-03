package com.profi.app;

import com.profi.api.IContract;
import com.profi.model.Company;
import com.profi.model.Product;
import com.profi.model.User;
import com.profi.utils.CheckStatus;
import com.wavesenterprise.sdk.contract.api.annotation.ContractHandler;
import com.wavesenterprise.sdk.contract.api.domain.ContractCall;
import com.wavesenterprise.sdk.contract.api.state.ContractState;
import com.wavesenterprise.sdk.contract.api.state.TypeReference;
import com.wavesenterprise.sdk.contract.api.state.mapping.Mapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.profi.api.IContract.Keys.*;

@ContractHandler
public class Contract implements IContract {

    private final Mapping<User> userMapping;
    private final ContractState contractState;
    private final ContractCall contractCall;
    private final Mapping<Company> companyMapping;


    public Contract(ContractState contractState, ContractCall contractCall) {
        this.contractState = contractState;
        this.contractCall = contractCall;
        this.userMapping = contractState.getMapping(new TypeReference<>() {
        }, USER_MAPPING);
        this.companyMapping = contractState.getMapping(new TypeReference<Company>() {
        }, COMPANY_MAPPING);
    }

    @Override
    public void init() {
        this.contractState.put(CONTRACT_CREATOR, contractCall.getCaller());
    }

    @Override
    public void hello(String name) {
        System.out.println("Hello test: " + name);
    }

    @Override
    public void approveUser(String name, boolean status, String from) {
        Optional<User> approveUser = this.userMapping.tryGet(name);
        Optional<User> fromUser = this.userMapping.tryGet(from);

        if (approveUser.isEmpty()) {
            throw new IllegalStateException("Approve user not found!");
        }

        if (fromUser.isEmpty()) {
            throw new IllegalStateException("From user not found!");
        }
        CheckStatus.onlyAdmin(fromUser.get().getRole());

        approveUser.get().setApproved(status);
        Optional<Company> company = this.companyMapping.tryGet(approveUser.get().getCompany());

        if (company.isEmpty()) {
            Company newCompany = new Company(approveUser.get().getCompany());
            newCompany.addUser(approveUser.get().getName());
            this.companyMapping.put(newCompany.getCompanyName(), newCompany);
        }

        this.userMapping.put(approveUser.get().getName(), approveUser.get());

    }

    @Override
    public void createNewUser(User user, String regions) {
        System.out.println(user);
        user.setSupplyRegions(Arrays.stream(regions.split(",")).toList());

        this.userMapping.put(user.getName(), user);
    }

    @Override
    public void blockUser(String name, boolean status, String sender) {
        Optional<User> blockUser = this.userMapping.tryGet(name);

        Optional<User> fromUser = this.userMapping.tryGet(sender);

        if (blockUser.isEmpty()) {
            throw new IllegalStateException("Approve user not found!");
        }

        if (fromUser.isEmpty()) {
            throw new IllegalStateException("From user not found!");
        }

        CheckStatus.onlyAdmin(fromUser.get().getRole());
        blockUser.get().setBlock(status);
        this.userMapping.put(blockUser.get().getName(), blockUser.get());
    }

    @Override
    public void createCard(Product product, String from) {
        Optional<User> userFrom = this.userMapping.tryGet(from);

        if(userFrom.isEmpty()) {
            throw new IllegalStateException("User from not found");
        }

        Optional<Company> company = this.companyMapping.tryGet(userFrom.get().getCompany());

        if(company.isEmpty()) {
            throw new IllegalStateException("Company not found!");
        }

        company.get().addProduct(product);

        this.companyMapping.put(userFrom.get().getCompany(), company.get());
    }

    
}
