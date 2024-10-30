package com.prime

class User(val username: String, val password: String, vararg roles: String) {
    val roles: Set<String> = roles.toSet()

    companion object {
        fun findUser(username: String): User? {
            return when (username) {
                "user0" -> User("user0", "password123", "dummy")
                "user1" -> User("user1", "password123", "user")
                "admin1" -> User("admin1", "adminpass", "user", "admin")
                else -> null
            }
        }
    }
}