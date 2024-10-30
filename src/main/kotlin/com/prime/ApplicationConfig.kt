package com.prime

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.core.Application
import org.eclipse.microprofile.auth.LoginConfig

@ApplicationPath("/api")
@LoginConfig(authMethod = "MP-JWT")
class ApplicationConfig : Application(){
}