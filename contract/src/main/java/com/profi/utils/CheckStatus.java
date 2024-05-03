package com.profi.utils;

import com.profi.api.IContract;

import java.util.Objects;

public class CheckStatus {

    public static void onlyAdmin(String role) {
        if(Objects.equals(role, IContract.Role.ADMIN)) {
            throw new IllegalStateException("Only admin!");
        }
    }

    public static void onlySupplier(String role) {
        if(Objects.equals(role, IContract.Role.SUPPLIER)) {
            throw new IllegalStateException("Only supplier");
        }
    }
}
