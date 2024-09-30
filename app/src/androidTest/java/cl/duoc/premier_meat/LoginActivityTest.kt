package cl.duoc.premier_meat

//import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import org.junit.Rule
//import org.junit.Test
import org.junit.runner.RunWith
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//import org.junit.Before
//import org.mockito.Mock
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import com.google.android.gms.tasks.Task
//import com.google.android.gms.tasks.Tasks
//import org.junit.Assert.assertNotNull
//import org.mockito.ArgumentMatchers.anyString
//import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

//    @get:Rule
//    val activityRule = ActivityScenarioRule(LoginActivity::class.java)
//
//    @Mock
//    private lateinit var mockFirebaseAuth: FirebaseAuth
//
//    @Mock
//    private lateinit var mockFirebaseUser: FirebaseUser
//
//    @Before
//    fun setUp() {
//        // Inicializamos los mocks
//        MockitoAnnotations.openMocks(this)
//    }
//
//    @Test
//    fun testLoginSuccess() {
//        // Simulamos un inicio de sesión exitoso
//        val mockTask: Task<AuthResult> = Tasks.forResult(mock(AuthResult::class.java))
//        `when`(mockFirebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask)
//        `when`(mockFirebaseAuth.currentUser).thenReturn(mockFirebaseUser)
//
//        // Iniciamos la actividad
//        val intent = Intent(ApplicationProvider.getApplicationContext(), LoginActivity::class.java)
//        val scenario = activityRule.scenario
//
//        scenario.onActivity { activity ->
//            // Simulamos el uso de FirebaseAuth mockeado
//            activity.auth = mockFirebaseAuth
//
//            // Llamamos al método que estamos probando
//            activity.iniciarSesionConFirebase("test@example.com", "password123")
//
//            // Verificamos que el método signInWithEmailAndPassword fue llamado correctamente
//            verify(mockFirebaseAuth).signInWithEmailAndPassword("test@example.com", "password123")
//
//            // Verificamos que el usuario autenticado no sea nulo
//            assertNotNull(mockFirebaseAuth.currentUser)
//        }
//    }
//
//    @Test
//    fun testLoginFailure() {
//        // Simulamos un inicio de sesión fallido
//        val mockTask: Task<AuthResult> = Tasks.forException(Exception("Login failed"))
//        `when`(mockFirebaseAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask)
//        `when`(mockFirebaseAuth.currentUser).thenReturn(null)
//
//        // Iniciamos la actividad
//        val intent = Intent(ApplicationProvider.getApplicationContext(), LoginActivity::class.java)
//        val scenario = activityRule.scenario
//
//        scenario.onActivity { activity ->
//            // Simulamos el uso de FirebaseAuth mockeado
//            activity.auth = mockFirebaseAuth
//
//            // Llamamos al método que estamos probando
//            activity.iniciarSesionConFirebase("wrong@example.com", "wrongpassword")
//
//            // Verificamos que el método signInWithEmailAndPassword fue llamado correctamente
//            verify(mockFirebaseAuth).signInWithEmailAndPassword("wrong@example.com", "wrongpassword")
//
//            // Verificamos que el usuario autenticado es nulo
//            assert(mockFirebaseAuth.currentUser == null)
//        }
//    }
}
