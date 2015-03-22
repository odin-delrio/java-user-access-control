/*
 * Copyright 2015 odin.delrio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package User.Domain.Model;

import User.Domain.Model.Hasher.Md5PasswordHasher;
import User.Domain.Model.Hasher.PasswordHasher;
import User.Domain.Model.Hasher.UnableToHashPasswordException;
import User.Domain.Model.User.User;
import User.Domain.Model.User.UserNotFoundException;
import User.Domain.Model.User.UserRepository;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author odin.delrio
 */
public class AuthentifierTest 
{
    private Authentifier authentifier;

    private final String username = "testUserName";
    
    /**
     * Represents the stored hashed password.
     */
    private final char[] password = "05060805mmdd_hashedPassword".toCharArray();
    
    /**
     * Test of authenticate method with an unexistent user.
     * 
     * @throws User.Domain.Model.Hasher.UnableToHashPasswordException
     * @throws User.Domain.Model.User.UserNotFoundException
     */
    @Test
    public void testAuthenticateWithUnexistentUser() throws UnableToHashPasswordException, UserNotFoundException 
    {        
        givenAnAuthentifier();
        whenTheUserRepositoryHasNoUsersForName();
        thenAssertCredentialsAreInvalid(); 
    }

    /**
     * 
     * @throws UserNotFoundException
     * @throws UnableToHashPasswordException 
     */
    @Test
    public void testAuthenitcateWithExistingUserButWrongPassword() throws UserNotFoundException, UnableToHashPasswordException
    {
        givenAnAuthentifier();
        whenTheUserRepositoryHasAnUserForName();
        andWithAMd5PasswordHasherThatWontMatchCurrentPassword();
        thenAssertCredentialsAreInvalid(); 
    }

    /**
     *
     * @throws UserNotFoundException
     * @throws UnableToHashPasswordException 
     */
    @Test
    public void testAuthenitcateWithExistingUserAndCorrectPassword() throws UserNotFoundException, UnableToHashPasswordException
    {
        givenAnAuthentifier();
        whenTheUserRepositoryHasAnUserForName();
        andWithAMd5PasswordHasherThatWillMatchCurrentPassword();
        thenAssertCredentialsAreValid(); 
    }
    
    private void givenAnAuthentifier()
    {
        UserRepository userRepository = null;
        PasswordHasher passwordHasher = null;
        
        authentifier = new Authentifier(userRepository, passwordHasher);
    } 
    
    /**
     * 
     * @throws UserNotFoundException 
     */
    private void whenTheUserRepositoryHasAnUserForName() throws UserNotFoundException
    {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        
        when(
            userRepository
                .userOfName(username)
        )
        .thenReturn(
            new User()
                .setName(username)
                .setPassword(password)
        );
        
        this.authentifier.setUserRepository(userRepository);
    }

    private void whenTheUserRepositoryHasNoUsersForName() throws UserNotFoundException
    {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.userOfName(username)).thenThrow(UserNotFoundException.class);
        
        this.authentifier.setUserRepository(userRepository);
    }
    
    /**
     * The mocked hasher will returns an empty char array that won't match with
     * the expected password.
     */
    private void andWithAMd5PasswordHasherThatWontMatchCurrentPassword() throws UnableToHashPasswordException
    {
        Md5PasswordHasher md5PasswordHasher = Mockito.mock(Md5PasswordHasher.class);
        
        when(md5PasswordHasher.hash(password))
            .thenReturn(new char[0]);
                
        this.authentifier.setPasswordHasher(md5PasswordHasher);
    }

    /**
     * The mocked hasher will returns the same password as the passed one.
     */
    private void andWithAMd5PasswordHasherThatWillMatchCurrentPassword() throws UnableToHashPasswordException
    {
        Md5PasswordHasher md5PasswordHasher = Mockito.mock(Md5PasswordHasher.class);
        
        when(md5PasswordHasher.hash(password))
            .thenReturn(password);
                
        this.authentifier.setPasswordHasher(md5PasswordHasher);
    }
    
    private void thenAssertCredentialsAreInvalid() throws UnableToHashPasswordException
    {
        assertFalse(authentifier.authenticate(username, password));
    }
    
    private void thenAssertCredentialsAreValid() throws UnableToHashPasswordException
    {
        assertTrue(authentifier.authenticate(username, password));
    }
}
