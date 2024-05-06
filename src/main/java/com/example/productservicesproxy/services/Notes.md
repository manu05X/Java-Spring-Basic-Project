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
```angular2html
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

```
* Say we have ProductService and FakkeStoreProductService class and IProductService interface now we will have conflict
in the ProductController file.
* As private IProductService productService;, will be confused which IProductService object or beans to call.
* ProductService OR FakkeStoreProductService as both have @Service annotation at the top.
* So here come a concept of Qualifiers
* We gave the name to the services using qualifiers and then specify same in the ProductController.

EXAMPLE:
@Qualifier("SelfProductService")
@Qualifier("FakeProductService")

  public ProductController(@Qualifier("FakeProductService") IProductService productService) {
        this.productService = productService;
    }
```

