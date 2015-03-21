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
package Library.Hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author odin.delrio
 */
public class Md5Hasher extends Hasher
{
    /**
     * Performs the hash operation.
     * 
     * @param stringToHash char[]
     * @return char[]
     * @throws NoSuchAlgorithmException 
     */
    @Override
    public char[] hash(char[] stringToHash) throws NoSuchAlgorithmException
    {
        byte[] stringBytes = new String(stringToHash).getBytes();
        
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return this.convertBytesArrayToCharArray(messageDigest.digest(stringBytes));
    } 
}
