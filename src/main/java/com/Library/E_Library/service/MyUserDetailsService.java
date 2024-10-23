package com.Library.E_Library.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final String PASSWORD = "$2a$12$8QvbDg8SB4d7Xh1yTV.3Ue8vaQg9SCjlU8tckZfPlZXbpnyjmo6Gi";

    private List<User> userList = Arrays.asList(
            new User("Member", PASSWORD, Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"))),
            new User("Librarian", PASSWORD,Collections.singletonList(new SimpleGrantedAuthority("ROLE_LIBRARIAN")))
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userList.stream().filter(user -> user.getUsername().equals(username))
                .findFirst().orElseThrow(()->new UsernameNotFoundException("User not Found"));
    }


}
