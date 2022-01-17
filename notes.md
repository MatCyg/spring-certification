Beans:
@Resource == @Autowired + @Qualifier
@Named == @Component
@Autowired == @Inject
@Import vs @ContextConfiguration, @Import adds beans to current configuration, @ContextConfiguration overrides the whole context

Context:
- `AutowiredAnnotationBeanPostProcessor`
- `DefaultSingletonBeanRegistry`
- `InfrastructureAdvisorAutoProxyCreator`

Configurationap:
- `PropertySourcesPlaceholderConfigurer`

Mapping rows:
- `RowCallbackHandler` - when no value should be returned.
- `RowMapper` - when each row of the ResultSet maps to a domain object.
- `RowSetExtractor` - when **multiple** rows of the ResultSet maps to a single object.
- `ParameterizedRowMapper` complex object creation

Data:
- `TransactionInterceptor`
- `EnableTransactionManagement`
- `TransactionManagementConfigurer` - allows setting default transaction manager when more than one is available (`@Primary` can also be used)
- `SqlInitializationProperties` - contains default location for [database initialization](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.data-initialization.using-basic-sql-scripts) locations:
  - schema.sql
  - data.sql
  - schema-${platform}.sql
  - data-${platform}.sql \
    where platform: `all`, `h2` `postgres`, etc
- `@Transactional`
- `@Commit`
- `@Rollback`
- `@BeforeTransaction`
- `@AfterTransaction`
- `@Sql`
- `@SqlConfig`
- `@SqlGroup`



Transaction Managers:
- `DataSourceTransactionManager` - basic transaction management provider used with JDBC and MyBatis
- `HibernateTransactionManager`
- `JpaTransactionManager`
- `JtaTransactionManager` - used to delegate transaction management to a Java EE server. Can be also used with Atomikos
- `WebLogicJtaTransactionManager`

![Transaction Sequence Diagram](images/transaction_sequence_diagram.png)


Web:
- `MessageConverter`
- ``
- Handler method: can have parameters:
  - Model, ModelMap, RedirectAttributes, @ModelAttribute, @SessionAttribute, @RequestAttribute
  - @Validated, BindingResult - provides validation errors
  - java.io.InputStream
  - java.io.Reader
  - PushBuilder - HTTP2 resource push
  - java.security.Principal
  - WebRequest, NativeWebRequest, javax.servlet.ServletRequest
  - javax.servlet.ServletResponse
  - HttpMethod
  - Locale
  - @RequestParam, @PathVariable, @RequestHeader, @MatrixVariable, @CookieValue
  - String, CharSequence, Date, Temporal, URI, URL (treated as if they are annotated with @RequestParam)
- Handler method: annotations:
  - @RequestMapping, @GetMapping, etc.
  - @ResponseStatus
- Path variable can be defined in the class level @RequestMapping