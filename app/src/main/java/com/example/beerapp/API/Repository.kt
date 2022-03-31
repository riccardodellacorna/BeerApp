package com.example.beerapp.API

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getBeer(): Flow<NetworkResult<BeerResponse>> {
        return flow<NetworkResult<BeerResponse>> {
            emit(safeApiCall { remoteDataSource.getBeer() })
        }.flowOn(Dispatchers.IO)
    }
}