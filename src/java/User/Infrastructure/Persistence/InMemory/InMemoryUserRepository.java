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
import User.Domain.Model.User.UserId;
import User.Domain.Model.User.UserRepository;

/**
 *
 * @author odin.delrio
 */
public class InMemoryUserRepository implements UserRepository
{

    @Override
    public User userOfName(String name) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserId nextIdentity() 
    {
        return new UserId();
    }
}
