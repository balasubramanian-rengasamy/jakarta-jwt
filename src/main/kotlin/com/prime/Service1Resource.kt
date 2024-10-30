package com.prime

import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path


@Path("/secured")
class Service1Resource {

  @GET
  @Path("/service1")
  @Authenticated
  fun userResource(): String {
    return "successful authenticated"
  }

  @GET
  @Path("/admin")
  @RolesAllowed("admin")
  fun adminResource(): String {
    return "successful admin access"
  }
}
