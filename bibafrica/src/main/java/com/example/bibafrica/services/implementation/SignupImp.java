package com.example.bibafrica.services.implementation;

import com.example.bibafrica.model.Signup;
import com.example.bibafrica.services.SignupInterface;
import com.example.bibafrica.dao.userAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bibafrica.repository.SignupRepository;

@Service
public class SignupImp implements SignupInterface {
  userAuthentication dao=new userAuthentication();
    @Autowired
    SignupRepository signupRepository;

    public SignupImp() {
    }

    @Override
    public Signup saveAccount(Signup signup) {
        return signupRepository.save(signup);
    }

    @Override
    public Signup findAccount(String password) {
        return signupRepository.findById(password).get();
    }

    @Override
    public boolean userCheck(String email, String password) {

        return dao.loginUser(email, password);
    }
}
