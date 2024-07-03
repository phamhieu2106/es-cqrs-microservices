package org.example.contractcommand.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "insurance-query", path = "/internal/api/insurances")
public interface InsuranceQueryClient {
}
