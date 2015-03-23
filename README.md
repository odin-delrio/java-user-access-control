# java-user-access-control

Basic J2EE web application in order to practise Java, BDD, DDD and hexagonal architecture.

Technical stuff
--------
* Tests written with [jUnit 4.10](http://mvnrepository.com/artifact/junit/junit).
* Mocking library [Mockito 1.9.5](http://mvnrepository.com/artifact/org.mockito/mockito-all/1.9.5).
* Developed with JVM 1.8.
* GlassFish Server 4.1.

Tests written focused on behaviour
--------
Tests implemented trying to test behaviour, not the final implementation. Using the typicall *acceptance tests* syntax, like [Gherkin syntax](https://github.com/cucumber/cucumber/wiki/Gherkin).

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

Domain logic implementation
--------
Using hexagonal architecture to implement the User model and related objects.

An interface was defined for the **UserRepository** and implemented in the **InMemoryUserRepository** for this example.
With this design, it would be easy to switch the repository's final storage method (MySql, Mongo... if the interface is satisfied it won't be a problem).

Resources access security checks
--------
Practising the user role management without using the server options (Glassfish provides a set of rules to manage the access to the web resources).

Using the Servlet filters instead for denying access to private sections. Two different filters were implemented:
* one for denying access for any unauthenticated user, if not, the user will be redirected to the login page.
* other one to check if the logged user has the needed role to access the requested resource, if not, the user will get the "forbidden" error page.
