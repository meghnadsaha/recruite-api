//package com.recruitment.service;
//
//import com.recruitment.model.User;
//import com.recruitment.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @Override
////    public UserDetails loadUserByUsername( String username) throws UsernameNotFoundException {
////        User user = userRepository.findByUsername(username)
////                                  .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
////        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
////                                                                      AuthorityUtils.createAuthorityList(user.getRole()));
////    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch the user from the database
//        User user = userRepository.findByUsername(username)
//                                  .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        // Return a UserDetails object (the custom User class implements UserDetails)
//        return user;
//    }
//}
