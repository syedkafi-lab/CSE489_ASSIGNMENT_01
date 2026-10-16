package com.example.assignment01

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(SavedStateHandle())
    }

    @Test
    fun testCalculateChange_forAmount234() {
        val change = viewModel.calculateChange(234L)
        assertEquals(0, change[500])
        assertEquals(2, change[100])
        assertEquals(0, change[50])
        assertEquals(1, change[20])
        assertEquals(1, change[10])
        assertEquals(0, change[5])
        assertEquals(2, change[2])
        assertEquals(0, change[1])
    }

    @Test
    fun testCalculateChange_forAmount688_assignmentScreenshot() {
        val change = viewModel.calculateChange(688L)
        assertEquals(1, change[500])
        assertEquals(1, change[100])
        assertEquals(1, change[50])
        assertEquals(1, change[20])
        assertEquals(1, change[10])
        assertEquals(1, change[5])
        assertEquals(1, change[2])
        assertEquals(1, change[1])
    }

    @Test
    fun testCalculateChange_forAmount6880_assignmentScreenshot() {
        val change = viewModel.calculateChange(6880L)
        assertEquals(13, change[500])
        assertEquals(3, change[100])
        assertEquals(1, change[50])
        assertEquals(1, change[20])
        assertEquals(1, change[10])
        assertEquals(0, change[5])
        assertEquals(0, change[2])
        assertEquals(0, change[1])
    }

    @Test
    fun testCalculateChange_forAmount888() {
        val change = viewModel.calculateChange(888L)
        assertEquals(1, change[500])
        assertEquals(3, change[100])
        assertEquals(1, change[50])
        assertEquals(1, change[20])
        assertEquals(1, change[10])
        assertEquals(1, change[5])
        assertEquals(1, change[2])
        assertEquals(1, change[1])
    }

    @Test
    fun testDigitClicked_appendsDigits() {
        viewModel.onDigitClicked("2")
        viewModel.onDigitClicked("3")
        viewModel.onDigitClicked("4")

        assertEquals("234", viewModel.inputAmount.value)
    }

    @Test
    fun testDigitClicked_ignoresLeadingZero() {
        viewModel.onDigitClicked("0")

        assertEquals("", viewModel.inputAmount.value)
    }

    @Test
    fun testClearClicked_resetsInput() {
        viewModel.onDigitClicked("5")
        viewModel.onDigitClicked("0")
        viewModel.onClearClicked()

        assertEquals("", viewModel.inputAmount.value)
    }

    @Test
    fun testRestoreAmount_updatesStateAndCounts() {
        viewModel.restoreAmount("688")
        assertEquals("688", viewModel.inputAmount.value)
        val counts = viewModel.noteCounts.value
        assertEquals(1, counts?.get(500))
        assertEquals(1, counts?.get(100))
        assertEquals(1, counts?.get(50))
        assertEquals(1, counts?.get(20))
        assertEquals(1, counts?.get(10))
        assertEquals(1, counts?.get(5))
        assertEquals(1, counts?.get(2))
        assertEquals(1, counts?.get(1))
    }
}
