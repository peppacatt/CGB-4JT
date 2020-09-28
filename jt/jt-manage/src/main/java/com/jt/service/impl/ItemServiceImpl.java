package com.jt.service.impl;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		Integer startIndex = (page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(startIndex, rows);
		Long total = Long.valueOf(itemMapper.selectCount(null));
		return new EasyUITable(total, itemList);
	}

	@Override
	public void saveItem(Item item) {
//		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		item.setStatus(1);
		itemMapper.insert(item);
	}
}
