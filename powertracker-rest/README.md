# PowerTracker RESTful API

Provides the back-end support for the UI, keeping track of available power items and allocations. Implemented in Spring Boot, backed by a JPA repository.

## Supported API's

* `/item` retrieves all power items
* `/item/{id}` retrieves the power item with specified id 
* `/brand/{name}` retrieves all power items of specified brand
* `/allocate/{size}` allocates a power item of given size from stock if available
* `/deallocate/{size}` makes an allocated power item of given size available for use