package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.data.model.IDEntity
import kr.ac.kgu.app.trail.data.model.TokenEntity
import kr.ac.kgu.app.trail.util.DataState

interface AuthRepository {
    suspend fun signIn(): Flow<DataState<TokenEntity>>
    suspend fun signUp(): Flow<DataState<IDEntity>>
}