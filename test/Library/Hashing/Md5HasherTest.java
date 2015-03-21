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
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author odin.delrio
 */
public class Md5HasherTest 
{
    /**
     * Test of hash method, of class Md5Hasher.
     * 
     * @throws java.security.NoSuchAlgorithmException
     * @see http://3v4l.org/GfYkU You can view here the md5 function result with this string in PHP.
     */
    @Test
    public void testHash() throws NoSuchAlgorithmException 
    {
        String stringToHash = "hi, how are you?";
        String hashExpected = "76e79a19a674231c42d3e9aed7d151d7";
        
        Md5Hasher hasher = new Md5Hasher();
        
        Assert.assertArrayEquals(
            hashExpected.toCharArray(), 
            hasher.hash(stringToHash.toCharArray())
        );
    }    
}
