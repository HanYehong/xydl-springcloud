package com.njit.xydl.life.lostfound.service.impl;

import com.njit.xydl.life.common.util.UserUtil;
import com.njit.xydl.life.lostfound.controller.request.SearchRequest;
import com.njit.xydl.life.lostfound.dao.LostFoundImageMapper;
import com.njit.xydl.life.lostfound.dao.LostFoundMapper;
import com.njit.xydl.life.lostfound.dao.result.LostFoundBean;
import com.njit.xydl.life.lostfound.entity.LostFound;
import com.njit.xydl.life.lostfound.service.LostFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
@Service
public class LostFoundServiceImpl implements LostFoundService {

	@Autowired
	private LostFoundMapper lostFoundMapper;

	@Autowired
	private LostFoundImageMapper imageMapper;

	@Override
	public List<LostFound> listAllLostFoundSelective(SearchRequest param) {
		return lostFoundMapper.selectSelective(param);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void publishLostFound(LostFoundBean param) {
		param.setCreator(UserUtil.getCurrentUserId());
		String lostNumber = generateLostNumber();
		param.setLostNumber(lostNumber);
		lostFoundMapper.insertSelective(param);
		if (CollectionUtils.isEmpty(param.getImageList())) {
			return;
		}
		param.getImageList().forEach(x -> {
			x.setLostNumber(lostNumber);
			imageMapper.insertSelective(x);
		});
	}

	private String generateLostNumber() {
		return "LF" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + UUID.randomUUID().hashCode();
	}

	public static void main(String args[]) {
		System.out.println(new LostFoundServiceImpl().generateLostNumber());
	}
}
