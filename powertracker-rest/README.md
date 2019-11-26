# PowerTracker RESTful API

Spring Boot application, serving API's using SpringMVC and backed by a JPA repository.

## Supported API's

* `/item` retrieves all power items
* `/item/{id}` retrieves the power item with specified id 
* `/brand/{name}` retrieves all power items of specified brand
* `/stock` retrieves a count of all power items
* `/allocate/{size}` allocates a power item of given size from stock if available
* `/deallocate/{size}` makes an allocated power item of given size available for use