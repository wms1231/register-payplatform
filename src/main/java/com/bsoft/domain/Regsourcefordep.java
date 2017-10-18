package com.bsoft.domain;

import java.util.ArrayList;
import java.util.List;

import com.bsoft.domain.DoctorReturnBean;

/**
 * 某个挂号科室的排班信息整合
 * @author admin
 *
 */
public class Regsourcefordep {
	
	//医生信息
	private DoctorReturnBean doctorInfo ;
	
	private List<RegOrderDay>  pbiNfo = new ArrayList<RegOrderDay>();
	
	public DoctorReturnBean getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorReturnBean doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public List<RegOrderDay> getPbiNfo() {
		return pbiNfo;
	}

	public void setPbiNfo(List<RegOrderDay> pbiNfo) {
		this.pbiNfo = pbiNfo;
	}
	public void addPbiNfo(RegOrderDay pbiNfo) {
		this.pbiNfo.add(pbiNfo);
	}


}
