package com.zalesskyi.domain.base

abstract class BaseUseCase<in Params, T>() {

    abstract fun execute(params: Params): T
}