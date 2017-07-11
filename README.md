SomeTest shows how `camelContext.getManagementName()` is null in Camel 2.18.0 
when fetched in `InitizializingBean.afterPropertiesSet()`, and  when returned
from a `Bean`.  They are not null in Camel 2.17.7.

An `@Autowired` `camelContext` in the test will always return the proper
name from `getManagementName()`.
