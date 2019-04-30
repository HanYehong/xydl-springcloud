package com.njit.xydl.life.lostfound.service;

import com.njit.xydl.life.lostfound.controller.request.SearchRequest;
import com.njit.xydl.life.lostfound.dao.result.LostFoundBean;
import com.njit.xydl.life.lostfound.entity.LostFound;

import java.util.List;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
public interface LostFoundService {
	List<LostFound> listAllLostFoundSelective(SearchRequest param);

	void publishLostFound(LostFoundBean param);
}
