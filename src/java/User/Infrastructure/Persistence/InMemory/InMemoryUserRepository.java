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
import User.Domain.Model.User.UserId;
import User.Domain.Model.User.UserNotFoundException;
import User.Domain.Model.User.UserRepository;
import java.util.HashMap;

/**
 *
 * @author odin.delrio
 */
public class InMemoryUserRepository implements UserRepository
{
    private final HashMap<String, User> users = new HashMap<>();
    
    @Override
    public User userOfName(String name) throws UserNotFoundException 
    {
        if (!this.users.containsKey(name)) {
            throw new UserNotFoundException();
        }
        
        return users.get(name);
    }

    @Override
    public void persist(User user) throws UserAlreadyExistsException
    {
        if (this.users.containsKey(user.name())) {
            throw new UserAlreadyExistsException();
        }
        
        this.users.put(user.name(), user);
    }

    @Override
    public UserId nextIdentity() 
    {
        return new UserId();
    }
}
