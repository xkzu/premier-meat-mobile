package cl.duoc.premier_meat.model

data class User (
    val email: String,
    val name: String,
    val password: String,
    val admin: Boolean
)