package com.oluwaseyiayodele.domain.exception.movie

import com.oluwaseyiayodele.domain.exception.Failure

class MovieFailure {
    class ListNotAvailable : Failure.FeatureFailure()
    class NonExistentMovie : Failure.FeatureFailure()
}
