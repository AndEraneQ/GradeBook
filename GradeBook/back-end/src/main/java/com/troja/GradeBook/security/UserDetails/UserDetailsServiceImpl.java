//package com.troja.GradeBook.security.UserDetails;
//
//import com.troja.GradeBook.entity.User;
//import com.troja.GradeBook.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new UserDetailsImpl(user);
//    }
//}
