package com.njit.xydl.life.lostfound.dao.result;

import com.njit.xydl.life.lostfound.entity.LostFound;
import com.njit.xydl.life.lostfound.entity.LostFoundImage;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
public class LostFoundBean extends LostFound {
	@Valid
	private List<LostFoundImage> imageList;

	@Override
	public List<LostFoundImage> getImageList() {
		return imageList;
	}

	@Override
	public void setImageList(List<LostFoundImage> imageList) {
		this.imageList = imageList;
	}
}
