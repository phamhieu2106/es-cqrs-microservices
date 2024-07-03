package org.example.contractdomain.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseCommand;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateContractCommand extends BaseCommand {
    String createdBy;
    Date contractStartDate;
    Date contractEndDate;
    Double contractTotalPayAmount; //tổng phí bảo hiểm hợp đồng
    Double contractTotalInsurancePayAmount; //tổng phí loại hình
    Double contractTotalNeedPayAmount; //tổng phí cần thanh toán
    Double contractTotalPayedAmount; //tổng phí đã thanh toán
    String customerId;
    List<String> insuranceIds;

}
