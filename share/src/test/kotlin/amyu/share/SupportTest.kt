package amyu.share

import org.junit.Assert.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing

interface SupportTest {
    //Assert
    fun AssertTrue(condition: Boolean) {
        assertTrue(condition)
    }

    fun AssertFalse(condition: Boolean) {
        assertFalse(condition)
    }

    fun AssertEquals(any1: Any, any2: Any) {
        assertEquals(any1, any2)
    }

    fun Fail() {
        fail()
    }

    //Mockito
    fun <T> When(methodCall: T): OngoingStubbing<T> = `when`(methodCall)

    fun <T> OngoingStubbing<T>.ThenReturn(value: T): OngoingStubbing<T> = thenReturn(value)

    fun <T> Verify(mock: T): T = verify(mock)

    fun InitMocks(testClass: Any) {
        MockitoAnnotations.initMocks(testClass)
    }
}