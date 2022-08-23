package mvi.v1.base

interface IView<S : IState> {
    fun render(state: S)
}