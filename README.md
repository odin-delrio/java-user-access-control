# java-user-access-control

Basic J2EE web application in order to practise Java, BDD, DDD and hexagonal architecture.

Technical stuff
--------
* Tests written with [jUnit 4.10](http://mvnrepository.com/artifact/junit/junit).
* Mocking library [Mockito 1.9.5](http://mvnrepository.com/artifact/org.mockito/mockito-all/1.9.5).
* Developed with JVM 1.8.
* GlassFish Server 4.1.
* Twitter bootstrap for the look and feel.

Tests written focused on behaviour
--------
Tests implemented trying to test behaviour, not the final implementation. Using the typicall *acceptance tests* syntax ([Gherkin syntax](https://github.com/cucumber/cucumber/wiki/Gherkin)).

Preserving the unitary tests, mocking the behaviour of the objects that are not part of the test cases.
Here is an example of the [test for the Authentifier class](https://github.com/odin-delrio/java-user-access-control/blob/master/test/User/Domain/Model/AuthentifierTest.java).

```java
    @Test
    public void testAuthenticateWithExistingUserButWrongPassword()
    {
        givenAnAuthentifier();
        whenTheUserRepositoryHasAnUserForName();
        andWithAMd5PasswordHasherThatWontMatchCurrentPassword();
        thenAssertCredentialsAreInvalid(); 
    }
```
With this syntax, all the entire test method is readable and maintainable.

![Tests passing](/doc/screenshots/tests-passing.png?raw=true "Tests passing")

Domain logic implementation
--------
Using hexagonal architecture to implement the User model and related objects.

The domain models design is using the concept of Dependency Injection, but, for this example, the construction of objects is done in the Servlets, in a real world, these constructions should be avoided by using a *Dependency injection container* (usually the Spring DIC).

An interface was defined for the **UserRepository** and implemented in the **InMemoryUserRepository** for this example.
With this design, it would be easy to switch the repository's final storage method (MySql, Mongo... if the interface is satisfied it won't be a problem).

A facade was created to insert users into the **InMemoryUserRepository** in order to make a real authentication process, including the password hashing (with a SALT) and then, storing the User in session storage.

For the roles, a *Java Enum* was used, overriding the constructor to easy check the value with an ordinary String (Roles per page were defined in the web.xml config file).

MD5 hashing encapsulation
--------
This is not PHP and we haven't the **md5()** function... so, this non-trivial operation was encapsulated in the **Md5Hasher** ([test](https://github.com/odin-delrio/java-user-access-control/blob/master/test/Library/Hashing/Md5HasherTest.java) created for this class) and used in the **Md5PasswordHasher** (this class enforce the hashing operation with a salt).

Resources access security checks
--------
Practising the user role management without using the server options (Glassfish provides a set of rules to manage the access to the web resources).

Using the Servlet filters instead for denying access to private sections. Two different filters were implemented and configured in the [web.xml file](https://github.com/odin-delrio/java-user-access-control/blob/master/web/WEB-INF/web.xml).

* The [AuthenticationRequiredFilter](https://github.com/odin-delrio/java-user-access-control/blob/master/src/java/Bundle/UserAccess/Servlets/Filter/AuthenticationRequiredFilter.java) for denying access for any unauthenticated user, if not, the user will be redirected to the login page.
```xml
    <filter>
        <filter-name>AuthenticationRequiredFilter</filter-name>
        <filter-class>Bundle.UserAccess.Servlets.Filter.AuthenticationRequiredFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>AuthenticationRequiredFilter</filter-name>
        <url-pattern>/private/*</url-pattern>
    </filter-mapping>

    <filter-mapping>
        <filter-name>AuthenticationRequiredFilter</filter-name>
        <url-pattern>/logged-main.jsp</url-pattern>
    </filter-mapping>
```
* Other one, the [RoleRequiredFilter](https://github.com/odin-delrio/java-user-access-control/blob/master/src/java/Bundle/UserAccess/Servlets/Filter/RoleRequiredFilter.java), to check if the logged user has the needed role to access the requested resource, if not, the user will get the "forbidden" error page (with the properly 403 HTTP status code).
````xml
    <filter>
        <filter-name>RoleRequiredFilter</filter-name>
        <filter-class>Bundle.UserAccess.Servlets.Filter.RoleRequiredFilter</filter-class>
        <init-param>
            <param-name>page-1.jsp</param-name>
            <param-value>PAGE_1_VIEWER</param-value>
        </init-param>
        <init-param>
            <param-name>page-2.jsp</param-name>
            <param-value>PAGE_2_VIEWER</param-value>
        </init-param>
        <init-param>
            <param-name>page-3.jsp</param-name>
            <param-value>PAGE_3_VIEWER</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>RoleRequiredFilter</filter-name>
        <url-pattern>/private/*</url-pattern>
    </filter-mapping>
````

Error page declaration and demostration:
```java
    // RoleRequiredFilter class context 
    if (null == user || !user.hasRole(UserRole.valueOf(roleRequired))) {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
```

```xml
    <error-page>
        <error-code>403</error-code>
        <location>/error/forbidden.jsp</location>
    </error-page>
```
![403 code sent](/doc/screenshots/chrome-console-showing-403.png?raw=true "403 code sent")

Some screenshots
--------

![Sign in page](/doc/screenshots/sign-in-page.png?raw=true "Sign in page")

![Main page](/doc/screenshots/main-page.png?raw=true "Main page")

![Private page](/doc/screenshots/private-page-2.png?raw=true "Private page")

![Forbidden message](/doc/screenshots/forbidden-message.png?raw=true "Forbbiden message")


TODO
-------
* Convert into **maven** project in order to install the dependencies automatically.
