# AccountingNotebook

## **Installation steps:**

#### Run docker using makefile:
```
$> make docker-compose-run
```
The application will run on port 8080.

To stop the app, just run:
```
$> make docker-compose-down
```

## **REST API:**

#### Balance API:
```
GET /balance

Returns: 
    200      account balance
```

#### Transaction API:
```
GET /transaction

Returns: 
    200      transaction list
```


```
GET /transaction/{uuid}
Params:
            uid: String

Returns: 
    200     transaction
    404     transaction not found
    400     not valid uuid
```

```
POST /transaction
Params:
            {
                'amount': String,
                'type': String ('CREDIT'/'DEBIT')
            }

Returns: 
    200     transaction
    400     amount is negative
    400     balance is negative
    400     type is not 'CREDIT' or 'DEBIT'
```
