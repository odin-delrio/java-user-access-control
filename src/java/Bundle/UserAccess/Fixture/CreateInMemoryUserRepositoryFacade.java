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
package Bundle.UserAccess.Fixture;

import User.Domain.Model.Hasher.Md5PasswordHasher;
import User.Domain.Model.Hasher.UnableToHashPasswordException;
import User.Domain.Model.User.User;
import User.Domain.Model.User.UserAlreadyExistsException;
import User.Domain.Model.User.UserRepository;
import User.Domain.Model.User.UserRole;
import User.Infrastructure.Persistence.InMemory.InMemoryUserRepository;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author odin.delrio
 */
public class CreateInMemoryUserRepositoryFacade 
{
    /**
     * Creates the userRepository and save some test data.
     *
     * Note that the passwords are stored hashed, as a real application should do.
     * 
     * @return UserRepository
     */
    public static UserRepository createUserRepository()
    {
        UserRepository userRepository = new InMemoryUserRepository();
        Md5PasswordHasher md5PasswordHasher = new Md5PasswordHasher();
        
        try {
            userRepository.persist(
                new User()
                    .setName("user1")
                    .setPassword(md5PasswordHasher.hash("1111".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_1_VIEWER)))
            );
            userRepository.persist(
                new User()
                    .setName("user2")
                    .setPassword(md5PasswordHasher.hash("2222".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_2_VIEWER)))
            );
            userRepository.persist(
                new User()
                    .setName("user3")
                    .setPassword(md5PasswordHasher.hash("3333".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_3_VIEWER)))
            );
            userRepository.persist(
                new User()
                    .setName("user4")
                    .setPassword(md5PasswordHasher.hash("4444".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_1_VIEWER, UserRole.PAGE_2_VIEWER)))
            );
        } catch (UserAlreadyExistsException | UnableToHashPasswordException exception) {
            // This a fixture creation, these errors cannot be produced.
        }

        return userRepository;
    }
}
