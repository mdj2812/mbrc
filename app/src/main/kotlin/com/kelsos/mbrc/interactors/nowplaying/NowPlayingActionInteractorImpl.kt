package com.kelsos.mbrc.interactors.nowplaying

import com.kelsos.mbrc.constants.Code
import com.kelsos.mbrc.dto.requests.MoveRequest
import com.kelsos.mbrc.dto.requests.PlayPathRequest
import com.kelsos.mbrc.extensions.task
import com.kelsos.mbrc.services.api.NowPlayingService
import rx.Observable
import javax.inject.Inject

class NowPlayingActionInteractorImpl
@Inject constructor(private val service: NowPlayingService) :
    NowPlayingActionInteractor {

  override fun play(path: String): Observable<Boolean> {
    val request = PlayPathRequest()
    request.path = path
    return service.nowPlayingPlayTrack(request)
        .map { it.code == Code.SUCCESS }
        .task()
  }

  override fun remove(position: Long): Observable<Boolean> {
    return service.nowPlayingRemoveTrack(position)
        .map { it.code == Code.SUCCESS }
        .task()
  }

  override fun move(from: Int, to: Int): Observable<Boolean> {
    val request = MoveRequest(from, to)

    return service.nowPlayingMoveTrack(request)
        .map { it.code == Code.SUCCESS }
        .task()
  }
}
