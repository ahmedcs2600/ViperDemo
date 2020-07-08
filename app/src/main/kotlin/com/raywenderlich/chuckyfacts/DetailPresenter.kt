package com.raywenderlich.chuckyfacts

import com.raywenderlich.chuckyfacts.entity.Joke
import ru.terrakok.cicerone.Router

class DetailPresenter(private var view : DetailContract.View?) : DetailContract.Presenter {

    private val router: Router? by lazy { App.INSTANCE.cicerone.router }

    override fun backButtonClicked() {
        router?.exit()
    }

    override fun onViewCreated(joke: Joke) {
        view?.showJokeData(joke.id.toString(),joke.text)
    }

    override fun onDestroy() {
        view = null
    }

}