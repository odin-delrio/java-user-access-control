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

/**
 *
 * @author odin.delrio
 */
public enum UserRole 
{
    ROOT ("ROOT"),
    PAGE_1_VIEWER ("PAGE_1_VIEWER"),
    PAGE_2_VIEWER ("PAGE_2_VIEWER"),
    PAGE_3_VIEWER ("PAGE_3_VIEWER");
    
    private final String name;
    
    private UserRole(String name)
    {
        this.name = name;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean equals(String name) 
    {
        return null == name
            ? false
            : name.equals(this.name);
    }
}