package org.example.contractdomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.contractdomain.enumeration.StatusContract;
import org.example.contractdomain.enumeration.StatusPayment;
import org.example.contractdomain.model.InsuranceModel;
import org.example.sharedlibrary.base.BaseEvent;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateContractEvent extends BaseEvent {
    String contractId;
    String contractCode;
    Date contractStartDate;
    Date contractEndDate;
    Double contractTotalPayAmount; //tổng phí bảo hiểm hợp đồng
    Double contractTotalInsurancePayAmount; //tổng phí loại hình
    Double contractTotalNeedPayAmount; //tổng phí cần thanh toán
    Double contractTotalPayedAmount; //tổng phí đã thanh toán
    String customerId;
    List<InsuranceModel> insuranceEntities;
    StatusPayment statusPayment;
    StatusContract statusContract;
    Boolean softDelete = false;

    public CreateContractEvent(Date timestamp, String createdBy, String contractId, String contractCode,
                               Date contractStartDate, Date contractEndDate, Double contractTotalPayAmount,
                               Double contractTotalInsurancePayAmount, Double contractTotalPayedAmount,
                               Double contractTotalNeedPayAmount, String customerId, StatusPayment statusPayment,
                               List<InsuranceModel> insuranceEntities, StatusContract statusContract, Boolean softDelete) {
        super(timestamp, createdBy);
        this.contractId = contractId;
        this.contractCode = contractCode;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractTotalPayAmount = contractTotalPayAmount;
        this.contractTotalInsurancePayAmount = contractTotalInsurancePayAmount;
        this.contractTotalPayedAmount = contractTotalPayedAmount;
        this.contractTotalNeedPayAmount = contractTotalNeedPayAmount;
        this.customerId = customerId;
        this.statusPayment = statusPayment;
        this.insuranceEntities = insuranceEntities;
        this.statusContract = statusContract;
        this.softDelete = softDelete;
    }
}
