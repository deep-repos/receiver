# receiver
Url =>http://localhost:8081/v1/natwest/receiver/transaction is exposed in Receiver service with  POST Method.
Payload is expected in encrypted String format

It returns  201 if transaction is inserted sucessfully , 500 if exception occured


Receiver decrypts input string using AES Algorithm and secret key mentioned in application.properties file.
It converts jsonString into Transaction object.It then stores Transaction object into database
and returns response to sender
