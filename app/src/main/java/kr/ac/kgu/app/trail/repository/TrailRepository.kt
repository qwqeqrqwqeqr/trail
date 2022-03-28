package kr.ac.kgu.app.trail.repository

import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.util.DataState

interface TrailRepository {
    suspend fun test(): Flow<DataState<Unit>>



}