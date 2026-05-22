package com.example.domain.usecase

import javax.inject.Inject

class AcceptCallUseCase @Inject constructor() {
    suspend operator fun invoke(offerSdp: String) {
        // WebRTC SDP answer generation
    }
}
