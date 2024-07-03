package org.example.contractcommand.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customer-query", path = "/internal/api/customers")
public interface CustomerQueryClient {
}
