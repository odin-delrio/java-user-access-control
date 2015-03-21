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

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author odin.delrio
 */
public abstract class Hasher
{
    /**
     * Performs the hash operation.
     * 
     * @param stringToHash char[]
     * @return char[]
     * @throws NoSuchAlgorithmException 
     */
    abstract public char[] hash(char[] stringToHash) throws NoSuchAlgorithmException;
    
    /**
     * Executes the common hasher action that converts the bytes array to the char array.
     * 
     * @param arrayBytes char[]
     * @return char[] 
     */
    protected final char[] convertBytesArrayToCharArray(byte[] arrayBytes)
    {
        StringBuilder stringBuffer = new StringBuilder();
        
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer
                .append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                .substring(1));
        }
        
        return stringBuffer.toString().toCharArray();
    }
}
