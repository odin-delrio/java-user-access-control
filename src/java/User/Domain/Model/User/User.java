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
package User.Domain.Model.User;

import java.util.Set;

/**
 *
 * @author odin.delrio
 */
public class User 
{
    private UserId id;
    
    private String name;
    
    private Set<UserRole> roles;
    
    private char[] password;
    
    public UserId id()
    {
        return this.id;
    }
    
    public String name()
    {
        return this.name;
    }
    
    public char[] password()
    {
        return this.password;
    }
    
    public Set<UserRole> roles()
    {
        return this.roles;
    }

    public User setId(UserId id)
    {
        this.id = id;
        return this;
    }
    
    public User setName(String name) 
    {
        this.name = name;
        return this;
    }

    public User setRoles(Set<UserRole> roles) 
    {
        this.roles = roles;
        return this;
    }

    public User setPassword(char[] password) 
    {
        this.password = password;
        return this;
    }   
}
