package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	//尝试使用MP的方式进行分页操作
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("updated");
		//暂时只封装了2个数据  页数/条数
		IPage<Item> iPage = new Page<>(page, rows);
		//MP 传递了对应的参数,则分页就会在内部完成.返回分页对象
		iPage = itemMapper.selectPage(iPage,queryWrapper);
		//1.获取分页的总记录数
		Long total = iPage.getTotal();
		//2.获取分页的结果
		List<Item> list = iPage.getRecords();
		return new EasyUITable(total, list);
	}

	@Override
	@Transactional	//控制事务
	public void saveItem(Item item, ItemDesc itemDesc) {
		//思考:如果每次编辑数据库 每次都需要操作公共的属性...
		//完成自动的填充功能
		//Date date = new Date();
		//item.setStatus(1).setCreated(date).setUpdated(date);
		item.setStatus(1);
		//如果完成入库操作,时应该动态回显主键信息.
		//MP的方式实现数据入库操作,MP会自动的实现主键信息的回显..
		itemMapper.insert(item);

		//itemDesc属性有值
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);
	}

	@Transactional
	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {

		//更新时间由程序自动填充....
		itemMapper.updateById(item);

		//itemDesc={itemDesc="xxxhtml...",itemId:null}
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
	}


	@Transactional
	@Override
	public void deleteItems(Long[] ids) {
		//1.将数组转化为集合
		List<Long> longList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(longList);
		//2.删除商品详情信息
		itemDescMapper.deleteBatchIds(longList);
	}

	/**
	 * sql: update tb_item set status = #{status},updated={date}
	 * 		where id in (id1,id2,id3)
	 * 	MP机制实现
	 * @param status
	 * @param ids
	 */
	@Override
	public void updateStatus(Integer status, Long[] ids) {

		Item item = new Item();	//封装修改的值
		item.setStatus(status);
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", Arrays.asList(ids));
		itemMapper.update(item,updateWrapper);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {

		return itemDescMapper.selectById(itemId);
	}

	/**
	 * 分页查询商品信息
	 * Sql语句: 每页20条
	 * 		select * from tb_item limit 起始位置,查询的行数
	 * 查询第一页
	 *		select * from tb_item limit 0,20;     [0-19]
	 * 查询第二页
	 * 		select * from tb_item limit 20,20;    [20,39]
	 * 查询第三页
	 * 	 	select * from tb_item limit 40,20;    [40,59]
	 * 查询第N页
	 * 		select * from tb_item limit (n-1)*rows,rows;
	 *
	 *
	 * @param rows
	 * @return
	 */
	/*@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//1.手动完成分页操作
		int startIndex = (page-1) * rows;
		//2.数据库记录
		List<Item> itemList = itemMapper.findItemByPage(startIndex,rows);
		//3.查询数据库总记录数
		long total = itemMapper.selectCount(null);
		//4.将数据库记录 封装为VO对象
		return new EasyUITable(total,itemList);

	}*/
}
