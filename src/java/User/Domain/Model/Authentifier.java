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

import User.Domain.Model.Hasher.PasswordHasher;
import User.Domain.Model.Hasher.UnableToHashPasswordException;
import User.Domain.Model.User.User;
import User.Domain.Model.User.UserNotFoundException;
import User.Domain.Model.User.UserRepository;
import java.util.Arrays;

/**
 *
 * @author odin.delrio
 */
public class Authentifier 
{
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;
    
    public Authentifier(UserRepository userRepository, PasswordHasher passwordHasher)
    {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }
    
    public boolean authenticate(String userName, char[] password) throws UnableToHashPasswordException
    {
        boolean areValidCredentials = false;
        
        try
        {
            User user = this.userRepository.userOfName(userName);
            
            areValidCredentials = Arrays.equals(this.passwordHasher.hash(password), user.password());
        }
        catch (UserNotFoundException exception)
        {
            // If passed username was not found, we can't do nothing.
        }
        
        return areValidCredentials;
    }

    public Authentifier setUserRepository(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
        return this;
    }

    public Authentifier setPasswordHasher(PasswordHasher passwordHasher) 
    {
        this.passwordHasher = passwordHasher;
        return this;
    }
}
