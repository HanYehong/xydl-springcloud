package com.njit.xydl.life.lostfound.service.impl;

import com.njit.xydl.life.lostfound.controller.request.SearchRequest;
import com.njit.xydl.life.lostfound.dao.LostFoundMapper;
import com.njit.xydl.life.lostfound.entity.LostFound;
import com.njit.xydl.life.lostfound.service.LostFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
@Service
public class LostFoundServiceImpl implements LostFoundService {

	@Autowired
	private LostFoundMapper lostFoundMapper;

	@Override
	public List<LostFound> listAllLostFoundSelective(SearchRequest param) {
		return lostFoundMapper.selectSelective(param);
	}
}
