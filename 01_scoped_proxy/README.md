
Verify how different proxy creation modes work:
* ScopedProxyMode.INTERFACES = JDK Dynamic proxy (interface only)
* ScopedProxyMode.TARGET_CLASS = CGLIB


#### ScopedProxyMode.TARGET_CLASS vs ScopedProxyMode.INTERFACES

* `ScopedProxyMode.INTERFACES` produces a JDK dynamic proxy, which will try to proxy the interface. Concrete type cannot be autowired. Interface must be always referred to instead.  
* `ScopedProxyMode.TARGET_CLASS` creates CGLIB proxy, which is a subclass of the class. The class cannot be final and must be visible (public/package). 

#### default scope mode
Default scope proxy mode can be set on @ComponentScan, i.e.:
```java
@ComponentScan(scopedProxy = ScopedProxyMode.TARGET_CLASS);
```

#### proxyBeanMethods
`proxyBeanMethods` is about changing the behaviour of methods annotated with @Bean. Normally if such method is called directly, it always goes through a proxy method. If scope is singleton, all subsequent calls will return the same instance. Setting this value to false `@Configuration(proxyBeanMethods = false)` will enable so called 'Bean lite mode'. All `@Bean` methods defined in the `@Configuration` class will behave as if they were defined in a non-`@Configuration` annotated classes. Each subsequent call to the method will produce a new instance.

It could be perceived that setting `@SpringBootApplication(proxyBeanMethods = false)` will make all `@Configuration` classes use 'Bean lite mode', but it is not the case. Such configuration will only apply to `@Bean` methods defined in the annotated class (the one with `@SpringBootApplication` annotation).