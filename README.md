# Execution Timing using AspectJ
Measure and log execution time of annotated methods in a Spring app using AspectJ
*Note: Spring AOP is a proxy-based and cannot capture calls to private methods or calls made within a class*
## Usage
0. Add `TimingAspectConfig.java` and `TimeIt.java` to project
1. Add `aspectjweaver` dependency to `pom.xml` 
    ```xml
    <dependency>
    	<groupId>org.aspectj</groupId>
    	<artifactId>aspectjweaver</artifactId>
    	<version>${aspectj.version}</version>
    </dependency>
   ```
2. Add `@EnableAspectJAutoProxy` to configuration class
3. Add annotation `@TimeIt` over methods which are to be timed
4. Check `log4j` logs to see timings