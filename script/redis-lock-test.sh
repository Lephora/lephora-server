#!/bin/bash

# post/order接口并发测试，相同的请求，不允许创建多条订单
# result:
# {"errorCode":"INTERNAL_SERVER_ERROR","errorMessage":"failed to get redis lock: null"}
# {"errorCode":"INTERNAL_SERVER_ERROR","errorMessage":"failed to get redis lock: null"}
# {"errorCode":"INTERNAL_SERVER_ERROR","errorMessage":"failed to get redis lock: null"}
# {"errorCode":"10001","errorMessage":"Not found commodity.: commodity sku 000006 not found"}

SERVER_PORT=$1
JOBS_NUMBER=7

for (( i=0; i<$JOBS_NUMBER; i++ )); do {
  curl -X POST http://localhost:"${SERVER_PORT}"/order -w '\n' --header "token: 12345" --header 'Content-Type: application/json' --data-raw '{ "customerId": "000002","commoditySku": "000006","quantity": 11,"address": "Shanghai Thoughtworks","fullName": "Bob, Liu","phoneNumber": "13411011010"}' &
} done

