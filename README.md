# Employee-Salary-Disburse
There are total six grades/ranks; Grade one is the highest and grade 6 is the lowest. There are total 10 employees at the company - grade one: 1, grade two: 1, grade three: 2, grade four: 2, grade five: 2, grade six: 2. Each employee has name, grade/rank, address, mobile and a bank account. The bank account has account type - savings/current, account name, account number, current balance, bank and branch name etc. The salary is divided into the following heads - Basic House rent - 20% of the basic Medical allowance - 15% of the basic The basic salary of the lowest grade will be taken as input. The basic of the others grade will be calculated as basic of the previous grade + 5000 taka. The company will have a main bank account. The initial balance will be taken as input. From the company account the salary will be transferred to the employees account. If during the salary transfer, the company bank account run out of money, there will be an input option to add more money to the account and than continue the salary payment.

### Database setup information
- Username -> demouser
- Password -> demopass
- Database name -> salarydisburse

### Login api url and credentials

- API URL -> http://localhost:8080/oauth/token?grant_type=password&client_id=client_id&client_secret=client_secret&username=admin&password=password
- Credentials -> UserName - admin and Password - password
- Sample Response 

```json
{
    "access_token": "d47f730b-d46d-4d84-9a24-bbb6429161b9",
    "token_type": "bearer",
    "refresh_token": "47b84d18-4ea2-46bf-9503-fc6ee72168c0",
    "expires_in": 19999,
    "scope": "read write trust",
    "phone": "01710000000",
    "name": "Admin",
    "id": 1,
    "email": "demo@example.com",
    "authorities": [
        {
            "authority": "ADMINISTRATION"
        },
        {
            "authority": "ACCESS_USER_RESOURCES"
        }
    ],
    "username": "admin"
}
```
### Bank APIS

- GET -> http://localhost:8080/api/v1/banks?page=0&size=10
- Sample Response
```json
{
    "content": [
        {
            "id": 1,
            "uuid": null,
            "created_at": "2020-11-14T10:00:42.000+0000",
            "last_updated": "2020-11-14T10:00:42.000+0000",
            "name": "Brac",
            "branch_name": "Badda",
            "address": "Brac",
            "balance": 100000.0
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "numberOfElements": 1,
    "first": true,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 10,
    "number": 0,
    "empty": false
}
```
- POST -> http://localhost:8080/api/v1/banks
- Sample Body
```json
{
  "address": "Brac",
  "balance": 100000,
  "branch_name": "Badda",
  "name": "Brac"
}
```

### Bank Account APIS

- GET -> http://localhost:8080/api/v1/bankaccounts?page=0&size=10
- Sample Response
```json
{
    "content": [
        {
            "id": 1,
            "uuid": "e9954241-0cbe-4b45-9fc9-07c967571c44",
            "created_at": "2020-11-14T10:26:18.000+0000",
            "last_updated": "2020-11-14T10:26:18.000+0000",
            "name": "Mr. X",
            "account_number": "101011",
            "account_type": 1,
            "account_type_obj": {
                "id": 1,
                "labelEn": "Savings",
                "labelBn": "Savings"
            },
            "current_balance": 1000.0,
            "bank_id": 1,
            "bank_info": {
                "id": 1,
                "uuid": "709dd07c-29e5-48b5-9b19-7f1241136604",
                "created_at": "2020-11-14T10:00:42.000+0000",
                "last_updated": "2020-11-14T10:00:42.000+0000",
                "name": "Brac",
                "branch_name": "Badda",
                "address": "Brac",
                "balance": 100000.0
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "last": true,
    "totalElements": 1,
    "first": true,
    "numberOfElements": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "empty": false
}
```
- POST -> http://localhost:8080/api/v1/bankaccounts
- Sample Body
```json
{
  "account_number": 101011,
  "account_type": 1,
  "bank_id": 1,
  "current_balance": 1000,
  "name": "Mr. X"
}
```

### Company APIS

- GET -> http://localhost:8080/api/v1/companys?page=0&size=10
- Sample Response
```json
{
    "content": [
        {
            "id": 1,
            "uuid": "e0eef1ac-164f-4283-956f-a03a135fe047",
            "created_at": "2020-11-14T11:06:12.000+0000",
            "last_updated": "2020-11-14T11:06:12.000+0000",
            "name": "X LTD",
            "phone": "01917173733",
            "address": "Badda",
            "total_paid_salary": 0.0,
            "bank_account_id": 1,
            "bank_account_info": {
                "id": 1,
                "uuid": "e9954241-0cbe-4b45-9fc9-07c967571c44",
                "created_at": "2020-11-14T10:26:18.000+0000",
                "last_updated": "2020-11-14T10:26:18.000+0000",
                "name": "Mr. X",
                "account_number": "101011",
                "account_type": 1,
                "account_type_obj": {
                    "id": 1,
                    "labelEn": "Savings",
                    "labelBn": "Savings"
                },
                "current_balance": 1000.0,
                "bank_id": 1,
                "bank_info": {
                    "id": 1,
                    "uuid": "709dd07c-29e5-48b5-9b19-7f1241136604",
                    "created_at": "2020-11-14T10:00:42.000+0000",
                    "last_updated": "2020-11-14T10:00:42.000+0000",
                    "name": "Brac",
                    "branch_name": "Badda",
                    "address": "Brac",
                    "balance": 100000.0
                }
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "numberOfElements": 1,
    "first": true,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 10,
    "number": 0,
    "empty": false
}
```
- POST -> http://localhost:8080/api/v1/companys
- Sample Body
```json
{
  "address": "Badda",
  "bank_account_id": 1,
  "name": "X LTD",
  "phone": "01917173733"
}
```

### Company APIS

- GET -> http://localhost:8080/api/v1/employees?page=0&size=10
- Sample Response
```json
{
    "content": [
        {
            "id": 7,
            "uuid": "c9e6edff-e6ba-4a26-9b70-7307d4960d4d",
            "created_at": "2020-11-14T12:12:02.000+0000",
            "last_updated": "2020-11-14T17:10:09.000+0000",
            "name": "Mr. CA",
            "phone": "01917173753",
            "address": "Badda",
            "basic_salary": 25000.0,
            "house_rent": 5000.0,
            "medical_allowance": 3750.0,
            "grade_id": 2,
            "grade_obj": {
                "id": 2,
                "label": "two"
            },
            "bank_account_id": 1,
            "bank_account_info": {
                "id": 1,
                "uuid": "e9954241-0cbe-4b45-9fc9-07c967571c44",
                "created_at": "2020-11-14T10:26:18.000+0000",
                "last_updated": "2020-11-14T10:26:18.000+0000",
                "name": "Mr. X",
                "account_number": "101011",
                "account_type": 1,
                "account_type_obj": {
                    "id": 1,
                    "labelEn": "Savings",
                    "labelBn": "Savings"
                },
                "current_balance": 1000.0,
                "bank_id": 1,
                "bank_info": {
                    "id": 1,
                    "uuid": "709dd07c-29e5-48b5-9b19-7f1241136604",
                    "created_at": "2020-11-14T10:00:42.000+0000",
                    "last_updated": "2020-11-14T10:00:42.000+0000",
                    "name": "Brac",
                    "branch_name": "Badda",
                    "address": "Brac",
                    "balance": 100000.0
                }
            },
            "company_id": 1,
            "company_info": {
                "id": 1,
                "uuid": "e0eef1ac-164f-4283-956f-a03a135fe047",
                "created_at": "2020-11-14T11:06:12.000+0000",
                "last_updated": "2020-11-14T11:06:12.000+0000",
                "name": "X LTD",
                "phone": "01917173733",
                "address": "Badda",
                "bank_account_id": 1,
                "bank_account_info": {
                    "id": 1,
                    "uuid": "e9954241-0cbe-4b45-9fc9-07c967571c44",
                    "created_at": "2020-11-14T10:26:18.000+0000",
                    "last_updated": "2020-11-14T10:26:18.000+0000",
                    "name": "Mr. X",
                    "account_number": "101011",
                    "account_type": 1,
                    "account_type_obj": {
                        "id": 1,
                        "labelEn": "Savings",
                        "labelBn": "Savings"
                    },
                    "current_balance": 1000.0,
                    "bank_id": 1,
                    "bank_info": {
                        "id": 1,
                        "uuid": "709dd07c-29e5-48b5-9b19-7f1241136604",
                        "created_at": "2020-11-14T10:00:42.000+0000",
                        "last_updated": "2020-11-14T10:00:42.000+0000",
                        "name": "Brac",
                        "branch_name": "Badda",
                        "address": "Brac",
                        "balance": 100000.0
                    }
                }
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 7,
    "numberOfElements": 7,
    "first": true,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 10,
    "number": 0,
    "empty": false
}
```
- POST -> http://localhost:8080/api/v1/employees
- Sample Body
```json
{
  "address": "Badda",
  "bank_account_id": 1,
  "company_id": 1,
  "grade_id": 2,
  "name": "Mr. CA",
  "basic_salary": 10000,
  "phone": "01917173753"
}
```

### Salary distribute from company account to employee account
- PATCH -> http://localhost:8080/api/v1/employees/salary/disburse?basic_salary=5000&company_id=1

### Amount transfer to company bank account
- PATCH -> http://localhost:8080/api/v1/companys/salary/transfer?amount=5000&company_id=1
