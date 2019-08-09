package net.quiz.config;

import static net.quiz.constants.PasswordDefault.PASS;

import net.quiz.exception.AuthenException;
import net.quiz.exception.SystemException;
import net.quiz.models.User;
import net.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userId)
          throws UsernameNotFoundException {
    User user = userRepository.findByUserId(userId);
    if (user == null) {
      throw new AuthenException("Login fail");
    }


    //vi khong can pass trong T_USER, nen here phai dua vao de tao UserPrincipal. Neu khong co pass thi se never login
    UserPrincipal uu = UserPrincipal.create(user, passwordEncoder.encode(PASS));

    return uu;
  }

  public UserDetails loadUserById(Integer id)
          throws UsernameNotFoundException {
    User user = userRepository.findById(id).orElseThrow(() -> new SystemException("User not found"));

    //vi khong can pass trong T_USER, nen here phai dua vao de tao UserPrincipal. Neu khong co pass thi se never login
    return UserPrincipal.create(user, passwordEncoder.encode(PASS));
  }
}
