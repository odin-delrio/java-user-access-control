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
package Bundle.UserAccess.Servlets;

import User.Domain.Model.Authentifier;
import User.Domain.Model.Hasher.Md5PasswordHasher;
import User.Domain.Model.Hasher.UnableToHashPasswordException;
import User.Domain.Model.User.User;
import User.Domain.Model.User.UserAlreadyExistsException;
import User.Domain.Model.User.UserNotFoundException;
import User.Domain.Model.User.UserRepository;
import User.Domain.Model.User.UserRole;
import User.Infrastructure.Persistence.InMemory.InMemoryUserRepository;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author odin.delrio
 */
public class Login extends HttpServlet 
{
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
      if (isUserAlreadyLogged(request)) {
            response.sendRedirect("logged-main.jsp");
        } else {
            response.sendRedirect("login.html");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        UserRepository userRepository = this.createUserRepositoryWithTestUsers();
        Authentifier authentifier = new Authentifier(userRepository, new Md5PasswordHasher());
        boolean areValidCredentials = false;
        String username = request.getParameter("username");
        
        try {
            if (authentifier.authenticate(request.getParameter("username"), request.getParameter("pwd").toCharArray())) {
                User user = userRepository.userOfName(username);
                request.getSession().setAttribute("user", user);
                areValidCredentials = true;
            }
        } catch (UnableToHashPasswordException | UserNotFoundException ex) {
            // Can't perform login.
        }
        
        if (areValidCredentials) {
            response.sendRedirect("logged-main.jsp");
        } else {
            response.sendRedirect("login.html");
        }
    }
    
    private boolean isUserAlreadyLogged(HttpServletRequest request)
    {
        return null != request.getSession(false) && null != request.getSession(false).getAttribute("user");
    }
    
    /**
     * Creates the userRepository and save some test data.
     *
     * Note that the passwords are stored hashed, as a real application should do.
     * 
     * @return UserRepository
     * @throws UserAlreadyExistsException
     * @throws UnableToHashPasswordException 
     */
    private UserRepository createUserRepositoryWithTestUsers()
    {
        UserRepository userRepository = new InMemoryUserRepository();
        Md5PasswordHasher md5PasswordHasher = new Md5PasswordHasher();
        
        try {
            userRepository.persist(
                new User()
                    .setName("user1")
                    .setPassword(md5PasswordHasher.hash("1111".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_1_ALLOWED)))
            );
            userRepository.persist(
                new User()
                    .setName("user2")
                    .setPassword(md5PasswordHasher.hash("2222".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_2_ALLOWED)))
            );
            userRepository.persist(
                new User()
                    .setName("user3")
                    .setPassword(md5PasswordHasher.hash("3333".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_3_ALLOWED)))
            );
            userRepository.persist(
                new User()
                    .setName("user4")
                    .setPassword(md5PasswordHasher.hash("4444".toCharArray()))
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.PAGE_1_ALLOWED, UserRole.PAGE_2_ALLOWED)))
            );
        } catch (UserAlreadyExistsException | UnableToHashPasswordException exception) {
            // This a fixture creation, these errors cannot be produced.
        }

        return userRepository;
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        return "Login servlet.";
    }
}
