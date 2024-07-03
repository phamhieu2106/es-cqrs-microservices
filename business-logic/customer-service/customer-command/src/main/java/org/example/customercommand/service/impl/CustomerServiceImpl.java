package org.example.customercommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.customercommand.client.CustomerQueryClient;
import org.example.customercommand.service.CustomerHandlerService;
import org.example.customercommand.service.CustomerService;
import org.example.customerdomain.command.CreateCustomerCommand;
import org.example.customerdomain.command.DeleteCustomerCommand;
import org.example.customerdomain.command.UpdateCustomerCommand;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;
import org.example.sharedlibrary.constant.RegexConstant;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerHandlerService handlerService;
    private final CustomerQueryClient queryClient;

    @Override
    public WrapperResponse create(CreateCustomerCommand command, String username) {
        try {
            if (isValidAddRequest(command)) {
                command.setCreatedBy(username);
                handlerService.handleCreate(command);
            } else {
                return new WrapperResponse().fail(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST
                );
            }

        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }

    @Override
    public WrapperResponse update(UpdateCustomerCommand command, String username) {
        try {
            if (isValidUpdateRequest(command.getCustomerId(), command)) {
                command.setCreatedBy(username);
                handlerService.handleUpdate(command);
            } else {
                return new WrapperResponse().fail(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST
                );
            }
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }

    @Override
    public WrapperResponse delete(DeleteCustomerCommand command, String username) {
        try {
            if (command.getCustomerId() == null || command.getCustomerId().isBlank()) {
                return new WrapperResponse().fail(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST
                );
            }
            command.setCreatedBy(username);
            handlerService.handleDelete(command);
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }

    private boolean isValidAddRequest(CreateCustomerCommand request) {
        if (request != null) {

            if (request.getGender() == null) {
                return false;
            }

            if (request.getPhoneNumber() == null
                || request.getPhoneNumber().isBlank() || request.getPhoneNumber().isEmpty()
                || isValidPhoneNumber(request.getPhoneNumber())
                || this.queryClient.existsByPhoneNumber(request.getPhoneNumber())) {
                return false;
            }

            if (request.getEmail() == null
                || request.getEmail().isBlank() || request.getEmail().isEmpty()
                || isValidEmail(request.getEmail())
                || this.queryClient.existsByEmail(request.getEmail())) {
                return false;
            }

            if (request.getProof() == null
                || isValidProof(request.getProof())) {
                return false;
            }

            if (request.getJobName() == null || request.getJobName().isBlank()
                || request.getJobName().isEmpty()) {
                return false;
            }

            if (request.getDateOfBirth() == null) {
                return false;
            }

            for (AddressModel addressModel : request.getAddressModels()) {
                if (isValidAddress(addressModel)) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    private boolean isValidUpdateRequest(String customerId, UpdateCustomerCommand request) {
        if (request != null) {
            if (request.getGender() == null) {
                return false;
            }

            if (request.getPhoneNumber() == null
                || request.getPhoneNumber().isBlank() || request.getPhoneNumber().isEmpty()
                || isValidPhoneNumber(request.getPhoneNumber())
                || this.queryClient.existsByPhoneNumberAndIdIsNot(
                    request.getPhoneNumber(), customerId)) {
                return false;
            }

            if (request.getEmail() == null
                || request.getEmail().isBlank() || request.getEmail().isEmpty()
                || isValidEmail(request.getEmail())
                || this.queryClient.existsByEmailAndIdIsNot(
                    request.getEmail(), customerId)) {
                return false;
            }


            if (request.getProof() == null
                || isValidProof(request.getProof())) {
                return false;
            }

            if (request.getJobName() == null || request.getJobName().isBlank()
                || request.getJobName().isEmpty()) {
                return false;
            }

            if (request.getDateOfBirth() == null) {
                return false;
            }

            if (request.getStatusCustomer() == null) {
                return false;
            }

            for (AddressModel addressModel : request.getAddressModels()) {
                if (isValidAddress(addressModel)) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    private boolean isValidProof(IdentityModel identityType) {
        switch (identityType.getTypeIdentity()) {
            case IDENTITY_CARD -> {
                return !RegexConstant.REGEX_IDENTITY_CARD.matcher(identityType.getNumberIdentity()).matches();
            }
            case CITIZEN_IDENTITY_CARD -> {
                return !RegexConstant.REGEX_CITIZEN_IDENTITY_CARD.matcher(identityType.getNumberIdentity()).matches();
            }
            case PASSPORT -> {
                return !RegexConstant.REGEX_PASSPORT.matcher(identityType.getNumberIdentity()).matches();
            }
        }
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return !RegexConstant.REGEX_PHONE_NUMBER.matcher(phoneNumber).matches();
    }

    private boolean isValidEmail(String email) {
        return !RegexConstant.REGEX_EMAIL.matcher(email).matches();
    }

    private boolean isValidAddress(AddressModel addressModel) {
        if (addressModel == null || addressModel.getNational() == null
            || addressModel.getNational().isBlank() || addressModel.getNational().isEmpty()) {
            return true;
        }
        String VIETNAM_CODE = "VN";
        if (VIETNAM_CODE.equals(addressModel.getNational())) {
            if (addressModel.getHouseNumber() == null
                || addressModel.getHouseNumber().isBlank() || addressModel.getHouseNumber().isEmpty()) {
                return true;
            }
            if (addressModel.getStreetName() == null
                || addressModel.getStreetName().isBlank() || addressModel.getStreetName().isEmpty()) {
                return true;
            }
            if (addressModel.getWardName() == null
                || addressModel.getWardName().isBlank() || addressModel.getWardName().isEmpty()) {
                return true;
            }
            if (addressModel.getDistrictName() == null
                || addressModel.getDistrictName().isBlank() || addressModel.getDistrictName().isEmpty()) {
                return true;
            }
            return addressModel.getCity() == null
                   || addressModel.getCity().isBlank() || addressModel.getCity().isEmpty();
        }
        return false;
    }

}
