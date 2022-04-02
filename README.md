# Overview

Simple project with ETags header into API response. 

ETag is an HTTP response header that contains a computed hash or
equivalent value of the response entity and a minor change in the entity must change its
value.

HTTP request objects can then contain the If-None-Match and If-Match
headers for receiving the conditional responses.

If data source contains the same entity, it sends a 304 (NOT MODIFIED) response instead of sending the proper response with 200 OK.

We use the HTTP cache control (org.springframework.http.CacheControl) class and use the version or similar attribute that gets updated for each
change, if available, to avoid unecessary CPU computation and for better ETag handling.

Adding an ETag to the response also allows UI apps to determine whether a page/object
refresh is required, or an event needs to be triggered, especially where data changes
frequently in applications such as providing live scores or stock quotes.

## Example 
### HTTP 304 NOT MODIFIED
http://localhost:8080/books/123

- GET /books/123 HTTP/1.1
- If-None-Match: "123"
- User-Agent: PostmanRuntime/7.29.0
- Accept: */*
- Postman-Token: f890aff9-d2e4-41ac-ade9-85a811b2e13f
- Host: localhost:8080
- Accept-Encoding: gzip, deflate, br
- Connection: keep-alive

- HTTP/1.1 304 Not Modified
- ETag: "123"
- Cache-Control: max-age=432000
- Date: Sat, 02 Apr 2022 12:07:11 GMT
- Keep-Alive: timeout=60
- Connection: keep-alive

### HTTP 200 OK
http://localhost:8080/books/123

- GET /books/123 HTTP/1.1
- If-None-Match:
- User-Agent: PostmanRuntime/7.29.0
- Accept: */*
- Postman-Token: 04df1b57-d39b-4681-83e2-b211ddb25a1f
- Host: localhost:8080
- Accept-Encoding: gzip, deflate, br
- Connection: keep-alive

- HTTP/1.1 200 OK
- ETag: "123"
- Cache-Control: max-age=432000
- Content-Type: application/json
- Transfer-Encoding: chunked
- Date: Sat, 02 Apr 2022 12:06:03 GMT
- Keep-Alive: timeout=60
- Connection: keep-alive
- 
- {"id":"123","name":null}


