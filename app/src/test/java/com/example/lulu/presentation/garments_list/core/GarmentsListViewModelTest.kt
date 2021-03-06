package com.example.lulu.presentation.garments_list.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lulu.domain.repo.FakeGarmentsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class GarmentsListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var vm: GarmentsListViewModel

    @Before
    fun setup(){

    }

}