/**
 * Aloprofe. 
 * Copyright © 2015 Aloprofe. 
 * 
 * All rights reserved.
 * 
 * THIS PRODUCT CONTAINS CONFIDENTIAL INFORMATION  OF ALOPROFE. 
 * USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE 
 * PRIOR EXPRESS WRITTEN PERMISSION OF ALOPROFE.
 */
package com.miprofe.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author tgupta1
 *
 */
public class DtoCmsContent {

	private String cmsContent;
	private CommonsMultipartFile video1;
	private CommonsMultipartFile video2;
	private CommonsMultipartFile video3;
	private CommonsMultipartFile video4;
	private CommonsMultipartFile video5;
	private CommonsMultipartFile video6;
	private CommonsMultipartFile pdf1;
	private CommonsMultipartFile pdf2;
	private CommonsMultipartFile pdf3;
	private String videoTitle;
	
	public String getCmsContent() {
		return cmsContent;
	}
	public void setCmsContent(String cmsContent) {
		this.cmsContent = cmsContent;
	}
	public CommonsMultipartFile getVideo1() {
		return video1;
	}
	public void setVideo1(CommonsMultipartFile video1) {
		this.video1 = video1;
	}
	public CommonsMultipartFile getVideo2() {
		return video2;
	}
	public void setVideo2(CommonsMultipartFile video2) {
		this.video2 = video2;
	}
	public CommonsMultipartFile getVideo3() {
		return video3;
	}
	public void setVideo3(CommonsMultipartFile video3) {
		this.video3 = video3;
	}
	public CommonsMultipartFile getVideo4() {
		return video4;
	}
	public void setVideo4(CommonsMultipartFile video4) {
		this.video4 = video4;
	}
	public CommonsMultipartFile getVideo5() {
		return video5;
	}
	public void setVideo5(CommonsMultipartFile video5) {
		this.video5 = video5;
	}
	public CommonsMultipartFile getVideo6() {
		return video6;
	}
	public void setVideo6(CommonsMultipartFile video6) {
		this.video6 = video6;
	}
	public CommonsMultipartFile getPdf1() {
		return pdf1;
	}
	public void setPdf1(CommonsMultipartFile pdf1) {
		this.pdf1 = pdf1;
	}
	public CommonsMultipartFile getPdf2() {
		return pdf2;
	}
	public void setPdf2(CommonsMultipartFile pdf2) {
		this.pdf2 = pdf2;
	}
	public CommonsMultipartFile getPdf3() {
		return pdf3;
	}
	public void setPdf3(CommonsMultipartFile pdf3) {
		this.pdf3 = pdf3;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
}
