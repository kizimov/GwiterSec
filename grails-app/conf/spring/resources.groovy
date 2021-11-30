import gwittersec.PersonPasswordEncoderListener
import org.springframework.security.core.userdetails.UserDetailsService
import qwittersec.CustomUserDetailsService
// Place your Spring DSL code here
beans = {
    personPasswordEncoderListener(PersonPasswordEncoderListener)
    UserDetailsService(CustomUserDetailsService)
}
