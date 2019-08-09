package net.quiz.controller;

import net.quiz.config.JwtTokenProvider;
import net.quiz.config.UserPrincipal;
import net.quiz.exception.AuthenException;
import net.quiz.exception.SystemException;
import net.quiz.payload.BaseResponse;
import net.quiz.payload.dto.LoginRequest;
import net.quiz.payload.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;
import static net.quiz.constants.PasswordDefault.PASS;

@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController extends BaseController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping
  public ResponseEntity signin(@RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), PASS));
      UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtTokenProvider.generateToken(authentication);

      UserDTO user = new UserDTO();
      user.setToken(token);
      user.setRole(principal.getAuthorities().stream().findFirst().get().toString());
      user.setName(principal.getUsername());

      return ok(user);
    } catch (Exception e) {
      throw new AuthenException("Fail to login");
    }
  }
}
