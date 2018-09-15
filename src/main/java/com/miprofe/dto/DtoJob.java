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

/**
 * @author tgupta1
 *
 */
public class DtoJob {

	private int jobId;
	private String jobText;
	private String content;
	private String jobVideo;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobText() {
		return jobText;
	}
	public void setJobText(String jobText) {
		this.jobText = jobText;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getJobVideo() {
		return jobVideo;
	}
	public void setJobVideo(String jobVideo) {
		this.jobVideo = jobVideo;
	}
}
