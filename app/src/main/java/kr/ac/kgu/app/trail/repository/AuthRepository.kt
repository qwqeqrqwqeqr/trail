package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.util.DataState

interface AuthRepository {
    suspend fun signIn(): Flow<DataState<Unit>>
    suspend fun signUp(): Flow<DataState<Unit>>
}