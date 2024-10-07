package cl.duoc.premier_meat

import android.content.Intent
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//import org.robolectric.Robolectric
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
import org.junit.Assert.*

//@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [34])
class LoginActivityTest {

//    @Mock
//    private lateinit var mockFirebaseAuth: FirebaseAuth
//
//    @Mock
//    private lateinit var mockFirebaseUser: FirebaseUser
//
//    private lateinit var loginActivity: LoginActivity
//
//    @Before
//    fun setUp() {
//        // Inicializamos los mocks
//        MockitoAnnotations.openMocks(this)
//
//        // Creamos la actividad usando Robolectric
//        loginActivity = Robolectric.buildActivity(LoginActivity::class.java).create().get()
//        loginActivity.auth = mockFirebaseAuth
//    }
//
//    @Test
//    fun testLoginSuccess() {
//        // Simulamos un inicio de sesión exitoso
//        val mockTask: Task<AuthResult> = Tasks.forResult(mock(AuthResult::class.java))
//        `when`(mockFirebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask)
//        `when`(mockFirebaseAuth.currentUser).thenReturn(mockFirebaseUser)
//
//        // Llama al método de inicio de sesión
//        loginActivity.iniciarSesionConFirebase("test@example.com", "password123")
//
//        // Verifica que el método de inicio de sesión fue llamado correctamente
//        verify(mockFirebaseAuth).signInWithEmailAndPassword("test@example.com", "password123")
//
//        // Verifica que el usuario autenticado no sea nulo
//        assertNotNull(mockFirebaseAuth.currentUser)
//    }
//
//    @Test
//    fun testLoginFailure() {
//        // Simulamos un inicio de sesión fallido
//        val mockTask: Task<AuthResult> = Tasks.forException(Exception("Login failed"))
//        `when`(mockFirebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask)
//        `when`(mockFirebaseAuth.currentUser).thenReturn(null)
//
//        // Llama al método de inicio de sesión
//        loginActivity.iniciarSesionConFirebase("wrong@example.com", "wrongpassword")
//
//        // Verifica que el método de inicio de sesión fue llamado correctamente
//        verify(mockFirebaseAuth).signInWithEmailAndPassword("wrong@example.com", "wrongpassword")
//
//        // Verifica que el usuario autenticado es nulo
//        assertNull(mockFirebaseAuth.currentUser)
//    }
}
