package com.pms.app.web.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pms.app.entity.InventoryDetail;
import com.pms.app.entity.reference.AuditState;
import com.pms.app.service.DelegatorService;
import com.pms.app.service.InventoryDetailService;
import com.pms.app.service.SupervisionCustomerService;

@Controller
@RequestMapping(value = "/manage/inventory")
public class ManageInventoryController {
	
	private Logger logger = LoggerFactory.getLogger(ManageInventoryController.class);
	
	@Autowired DelegatorService delegatorService;
	@Autowired SupervisionCustomerService supervisionCustomerService;
	@Autowired InventoryDetailService inventoryDetailService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) throws Exception {  
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    CustomDateEditor dateEditor = new CustomDateEditor(df, true);
	    binder.registerCustomEditor(Date.class, dateEditor);
	}  
	
	@RequestMapping(value = { "/list", "" })
	public String list(Model model, Pageable pageable, String delegatorId, String supervisionCustomerId, Date date) {
		DateTime dateTime = new DateTime();
		if(date != null) {
			dateTime = new DateTime(date);
			model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
		}
		model.addAttribute("delegatorList", delegatorService.findAll());
		model.addAttribute("supervisionCustomerList", supervisionCustomerService.findAll());
		model.addAttribute("delegatorId", delegatorId);
		model.addAttribute("supervisionCustomerId", supervisionCustomerId);
		model.addAttribute("page", inventoryDetailService.findPageByQuery(pageable, delegatorId, supervisionCustomerId, date));
		return "manage/inventory/list";
	}
	
	@RequestMapping(value = "/{id}/audit")
	public String audit(Model model, @PathVariable("id")String id, RedirectAttributes ra){
		try {
			InventoryDetail inventoryDetail = inventoryDetailService.findById(id);
			inventoryDetail.setAuditState(AuditState.Pass);
			inventoryDetailService.save(inventoryDetail);
			ra.addFlashAttribute("messageOK", "操作成功！");
		}  catch (Exception e) {
			ra.addFlashAttribute("messageErr", "操作失败！");
			logger.error("监管经理盘存操作异常", e);
		}
		return "redirect:/manage/inventory/list";
	}
	
	
}