package com.pms.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 质物清单明细
 * @author wangzz
 */
@Entity
@Table(name = "t_iniPledgeRecordDetail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IniPledgeRecordDetail {
	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "iprd_id")
	private String id;
	
	/**
	 * 所属质物清单
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ipr_id")
	private IniPledgeRecord iniPledgeRecord;
	
	/**
	 * 款式大类
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "sty_id")
	private Style style;
	
	/**
	 * 成色
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "pp_id")
	private PledgePurity pledgePurity;
	
	/**
	 * 规格重量（g/件）
	 */
	@Column(name = "iprd_specWeight")
	private Double specWeight;
	
	/**
	 * 数量（件）
	 */
	@Column(name = "iprd_amount")
	private Double amount;
	
	/**
	 * 总重量（kg）
	 */
	@Column(name = "iprd_sumWeight")
	private Double sumWeight;
	
	/**
	 * 生产厂家
	 */
	@Column(name = "iprd_company")
	private String company;
	
	/**
	 * 存储地点
	 */
	@Column(name = "iprd_storage")
	private String storage;
	
	/**
	 * 是否封闭运输<br>
	 * 0:否<br>
	 * 1:是
	 */
	@Column(name = "iprd_closedTran")
	private int closedTran;
	
	/**
	 * 光谱法抽检重量占比
	 */
	@Column(name = "iprd_spectrumRate")
	private Double spectrumRate;
	
	/**
	 * 溶金法抽检重量占比
	 */
	@Column(name = "iprd_dissolveRate")
	private Double dissolveRate;
	
	/**
	 * 标记
	 */
	@Column(name = "iprd_desc")
	private String desc;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public PledgePurity getPledgePurity() {
		return pledgePurity;
	}

	public void setPledgePurity(PledgePurity pledgePurity) {
		this.pledgePurity = pledgePurity;
	}

	public Double getSpecWeight() {
		return specWeight;
	}

	public void setSpecWeight(Double specWeight) {
		this.specWeight = specWeight;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getSumWeight() {
		return sumWeight;
	}

	public void setSumWeight(Double sumWeight) {
		this.sumWeight = sumWeight;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public int getClosedTran() {
		return closedTran;
	}

	public void setClosedTran(int closedTran) {
		this.closedTran = closedTran;
	}

	public Double getSpectrumRate() {
		return spectrumRate;
	}

	public void setSpectrumRate(Double spectrumRate) {
		this.spectrumRate = spectrumRate;
	}

	public Double getDissolveRate() {
		return dissolveRate;
	}

	public void setDissolveRate(Double dissolveRate) {
		this.dissolveRate = dissolveRate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public IniPledgeRecord getIniPledgeRecord() {
		return iniPledgeRecord;
	}

	public void setIniPledgeRecord(IniPledgeRecord iniPledgeRecord) {
		this.iniPledgeRecord = iniPledgeRecord;
	}
}
