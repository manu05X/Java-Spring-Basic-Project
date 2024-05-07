### Why we created interface of productServices?

```
Creating an interface for ProductService allows for abstraction and decoupling in the codebase. 
This means that different implementations of ProductService can be easily swapped in and out without affecting the rest of the application. 
It also promotes code modularity and makes the codebase easier to maintain and test. 
Additionally, interfaces enable dependency injection, facilitating loose coupling and promoting best practices in software development.
```

### How does Productcontroller rely on ProductServiceImp to get the data?

```
The ProductController relies on ProductServiceImp to get the data through dependency injection. Here's how it works:

Constructor Injection: In the ProductController class, there's a constructor that accepts an instance of ProductServiceImp (or an implementation of ProductService) as a parameter.
Autowired Annotation: The constructor is annotated with @Autowired, indicating to Spring that it should automatically inject an instance of ProductServiceImp when creating a ProductController object.
Instance Usage: Within the ProductController methods (e.g., getProductById()), ProductServiceImp methods are invoked to retrieve the data. The controller doesn't directly interact with the database or perform data retrieval tasks; 
instead, it delegates these responsibilities to the ProductServiceImp implementation.
Decoupling: This approach decouples the controller from the specific implementation of the ProductService interface. It allows for easier testing and maintenance since different implementations of ProductService can be swapped without affecting the controller's functionality.
Overall, by relying on ProductServiceImp through dependency injection, the ProductController achieves separation of concerns and maintains a clear division of responsibilities between handling HTTP requests and interacting with data.

```
Code Example
```java
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    // Other controller methods...
}
```

### What to do if we have more than one service class and one interface in our services module?

``` java
* Say we have ProductService and FakkeStoreProductService class and IProductService interface now we will have conflict
in the ProductController file.
* As private IProductService productService;, will be confused which IProductService object or beans to call.
* ProductService OR FakkeStoreProductService as both have @Service annotation at the top so two beans will be created by spring.
* In SOLID principal we have learned that no two concrete class should depend on each other.
* So here come a concept of Qualifiers
* We gave the name to the services using qualifiers and then specify same in the ProductController.

EXAMPLE:
@Qualifier("SelfProductService")
@Qualifier("FakeProductService")

  public ProductController(@Qualifier("FakeProductService") IProductService productService) {
        this.productService = productService;
    }
```

### How to integrate with 3rd party API?

* We will be using RestTemplateBuilder of spring to connect to 3rd party API.
* RestTemplateBuilder is providing us the basic structure of a HTTP call.
* RestTemplateBuilder object will be created by spring.
* We just need to @Autowired to the FakeStoreProductService object to RestTemplateBuilder object using constructor injection.

```java
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/1";
    
    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
```
* when we connect to any URL we are trying to make a HTTP call to that URL with http verb such as GET,PUT,POST,PATCH .etc .
* If we create a HTTP client and every time we need to specify that, then it is better that the HTTP call gets a template, and everything other than request url and its response type, all will be taken care by spring.
* As we have the RestTemplateBuilder available. We need to get an entity using RestTemplateBuilder. -> restTemplate.getForEntity
* We just need to provide URL and its response type in the template.
* eg : restTemplate.getForEntity(requestToUrl, responseFromUrl);
  * Here we are telling the template that i am providing the url get met the required response.

* Now save the incoming response in a responseEntity
```ResponseEntity<String> responseEntity.```
* At last return the responseEntity.toString();.

```java
 @Override
    public String getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getProductUrl, String.class);
        //return "Product fetched from FakeStore service with Id " + id;
        return "Product fetched from FakeStore service with Id " + responseEntity.toString();
    }
```

### Let See All the above step in one place.

```java
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/1";

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getProductById(Long id) {
        //here we are using builder pattern so every time we need to make new request
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getProductUrl, String.class);
        //return "Product fetched from FakeStore service with Id " + id;
        return "Product fetched from FakeStore service with Id " + responseEntity.toString();
    }
```