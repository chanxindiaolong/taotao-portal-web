package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AD1Node;

@Controller
public class IndexController {
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		List<TbContent> contentList = contentService.getContentListByCid(AD1_CATEGORY_ID);
		List<AD1Node> ad1Nodes = new ArrayList<AD1Node>();
		for(TbContent content:contentList) {
			AD1Node node = new AD1Node();
			node.setAlt(content.getTitle());
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setSrc(content.getPic());
			node.setSrcB(content.getPic2());
			node.setHref(content.getUrl());
			ad1Nodes.add(node);
		}
		String ad1Json = JsonUtils.objectToJson(ad1Nodes);
		model.addAttribute("ad1",ad1Json);
		return "index";
		}
}
