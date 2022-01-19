import gwittersec.CustomPersonService
import gwittersec.PersonPasswordEncoderListener
import gwittersec.CustomUserDetailsService
// Place your Spring DSL code here
beans = {
    personPasswordEncoderListener(PersonPasswordEncoderListener)
    UserDetailsService(CustomUserDetailsService)
    customPersonService(CustomPersonService)
}

boolean loadExternalBeans() {
    true
}
