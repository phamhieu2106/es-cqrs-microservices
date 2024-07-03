package org.example.contractdomain.aggregate;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.contractdomain.command.CreateContractCommand;
import org.example.contractdomain.command.DeleteContractCommand;
import org.example.contractdomain.command.UpdateContractCommand;
import org.example.contractdomain.enumeration.StatusContract;
import org.example.contractdomain.enumeration.StatusPayment;
import org.example.contractdomain.event.CreateContractEvent;
import org.example.contractdomain.model.InsuranceModel;
import org.example.sharedlibrary.base.BaseAggregate;
import org.example.sharedlibrary.base.BaseAggregateApply;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.sharedlibrary.constant.GenerateConstant;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractAggregate extends BaseAggregate implements BaseAggregateApply<
        CreateContractCommand, UpdateContractCommand, DeleteContractCommand> {


    @Id
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
    Date createdAt;
    String createdBy;

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyCreate(CreateContractCommand command) {
        this.contractId = GenerateConstant.generateId();
        this.contractStartDate = command.getContractStartDate();
        this.contractEndDate = command.getContractEndDate();
//        this.contractTotalPayAmount = command.getContractTotalPayAmount();
//        this.contractTotalInsurancePayAmount = command.getContractTotalInsurancePayAmount();
        this.contractTotalPayedAmount = command.getContractTotalPayedAmount();
        this.customerId = command.getCustomerId();
        this.createdAt = new Date();
        this.createdBy = command.getCreatedBy();

        return (E) new CreateContractEvent(
                new Date(),
                this.createdBy,
                this.contractId,
                this.contractCode,
                this.contractStartDate,
                this.contractEndDate,
                this.contractTotalPayAmount,
                this.contractTotalInsurancePayAmount,
                this.contractTotalPayedAmount,
                this.contractTotalNeedPayAmount,
                this.customerId,
                this.statusPayment,
                this.insuranceEntities,
                this.statusContract,
                false
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyUpdate(UpdateContractCommand command) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyDelete(DeleteContractCommand command) {
        return null;
    }
}
