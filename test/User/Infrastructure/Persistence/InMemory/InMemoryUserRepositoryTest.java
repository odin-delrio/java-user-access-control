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
package User.Infrastructure.Persistence.InMemory;

import User.Domain.Model.User.User;
import User.Domain.Model.User.UserAlreadyExistsException;
import User.Domain.Model.User.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author odin.delrio
 */
public class InMemoryUserRepositoryTest {
    
    private InMemoryUserRepository userRepository;
    
    @Before
    public void setUp() 
    {
        this.userRepository = new InMemoryUserRepository();
    }
    
    /**
     * Test the persist action withour errors.
     * 
     * @throws User.Domain.Model.User.UserAlreadyExistsException
     * @throws User.Domain.Model.User.UserNotFoundException
     */
    @Test
    public void testPersistAndRetrieveUserSuccessfully() throws UserAlreadyExistsException, UserNotFoundException
    {
        User user = new User();
        user.setName("testUserName");

        this.userRepository.persist(user);
        
        assertEquals(user, this.userRepository.userOfName(user.name()));
    }
    
    /**
     * Test case for verify that an exception is thrown if the username already exists.
     * 
     * @throws User.Domain.Model.User.UserAlreadyExistsException
     */
    @Test(expected = UserAlreadyExistsException.class)
    public void testPersistAnUserWithAnExistingUsername() throws UserAlreadyExistsException
    {
        String userName = "commonUserName";
        
        User user1 = new User();
        user1.setName(userName);

        User user2 = new User();
        user2.setName(userName);
        
        this.userRepository.persist(user1);
        this.userRepository.persist(user2);
    }
    
    /**
     * Test case for verify that an exception is thrown if the user not exists.
     * 
     * @throws UserNotFoundException 
     */
    @Test(expected = UserNotFoundException.class)
    public void testFindAnUnexistentUser() throws UserNotFoundException
    {
        this.userRepository.userOfName("unexistentUser");
    }
}
