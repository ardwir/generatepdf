package coid.bca.generatepdf.model;

import java.util.Date;
//import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "trx_list")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long trxId;
	
	private String uname;
	private Integer acctDestination;
	private Date trxDate;
	private Double trxAmount;
	private String trxType;
	private String trxStatus;
	
	public Transaction() {
	}
	
	public Transaction(Long trxId, String uname, Integer acctDestination, Date trxDate,
			 Double trxAmount, String trxType, String trxStatus) {
		this.trxId = trxId;
		this.uname = uname;
		this.acctDestination = acctDestination;
		this.trxDate = trxDate;
		this.trxAmount = trxAmount;
		this.trxType = trxType;
		this.trxStatus = trxStatus;
	}

	public Long getTrxId() {
		return trxId;
	}

	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getAcctDestination() {
		return acctDestination;
	}

	public void setAcctDestination(Integer acctDestination) {
		this.acctDestination = acctDestination;
	}

	public Date getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}

	public Double getTrxAmount() {
		return trxAmount;
	}

	public void setTrxAmount(Double trxAmount) {
		this.trxAmount = trxAmount;
	}

	public String getTrxType() {
		return trxType;
	}

	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	public String getTrxStatus() {
		return trxStatus;
	}

	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}
	

}
