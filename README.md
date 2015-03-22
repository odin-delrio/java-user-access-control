# java-user-access-control

Basic J2EE web application in order to practise Java, BDD, DDD and hexagonal architecture.

Technical stuff
--------
* Tests wroten with [jUnit 4.10](http://mvnrepository.com/artifact/junit/junit).
* Mocking library [Mockito 1.9.5](http://mvnrepository.com/artifact/org.mockito/mockito-all/1.9.5).
* Developed with JVM 1.8.
* GlassFish Server 4.1.

Resources access security checks
--------
Practised the user role management without using the server options (Glassfish provides a set o rules to manage the access to the web resources).

Used the Servlet filters instead for deny access to private sections. Two different filters were implemented, one for deny access for any unauthenticated user and other for decide if the logged user has the needed role to access to the requested resource.

Domain logic implementation
--------
Used hexagonal architecture to implement the User model and related objects.

An interface were defined to the **UserRepository** and implemented an **InMemoryUserRepository** for this example.
With this design, it would be easy to switch the repository final storage method (MySql, Mongo... if the interface is satisfied it won't be a problem).

Tests wroten focused on behaviour
--------
Tests implemented trying to test behaviour, not the final implementation. Used the typicall *acceptance tests* syntax, like [Gherkin syntax](https://github.com/cucumber/cucumber/wiki/Gherkin).

Preserving the unitary tests, mocking the behaviour of the objects that are not part of the test cases.

```java
    @Test
    public void testAuthenitcateWithExistingUserButWrongPassword()
    {
        givenAnAuthentifier();
        whenTheUserRepositoryHasAnUserForName();
        andWithAMd5PasswordHasherThatWontMatchCurrentPassword();
        thenAssertCredentialsAreInvalid(); 
    }
```
With this syntax, all the entire test method is readable and maintainable.
