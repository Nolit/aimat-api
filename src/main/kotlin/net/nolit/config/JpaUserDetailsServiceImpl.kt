package net.nolit.dredear.config

import net.nolit.dredear.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JpaUserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails? {

        return userRepository.findByEmail(email)
    }
}