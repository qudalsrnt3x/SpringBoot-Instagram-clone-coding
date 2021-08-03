package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscrib.Subscribe;
import com.cos.photogramstart.domain.subscrib.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserid, int toUserId) {
        //subscribeRepository.save() // 네이티브 쿼리로 짜는게 더 쉬움
        try {
            subscribeRepository.mSubscribe(fromUserid, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserid, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserid, toUserId);
    }
}
