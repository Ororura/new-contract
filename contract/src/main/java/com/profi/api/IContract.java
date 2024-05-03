package com.profi.api;


import com.profi.model.Product;
import com.profi.model.User;
import com.wavesenterprise.sdk.contract.api.annotation.ContractAction;
import com.wavesenterprise.sdk.contract.api.annotation.ContractInit;
import com.wavesenterprise.sdk.contract.api.annotation.InvokeParam;

public interface IContract {

    @ContractInit
    void init();

    @ContractAction
    void hello(@InvokeParam(name = "name") String name);

    @ContractAction
    void createNewUser(@InvokeParam(name = "user") User user, @InvokeParam(name = "regions") String regions);

    @ContractAction
    void approveUser(@InvokeParam(name = "name") String name, @InvokeParam(name = "status") boolean status, @InvokeParam(name = "from") String from);

    @ContractAction
    void blockUser(@InvokeParam(name = "userName") String name, @InvokeParam(name = "status") boolean status, @InvokeParam(name = "sender") String sender);

    @ContractAction
    void createCard(@InvokeParam(name = "product") Product product, @InvokeParam(name = "form") String from);

    class Keys {
        public static final String CONTRACT_CREATOR = "CONTRACT_CREATOR";
        public static final String USER_MAPPING = "USER_MAPPING";
        public static final String COMPANY_MAPPING = "COMPANY_MAPPING";
    }

    class Role {
        public static final String ADMIN = "ADMIN";
        public static final String SUPPLIER = "SUPPLIER";
        public static final String DISTRIBUTOR = "DISTRIBUTOR";
        public static final String USER = "USER";
    }

    class Status {
        public static final String ON_CHECK = "ON_CHECK";
    }

}
