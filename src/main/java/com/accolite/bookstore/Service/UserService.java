package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.User;
import com.accolite.bookstore.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public List<User> getUsers(){
        return this.userRepo.findAll();
    };

    public User getUserById(long userId){
        Optional<User> userObj = this.userRepo.findById(userId);

        if(userObj.isPresent()){
            return userObj.get();
        }else{
            throw new NullPointerException();
        }
    };
    public User createUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(User user){
        Optional<User> userObj = this.userRepo.findById((long) user.getUserId());

        if(userObj.isPresent()){
            User userUpdate = userObj.get();
            userUpdate.setUserId(user.getUserId());
            userUpdate.setUserName(user.getUserName());
            userUpdate.setUserEmail(user.getUserEmail());
            userUpdate.setUserPhone(user.getUserPhone());
            userUpdate.setIsSuspended(user.getIsSuspended());
            return this.userRepo.save(userUpdate);
        }
        else {
            throw new NullPointerException();
        }
    }

    public User suspendUser(User user){
        Optional<User> userObj = this.userRepo.findById((long) user.getUserId());

        if(userObj.isPresent()){
            User userUpdate = userObj.get();
            userUpdate.setUserId(user.getUserId());
            userUpdate.setUserName(user.getUserName());
            userUpdate.setUserEmail(user.getUserEmail());
            userUpdate.setUserPhone(user.getUserPhone());
            userUpdate.setIsSuspended(1);
            return this.userRepo.save(userUpdate);
        }
        else {
            throw new NullPointerException();
        }
    }

}
