package com.example.shoppingapp.domain.usecase.home

import com.example.shoppingapp.domain.model.BannerModel
import com.example.shoppingapp.domain.repository.HomeRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class GetBannersUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<Response<List<BannerModel>>> {
        return homeRepository.loadBanners()
    }
}