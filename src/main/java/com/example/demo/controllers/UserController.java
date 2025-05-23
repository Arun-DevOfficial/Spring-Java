package com.example.demo.controllers;

import com.example.demo.Model.Users;
import com.example.demo.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UsersRepository usersRepository;

    // Todo : Error Exception to get error message
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    // Todo : To get all the users
    @GetMapping()
    public ResponseEntity<Iterable<Users>> getUsers() {
       try{
           var users = usersRepository.findAll(); // Retrieve user list
           //Todo : Validate whether users empty or not
           if(!users.iterator().hasNext()) {
               throw new UserNotFoundException("Users not yet created!");
           }
           return ResponseEntity.ok(users); // Response success
       }catch(UserNotFoundException error){
           System.out.println(error.getMessage());
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

    // Todo : To get a specific user
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable String id){
        try{
            var user = usersRepository.findById(id).orElse(null); //
            // Todo : Validate User data
            if(user==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user); // Todo : Send a success response
        }catch(UserNotFoundException error){
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Todo : Add users
    @PostMapping("/register")
    public ResponseEntity<Users> createUser(@RequestBody Users user){
        try {
            Users checkUser = usersRepository.findByEmail(user.getEmail());
            // Todo : Check whether user exists or not
            if(checkUser == null) {
              var users = usersRepository.save(user);
              return ResponseEntity.ok(users);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (UserNotFoundException error) {
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // Todo : To Login into app
    @PostMapping("/login")
    public ResponseEntity<Users> LoginUser(@RequestBody Users user){
        try{
            var checkUser = usersRepository.findByEmail(user.getEmail());
            return ResponseEntity.ok(checkUser);
        }catch(UserNotFoundException error){
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    //Todo : Update user with id
    @PatchMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Users id, @RequestBody Users user){
        try{
            var checkUser = usersRepository.findById(id.getId()).orElse(null);
            //Todo : Check User exists
            if(checkUser == null) {
                return ResponseEntity.notFound().build();
            }
            //Todo : To store user in db
            var users = usersRepository.save(user);
            return ResponseEntity.ok(users);
        }catch(UserNotFoundException error){
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
     // Todo : To delete a user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable String id){
        try{
            var users = usersRepository.findById(id).orElse(null);
            //Todo : Check User exists
            if(users == null) {
                return ResponseEntity.notFound().build();
            }
            //Todo : Todo Delete users
            usersRepository.delete(users);
            return ResponseEntity.noContent().build();
        }catch(UserNotFoundException error){
            System.out.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}