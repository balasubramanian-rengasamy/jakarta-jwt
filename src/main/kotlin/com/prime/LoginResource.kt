package com.prime

import io.smallrye.jwt.build.Jwt
import jakarta.annotation.security.PermitAll
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/auth")
class LoginResource {

    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun login(credentials: CredentialRequest): Response {
        return if (authenticate(credentials.username, credentials.password)) {
            val token = issueToken(credentials.username)
            Response.ok(token).build()
        } else {
            Response.status(Response.Status.UNAUTHORIZED).build()
        }
    }

  private fun issueToken(username: String): String {
    val user = User.findUser(username) ?: throw IllegalArgumentException("User not found")
    val currentTimeInSecs = System.currentTimeMillis() / 1000
    val expirationTimeInSecs = currentTimeInSecs + 3600 // Token valid for 1 hour

    return Jwt.issuer("https://example.com/issuer")
      .upn(username)
      .groups(user.roles ?: emptySet())
      .claim("sub", username)
      .claim("aud", "your-audience")
      .claim("iat", currentTimeInSecs)
      .claim("exp", expirationTimeInSecs)
      .sign()
  }

    private fun authenticate(username: String, password: String): Boolean {
        val user = User.findUser(username)
        return user != null && user.password == password
    }
}
