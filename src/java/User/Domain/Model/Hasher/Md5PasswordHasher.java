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
package User.Domain.Model.Hasher;

import Library.Hashing.Md5Hasher;
import java.security.NoSuchAlgorithmException;

/**
 * Obviusly, the MD5 with a constant salt is no the best option to create the
 * hash of a password, but, for this example, it's fine.
 * 
 * @author odin.delrio
 */
public class Md5PasswordHasher implements PasswordHasher
{
    private static final String salt = "Secure SALT to hash my passwords!@|$% ;)";
    
    /**
     * 
     * @param password char[] Password stored in a char array.
     * @return The array of chars with the hashed password. 
     * @throws User.Domain.Model.Hasher.UnableToHashPasswordException Transforms the library exception in a domain exception.
     */
    @Override
    public char[] hash(char[] password) throws UnableToHashPasswordException
    {
        char[] saltedPassword = new String(password).concat(salt).toCharArray();
        
        try {
            return new Md5Hasher().hash(saltedPassword);
        } catch (NoSuchAlgorithmException exception) {
            throw new UnableToHashPasswordException();
        }
    }
}
