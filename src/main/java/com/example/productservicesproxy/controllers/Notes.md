## Controller
```
* Controllers are responsible for receiving requests from users and invoking back-end services (manager or dao) for business logic processing.
* Controllers with @Controller annotation and map HTTP requests with annotations like @RequestMapping, @GetMapping and @PostMapping in a Spring or Spring Boot application.
```

### What is a EndPoint in API
* Endpoint -> Combination of domain Name + Path of Entity API

### Path notation and Mapping
```
* @GetMapping("/products/{id}") 
*  if we have many services in a controller file so at the top of NameController class we should have the common path for example here as /products/ is common path 
so @RequestMapping("/products") at top and for each function map required path @GetMapping("/{id}")
* Entity name should be in prular format as they will return a list of products.

```
### Who creates the object of the controller?
```
* Spring will create its object, So we need to tell Spring by using annotation.
* @RestController will create its object or bean, Spring Boot identifies it as a controller and manages its lifecycle.
  * Here's a simplified explanation of how objects of controllers are created in Spring Boot:
  * Component Scanning: Spring Boot scans the application for classes annotated with @Controller, @RestController, or other stereotypes like @Service, @Repository, etc. 
  * Bean Creation: When it encounters a class annotated with @Controller, Spring Boot creates a bean for that class. 
  * Dependency Injection: If the controller class has dependencies on other Spring-managed beans (like services, repositories, etc.), Spring Boot injects those dependencies into the controller either through constructor injection, setter injection, or field injection, based on the annotations present in the controller. 
  * Request Handling: When an HTTP request is received by the application, Spring Boot consults its request mapping configurations to determine which controller method should handle the request. It then invokes the appropriate method on the controller object. 
  * Response Handling: The controller method processes the request, possibly calling other services or repositories to perform business logic, and returns a response. Spring Boot handles serialization of the response into the appropriate format (e.g., JSON for REST controllers).
```
### what do you mean by "Spring Boot creates a bean for that class"?
```  
In Spring (and consequently in Spring Boot, which is built on top of Spring), a "bean" is simply an object that is managed by the Spring container.
  
  When you annotate a class with @Controller, @RestController, @Service, @Repository, or similar stereotypes, Spring Boot recognizes these annotations 
  and creates bean instances for the annotated classes during the application's startup process.
  
  So, when I mentioned "Spring Boot creates a bean for that class", I meant that Spring Boot instantiates an object of the class annotated 
  with @Controller and manages its lifecycle as a bean. This means that Spring Boot will handle its creation, configuration, and destruction 
  as necessary throughout the application's runtime.

In practical terms, this means that you can rely on Spring Boot to manage the lifecycle of your controller objects, and you can inject these 
controller beans into other Spring-managed beans or components as needed, facilitating the dependency injection and inversion of control principles that Spring is known for.
```
### Spring @GetMapping Example

* The @GetMapping annotation is a composed version of @RequestMapping annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
* The @GetMapping annotated methods handle the HTTP GET requests matched with the given URI expression.
Let us understand how to write controller methods mapped with @GetMapping annotations. In the following example, we are mapping two GET requests:

* HTTP GET /users – returns all the users in the system.
* HTTP GET /users/{id} – returns a user by specified id.

```
@RestController
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
    
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
    
        Optional<User> user = userService.getById(id);
        return user.map(ResponseEntity::ok).orElseThrow(() -> new RecordNotFoundException("User not found for ID " + id));
    }
}
```

### @PathVariable in spring

```
Actually, @PathVariable("id") is used in Spring MVC (and consequently in Spring Boot) to map a URL template variable to a method parameter in a controller. It doesn't assign a default value of NULL to the method parameter; instead, it extracts the value from the URL path and binds it to the method parameter.

Here's how it works:

URL Template Variable: In your controller's request mapping, you specify a URL template with a variable placeholder, typically denoted by curly braces, like /example/{id}.
PathVariable Annotation: In your controller method signature, you use @PathVariable("id") to indicate that you want to map the {id} variable from the URL template to the method parameter id.
Value Binding: When a request comes in with a URL that matches the template (/example/123, for example), Spring MVC will extract the value 123 from the URL and bind it to the id parameter of your controller method.
Optional or Required: By default, path variables are required, meaning that if the {id} part of the URL is missing or cannot be converted to the type of the method parameter (in this case, Long), Spring will throw an exception. However, you can make the path variable optional by specifying @PathVariable(value = "id", required = false).
So, to clarify, @PathVariable("id") doesn't assign a default value of NULL to id. If the path variable is missing in the URL, and it's not marked as optional, Spring will typically raise an exception rather than assigning a default value. You can provide a default value manually in your method signature if you want, like @PathVariable("id") Long id = 0L, but this is different from a default value of NULL.
```
```angular2html
  //Path
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product Fetched with id:"+id;
    }
// maps the  @GetMapping("/product/{id}") to Long id by using @PathVariable("id")
```